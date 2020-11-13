<template>
  <div class="infoFormat">
    <el-collapse v-model="activeCard">
      <el-collapse-item name="请求参数">
        <template slot="title">
          <div class="title">请求参数</div>
        </template>
        <el-tabs v-model="requestActiveName" type="border-card">
          <el-tab-pane label="请求头" name="requestHeader">
            <el-table
              :data="requestHeaderData"
              border
              style="width: 100%"
              row-key="dataId"
            >
              <el-table-column
                prop="name"
                label="标签"
                align="center"
                min-width="180"
              >
              </el-table-column>
              <el-table-column
                prop="required"
                label="必填"
                min-width="80"
                align="center"
                :formatter="requiredFormatter"
              >
              </el-table-column>
              <el-table-column
                prop="type"
                label="类型"
                min-width="100px"
                align="center"
              >
                <template slot-scope="scope"> [{{ scope.row.type }}] </template>
              </el-table-column>
              <el-table-column
                prop="lengthLimit"
                label="长度限制"
                min-width="80px"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="otherLimit"
                label="其他限制"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="explain"
                label="说明"
                align="center"
              ></el-table-column>
              <el-table-column prop="example" label="示例" align="center">
              </el-table-column>
              <el-table-column min-width="180px" align="right">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="
                      editParameter(scope.row, requestHeaderData, '请求头')
                    "
                    >Edit</el-button
                  >
                  <el-button
                    size="mini"
                    type="danger"
                    @click="deleteParameter(scope.row, requestHeaderData)"
                    >Delete</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <div class="addLabel">
              <el-button
                size="small"
                @click="newParameter('请求头')"
                style="margin-top: 10px"
              >
                <i class="el-icon-circle-plus-outline"> 新增标签</i>
              </el-button>
            </div>
          </el-tab-pane>
          <el-tab-pane label="请求体" name="requestBody">
            <div class="requestBodyType">
              <el-radio-group v-model="requestBodyData.type">
                <el-radio label="Form-data" key="Form-data">Form-data</el-radio>
                <el-radio label="JSON" key="JSON">JSON</el-radio>
              </el-radio-group>
            </div>
            <div class="rootTypeBox" v-show="requestBodyData.type == 'JSON'">
              <div class="selectionTitle">JSON 根类型：</div>
              <el-select v-model="requestBodyData.rootType" size="mini">
                <el-option
                  key="Object"
                  label="Object"
                  value="Object"
                ></el-option>
                <el-option key="Array" label="Array" value="Array"> </el-option>
              </el-select>
            </div>
            <el-table
              :data="requestBodyData[requestBodyData.type]"
              border
              style="width: 100%"
              row-key="dataId"
              default-expand-all
              :tree-props="{ children: 'subfield' }"
            >
              <el-table-column
                prop="name"
                label="参数名"
                align="center"
                min-width="180"
              >
              </el-table-column>
              <el-table-column
                prop="required"
                label="必填"
                min-width="80"
                align="center"
                :formatter="requiredFormatter"
              >
              </el-table-column>
              <el-table-column
                prop="type"
                label="类型"
                width="100px"
                align="center"
              >
                <template slot-scope="scope"> [{{ scope.row.type }}] </template>
              </el-table-column>
              <el-table-column
                prop="lengthLimit"
                label="长度限制"
                min-width="80px"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="otherLimit"
                label="其他限制"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="explain"
                label="说明"
                align="center"
              ></el-table-column>
              <el-table-column prop="example" label="示例" align="center">
              </el-table-column>
              <el-table-column min-width="250px" align="right">
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    size="mini"
                    v-show="requestBodyData.type == 'JSON'"
                    @click="
                      newSubfield(
                        scope.row,
                        requestBodyData[requestBodyData.type],
                        '请求体'
                      )
                    "
                    >增加子字段</el-button
                  >
                  <el-button
                    size="mini"
                    @click="
                      editParameter(
                        scope.row,
                        requestBodyData[requestBodyData.type],
                        '请求体'
                      )
                    "
                    >Edit</el-button
                  >
                  <el-button
                    size="mini"
                    type="danger"
                    @click="
                      deleteParameter(
                        scope.row,
                        requestBodyData[requestBodyData.type]
                      )
                    "
                    >Delete</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <div class="addLabel">
              <el-button
                size="small"
                @click="newParameter('请求体')"
                style="margin-top: 10px"
              >
                <i class="el-icon-circle-plus-outline"> 新增参数</i>
              </el-button>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Query 参数" name="query">
            <el-table
              :data="queryData"
              border
              style="width: 100%"
              row-key="dataId"
            >
              <el-table-column
                prop="name"
                label="参数名"
                align="center"
                min-width="180"
              >
              </el-table-column>
              <el-table-column
                prop="required"
                label="必填"
                min-width="80"
                align="center"
                :formatter="requiredFormatter"
              >
              </el-table-column>
              <el-table-column
                prop="type"
                label="类型"
                min-width="100px"
                align="center"
              >
                <template slot-scope="scope"> [{{ scope.row.type }}] </template>
              </el-table-column>
              <el-table-column
                prop="lengthLimit"
                label="长度限制"
                min-width="80px"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="otherLimit"
                label="其他限制"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="explain"
                label="说明"
                align="center"
              ></el-table-column>
              <el-table-column prop="example" label="示例" align="center">
              </el-table-column>
              <el-table-column min-width="180px" align="right">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="editParameter(scope.row, queryData, 'Query 参数')"
                    >Edit</el-button
                  >
                  <el-button
                    size="mini"
                    type="danger"
                    @click="deleteParameter(scope.row, queryData)"
                    >Delete</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <div class="addLabel">
              <el-button
                size="small"
                @click="newParameter('Query 参数')"
                style="margin-top: 10px"
              >
                <i class="el-icon-circle-plus-outline"> 新增参数</i>
              </el-button>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Rest 参数" name="rest">
            <el-table
              :data="restData"
              border
              style="width: 100%"
              row-key="dataId"
            >
              <el-table-column
                prop="name"
                label="参数名"
                align="center"
                min-width="180"
              >
              </el-table-column>
              <el-table-column
                prop="required"
                label="必填"
                min-width="80"
                align="center"
                :formatter="requiredFormatter"
              >
              </el-table-column>
              <el-table-column
                prop="type"
                label="类型"
                min-width="100px"
                align="center"
              >
                <template slot-scope="scope"> [{{ scope.row.type }}] </template>
              </el-table-column>
              <el-table-column
                prop="lengthLimit"
                label="长度限制"
                min-width="80px"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="otherLimit"
                label="其他限制"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="explain"
                label="说明"
                align="center"
              ></el-table-column>
              <el-table-column prop="example" label="示例" align="center">
              </el-table-column>
              <el-table-column min-width="180px" align="right">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="editParameter(scope.row, restData, 'Rest 参数')"
                    >Edit</el-button
                  >
                  <el-button
                    size="mini"
                    type="danger"
                    @click="deleteParameter(scope.row, restData)"
                    >Delete</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <div class="addLabel">
              <el-button
                size="small"
                @click="newParameter('Rest 参数')"
                style="margin-top: 10px"
              >
                <i class="el-icon-circle-plus-outline"> 新增参数</i>
              </el-button>
            </div></el-tab-pane
          >
        </el-tabs>
      </el-collapse-item>
      <el-collapse-item name="响应内容">
        <template slot="title">
          <div class="title">响应内容</div>
        </template>

        <el-tabs v-model="responseActiveName" type="border-card">
          <el-tab-pane label="响应头" name="responseHeader">
            <el-table
              :data="responseHeaderData"
              border
              style="width: 100%"
              row-key="dataId"
            >
              <el-table-column
                prop="name"
                label="标签"
                align="center"
                min-width="180"
              >
              </el-table-column>
              <el-table-column
                prop="required"
                label="必填"
                min-width="80"
                align="center"
                :formatter="requiredFormatter"
              >
              </el-table-column>
              <el-table-column
                prop="type"
                label="类型"
                min-width="100px"
                align="center"
              >
                <template slot-scope="scope"> [{{ scope.row.type }}] </template>
              </el-table-column>
              <el-table-column
                prop="lengthLimit"
                label="长度限制"
                min-width="80px"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="otherLimit"
                label="其他限制"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="explain"
                label="说明"
                align="center"
              ></el-table-column>
              <el-table-column prop="example" label="示例" align="center">
              </el-table-column>
              <el-table-column min-width="180px" align="right">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="
                      editParameter(scope.row, responseHeaderData, '响应头')
                    "
                    >Edit</el-button
                  >
                  <el-button
                    size="mini"
                    type="danger"
                    @click="deleteParameter(scope.row, responseHeaderData)"
                    >Delete</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <div class="addLabel">
              <el-button
                size="small"
                @click="newParameter('响应头')"
                style="margin-top: 10px"
              >
                <i class="el-icon-circle-plus-outline"> 新增标签</i>
              </el-button>
            </div>
          </el-tab-pane>
          <el-tab-pane label="响应体" name="responseBody">
            <div class="rootTypeBox">
              <div class="selectionTitle">JSON 根类型：</div>
              <el-select v-model="responseBodyData.rootType" size="mini">
                <el-option
                  key="Object"
                  label="Object"
                  value="Object"
                ></el-option>
                <el-option key="Array" label="Array" value="Array"> </el-option>
              </el-select>
            </div>
            <el-table
              :data="responseBodyData.data"
              border
              style="width: 100%"
              row-key="dataId"
              default-expand-all
              :tree-props="{ children: 'subfield' }"
            >
              <el-table-column
                prop="name"
                label="参数名"
                align="center"
                min-width="180"
              >
              </el-table-column>
              <el-table-column
                prop="required"
                label="必填"
                min-width="80"
                align="center"
                :formatter="requiredFormatter"
              >
              </el-table-column>
              <el-table-column
                prop="type"
                label="类型"
                width="100px"
                align="center"
              >
                <template slot-scope="scope"> [{{ scope.row.type }}] </template>
              </el-table-column>
              <el-table-column
                prop="lengthLimit"
                label="长度限制"
                min-width="80px"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="otherLimit"
                label="其他限制"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="explain"
                label="说明"
                align="center"
              ></el-table-column>
              <el-table-column prop="example" label="示例" align="center">
              </el-table-column>
              <el-table-column min-width="250px" align="right">
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    size="mini"
                    @click="
                      newSubfield(scope.row, responseBodyData.data, '响应体')
                    "
                    >增加子字段</el-button
                  >
                  <el-button
                    size="mini"
                    @click="
                      editParameter(scope.row, responseBodyData.data, '响应体')
                    "
                    >Edit</el-button
                  >
                  <el-button
                    size="mini"
                    type="danger"
                    @click="deleteParameter(scope.row, responseBodyData.data)"
                    >Delete</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <div class="addLabel">
              <el-button
                size="small"
                @click="newParameter('响应体')"
                style="margin-top: 10px"
              >
                <i class="el-icon-circle-plus-outline"> 新增参数</i>
              </el-button>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-collapse-item>
    </el-collapse>
    <new-field
      :typeList="typeList_now"
      dialogWidth="40%"
      @newField="updateParameter"
      ref="newParameter"
    >
      <span slot="title">请求头详情</span>
    </new-field>
  </div>
</template>

<script>
import newField from "./newField.vue";

export default {
  name: "infoFormat",
  components: {
    newField,
  },
  data() {
    return {
      activeCard: ["请求参数", "响应内容"],
      requestActiveName: "requestBody", // 请求参数默认展示的标签
      responseActiveName: "responseBody", // 响应内容默认展示的标签
      operation: "new", // 新增：new 修改：change
      operationObject: "", // 操作的对象（请求头、请求体等）
      parentObjDataId: "", // 在进行新建子字段操作时，父对象的 dataId
      typeList_now: [],
      typeList: {
        // 可选的数据类型
        requestHeader: [
          "string",
          "array",
          "object",
          "number",
          "null",
          "json",
          "int",
          "float",
          "double",
          "date",
          "datetime",
          "boolean",
          "short",
          "long",
          "char",
        ],
        requestBody: {
          "Form-data": [
            "string",
            "file",
            "json",
            "int",
            "float",
            "double",
            "date",
            "datetime",
            "boolean",
            "byte",
            "short",
            "long",
            "array",
            "object",
            "number",
            "null",
            "char",
            "demo_structure",
            "user_info",
          ],
          JSON: [
            "string",
            "array",
            "object",
            "number",
            "null",
            "json",
            "int",
            "float",
            "double",
            "date",
            "datetime",
            "boolean",
            "short",
            "long",
            "char",
            "demo_structure",
            "user_info",
          ],
        },
      },
      requestHeaderData: [
        // 请求头数据
        {
          dataId: "1",
          name: "contant-type",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
        {
          dataId: "2",
          name: "contant-type",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
        {
          dataId: "3",
          name: "contant-type",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
      ],
      requestBodyData: {
        type: "Form-data",
        rootType: "Object", // JSON 根类型（仅当 type 为 JSON 时有效）
        "Form-data": [
          {
            dataId: "1",
            name: "username",
            required: true,
            lengthLimit: "10",
            otherLimit: "不能包含空格",
            explain: "用户名",
            type: "string",
            example: "",
          },
          {
            dataId: "2",
            name: "password",
            required: true,
            lengthLimit: "8",
            otherLimit: "不能含有特殊字符",
            explain: "密码",
            type: "string",
            example: "",
          },
        ],
        JSON: [
          {
            dataId: "1",
            name: "userid",
            required: true,
            lengthLimit: "2",
            otherLimit: "只能为数字",
            explain: "用户id",
            type: "number",
            example: "",
            subfield: [],
          },
        ],
      },
      queryData: [
        // 请求头数据
        {
          dataId: "1",
          name: "name",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
        {
          dataId: "2",
          name: "age",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
        {
          dataId: "3",
          name: "sex",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
      ],
      restData: [
        // 请求头数据
        {
          dataId: "1",
          name: "name",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
        {
          dataId: "2",
          name: "age",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
      ],
      responseHeaderData: [
        // 响应头数据
        {
          dataId: "1",
          name: "contant-type",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
        {
          dataId: "2",
          name: "Accept-Charset",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
        {
          dataId: "3",
          name: "Warning",
          required: true,
          lengthLimit: "",
          otherLimit: "",
          explain: "",
          type: "string",
          example: "",
        },
      ],
      responseBodyData: {
        rootType: "Object", // JSON 根类型
        data: [
          {
            dataId: "1",
            name: "contant-type",
            required: true,
            lengthLimit: "",
            otherLimit: "",
            explain: "",
            type: "string",
            subfield: [],
            example: "",
          },
        ],
      },
    };
  },
  methods: {
    /**
     * 格式化 是否必填 的显示
     */
    requiredFormatter(row, column, cellValue, index) {
      return cellValue ? "是" : "";
    },
    /**
     * 显示新增标签的弹窗
     */
    showNewLableDialog() {
      // 设置可选择的数据类型
      switch (this.operationObject) {
        case "请求头":
          this.typeList_now = this.typeList.requestHeader;
          break;
        case "请求体":
          this.typeList_now = this.typeList.requestBody[
            this.requestBodyData.type
          ];
          break;
        case "Query 参数":
          this.typeList_now = this.typeList.requestBody.JSON; // 一样的
          break;
        case "Rest 参数":
          this.typeList_now = this.typeList.requestBody["Form-data"]; // 一样的
          break;
        case "响应头":
          this.typeList_now = this.typeList.requestHeader; // 一样的
          break;
        case "响应体":
          this.typeList_now = this.typeList.requestBody["Form-data"]; // 一样的
          break;

        default:
          break;
      }

      this.$refs.newParameter.dialogVisible = true;
    },
    /**
     * 深度搜索树形数据结构
     * @param {Object} obj 被搜索的数据
     * @param {String} keyName 目标对象用以识别的键名
     * @param {String} keyValue 目标对象用以识别的键值
     * @return {Object} 搜索到的目标对象
     */
    deepSearch(obj, keyName, keyValue) {
      // todo 第一组搜索不到的bug
      let targetObj = null;
      for (const el of obj) {
        if (el[keyName] === keyValue) {
          return el;
        }
        if (el.subfield != undefined && el.subfield.length > 0) {
          targetObj = this.deepSearch(el.subfield, keyName, keyValue);
          if (targetObj != null) {
            return targetObj;
          }
        }
      }
    },
    /**
     * 点击增加子字段的回调函数
     * @param {Object} row 该行的数据
     * @param {Object} data 要操作的数据
     * @param {String} operationModule 正在操作的模块
     */
    newSubfield(row, data, operationModule) {
      this.parentObjDataId = row.dataId;
      this.newParameter(operationModule);
      this.operation = "newSubfield";
    },
    /**
     * 点击编辑参数的回调函数
     * @param {Object} row 该行的数据
     * @param {Object} data 要操作的数据
     * @param {String} operationModule 正在操作的模块
     */
    editParameter(row, data, operationModule) {
      this.operation = "change";
      this.$refs.newParameter.info = this.deepSearch(
        data,
        "dataId",
        row.dataId
      );
      this.operationObject = operationModule;
      this.showNewLableDialog();
    },
    /**
     * 点击删除参数的回调函数
     * @param {Object} row 该行的数据
     * @param {Object} data 要操作的数据
     */
    deleteParameter(row, data) {
      let targetObj = this.deepSearch(data, "dataId", row.dataId);
      let parentDataId = targetObj.dataId.slice(0, targetObj.dataId.length - 1);
      let parentObj = null;
      if (parentDataId == "") {
        parentObj = data;
      } else {
        parentObj = this.deepSearch(data, "dataId", parentDataId).subfield;
      }
      parentObj.forEach((el, i) => {
        if (el.dataId == row.dataId) {
          parentObj.splice(i, 1);
        }
      });
    },
    /**
     * 点击新增参数的回调函数
     * @param {String} operationModule 正在操作的模块
     */
    newParameter(operationModule) {
      this.operation = "new";
      this.operationObject = operationModule;
      this.showNewLableDialog();
    },
    /**
     * 改变原有标签的信息或新增标签
     * @param {Object} info 标签的信息
     */
    updateParameter(info) {
      let data = null;
      // 判断要操作哪个模块的数据
      switch (this.operationObject) {
        case "请求头":
          data = this.requestHeaderData;
          break;
        case "请求体":
          data = this.requestBodyData[this.requestBodyData.type];
          break;
        case "Query 参数":
          data = this.queryData;
          break;
        case "Rest 参数":
          data = this.restData;
          break;
        case "响应头":
          data = this.responseHeaderData;
          break;
        case "响应体":
          data = this.responseBodyData.data;
          break;

        default:
          break;
      }
      if (this.operation == "new") {
        // 新增标签，直接添加在最外层的数组里面就可以
        info.dataId = (data.length + 1).toString();
        this.$set(info, "subfield", []); // 强制视图更新
        data.push(info);
      } else if (this.operation == "newSubfield") {
        // 新增子字段，需要判断要插入哪个数组
        const parentArr = this.deepSearch(data, "dataId", this.parentObjDataId)
          .subfield;
        info.dataId = this.parentObjDataId + "0";
        this.$set(info, "subfield", []); // 强制视图更新
        let isOK = true;
        do {
          isOK = true;
          info.dataId = (parseInt(info.dataId) + 1).toString();
          parentArr.forEach((el) => {
            if (el.dataId == info.dataId) {
              isOK = false;
            }
          });
        } while (!isOK);

        parentArr.push(info);
        this.parentObjDataId = "";
      }
      // 由于对象数据类型是引用地址，不必手动再修改值
    },
  },
};
</script>

<style scoped>
.infoFormat {
  margin-top: 40px;
}
.title {
  font-size: 15px;
  color: #333333;
  padding-left: 15px;
  border-left: 5px solid #7fbdfb;
}
.infoFormat /deep/ .el-collapse-item__header {
  background-color: #f2f7fd !important;
  margin-top: 40px;
}
.infoFormat /deep/ .el-collapse-item__wrap {
  padding-top: 30px;
}
.requestBodyType {
  margin-bottom: 15px;
  margin-left: 15px;
}
.rootTypeBox {
  margin-left: 10px;
  margin-bottom: 15px;
}
.selectionTitle {
  font-size: 12px;
  color: #999999;
  margin-bottom: 4px;
}
</style>