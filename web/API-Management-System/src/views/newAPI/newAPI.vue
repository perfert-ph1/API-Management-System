<template>
  <div class="newAPI">
    <!-- 是否继续对话框 -->
    <el-dialog title="提示" :visible.sync="returnWarningVisible" width="30%">
      <span>此操作将会丢失未保存的内容，是否继续？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="returnWarningVisible = false">取 消</el-button>
        <el-button type="danger" @click="$router.go(-1)">确 定</el-button>
      </span>
    </el-dialog>
    <div class="header">
      <div class="backButton">
        <img src="~@/assets/images/backIcon.png" alt="返回" /><span
          @click.self="backToAPIList"
          >返回 API 列表</span
        >
        <el-button
          class="submitButton"
          type="primary"
          size="small"
          @click.stop="submitAPIInfo"
          ><i class="el-icon-plus"></i> 保存</el-button
        >
      </div>
      <div class="tagBox">
        <div
          class="tagButton"
          :class="{ tag_actived: moduleActived.apiDesc }"
          @click="changeModule('apiDesc')"
        >
          API 描述
        </div>
        <div
          class="tagButton"
          :class="{ tag_actived: moduleActived.returnExample }"
          @click="changeModule('returnExample')"
        >
          返回示例
        </div>
        <div
          class="tagButton"
          :class="{ tag_actived: moduleActived.additionalDesc }"
          @click="changeModule('additionalDesc')"
        >
          额外说明
        </div>
      </div>
    </div>
    <div class="body">
      <!-- api 描述 -->
      <div class="apiDesc" v-show="moduleActived.apiDesc">
        <basic-info ref="basicInfo"></basic-info>
        <info-format ref="infoFormat"></info-format>
      </div>
      <!-- 返回示例 -->
      <div class="returnExample" v-show="moduleActived.returnExample">
        <div class="item" @click="editExample('success')">
          <div class="text">成功示例</div>
          <div class="icon"><i class="el-icon-edit"></i> 编辑</div>
        </div>
        <div class="item" @click="editExample('fail')">
          <div class="text">失败示例</div>
          <div class="icon"><i class="el-icon-edit"></i> 编辑</div>
        </div>
      </div>
      <return-example ref="exampleForm"></return-example>
      <!-- 额外说明 -->
      <div class="additionalDesc" v-show="moduleActived.additionalDesc">
        additionalDesc
      </div>
    </div>
  </div>
</template>

<script>
import basicInfo from "./components/basicInfo.vue";
import infoFormat from "./components/infoFormat.vue";
import returnExample from "./components/returnExample.vue";

export default {
  name: "newAPI",
  components: {
    basicInfo,
    infoFormat,
    returnExample,
  },
  data() {
    return {
      returnWarningVisible: false,
      moduleActived: {
        apiDesc: true,
        returnExample: false,
        additionalDesc: false,
      },
      exampleType: "", // 当前编辑的示例类型
      successExample: {
        name: "成功示例",
        statusCode: "",
        ContentType: "",
        value: "",
      },
      failExample: {
        name: "失败示例",
        statusCode: "",
        ContentType: "",
        value: "",
      },
    };
  },
  methods: {
    /**
     * 确认是否返回 api 列表
     */
    backToAPIList() {
      this.returnWarningVisible = true;
    },
    /**
     * 保存 api 信息并返回列表
     */
    submitAPIInfo() {
      // 获取基础信息组件里的数据
      const basicInfo = this.$refs.basicInfo.submitBasicInfo();
      // 获取请求参数的相关信息
      const requestInfo = this.$refs.infoFormat.submitRequestInfo();
      // 获取响应参数的相关信息
      const responseInfo = this.$refs.infoFormat.submitResponseInfo();
      console.log(basicInfo, requestInfo, responseInfo);
      // todo 网络请求 此处参数值不完善
      this.$axios
        .post({
          url: "/api_management/apis/addApi",
          params: {
            gid: basicInfo.groupId,
            apiName: basicInfo.apiName,
            reqProtocol: basicInfo.transferProtocol,
            reqMethod: basicInfo.requestMethod,
            status: basicInfo.status,
            url: basicInfo.path,
            creatorId: basicInfo.creatorId,
            managerId: basicInfo.leaderId,
            // reqBodyType: ["Form-data", "JSON"].indexOf(requestInfo.body.type),
            // respJsonType: ["Object", "Array"].indexOf(requestInfo.body.rootType),
            // apiTags: basicInfo.tags,
            // apiRtnExample: [], // 返回示例
            // queryParam: requestInfo.query,
            // reqHeader: requestInfo.header,
            // reqBody: requestInfo.body,
            // respHeader: responseInfo.header,
            // respBody: responseInfo.body,
            // extraExplain: "",
          },
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            this.$router.go(-1);
          }
        });
    },
    /**
     * 改变当前显示的模块
     * @param {String} moduleName 当前点击的模块名
     */
    changeModule(moduleName) {
      for (const key in this.moduleActived) {
        if (key == moduleName) {
          this.moduleActived[key] = true;
        } else {
          this.moduleActived[key] = false;
        }
      }
    },
    /**
     * 点击编辑的回调函数
     * @param {String} status 返回示例的类别
     */
    editExample(status) {
      this.exampleType = status;
      this.$refs.exampleForm.dialogFormVisible = true;
      this.$refs.exampleForm.ableEditName = false;
      // 直接让子组件的变量和父组件变量指向同一个对象的地址，就可以同步数据
      this.$refs.exampleForm.exampleInfo =
        status == "success" ? this.successExample : this.failExample;
    },
  },
};
</script>

<style scoped>
.newAPI {
  user-select: none;
}
.header {
  height: 90px;
  width: 100%;
  background-color: #f0f2f5;
  box-sizing: border-box;
  position: fixed;
  top: 86px;
  border-bottom: 1px solid #d9d9d9;
  z-index: 100;
}
.backButton {
  height: 18px;
  width: 200px;
  line-height: 18px;
  padding: 2px;
  font-size: 15px;
  color: #333333;
  position: absolute;
  top: 15px;
  left: 40px;
}
.submitButton {
  font-size: 15px;
  padding: 6px 8px;
  position: absolute;
  top: 50%;
  transform: translate(15px, -50%);
}
.backButton > span:hover {
  color: #409eff;
  cursor: pointer;
}
.backButton img {
  height: 100%;
  vertical-align: top;
  margin-right: 6px;
}
.tagBox {
  position: absolute;
  bottom: -1px;
  left: 40px;
}
.tagButton {
  height: 30px;
  line-height: 30px;
  padding: 0 10px;
  font-size: 14px;
  color: #333333;
  display: inline-block;
  cursor: pointer;
  border-radius: 4px 4px 0 0;
  box-sizing: border-box;
  border-top: 1px solid #f0f2f5;
  border-left: 1px solid #f0f2f5;
  border-right: 1px solid #f0f2f5;
}
.tag_actived {
  color: #409eff;
  border: 1px solid #d9d9d9;
  border-bottom: 1px solid white !important;
  background-color: white;
}
.body {
  /* height: 400px; */
  width: 100%;
  margin-top: 90px;
  padding: 40px;
  box-sizing: border-box;
  background-color: white;
}
.item {
  width: 40%;
  height: 50px;
  margin: 30px auto;
  padding: 0 15px;
  border: 1px solid #d9d9d9;
  border-radius: 3px;
  display: flex;
  justify-content: space-between;
  color: #333333;
  font-size: 13px;
  cursor: pointer;
}
.text,
.icon {
  height: 50px;
  line-height: 50px;
}
</style>