<template>
  <div class="statusCodeList">
    <navigation-box
      @selectGroup="selectGroup"
      @addGroup="addGroup"
      @editGroup="editGroup"
      @deleteGroup="deleteGroup"
      :groupList="groupList"
      ref="navigationBox"
    >
      <span slot="title">所有状态码</span>
    </navigation-box>
    <data-list
      @search="search"
      @newSth="editStatusCode('')"
      :totalNum="statusCodeList.length"
    >
      <template v-slot:buttonName>新建状态码</template>
      <!-- 表格 -->
      <template v-slot:table>
        <el-table :data="statusCodeList" border height="100%" max-height="100%">
          <!-- 状态码 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="code"
            label="状态码"
            width="120"
          >
          </el-table-column>
          <!-- 描述 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="msg"
            label="描述"
          >
          </el-table-column>
          <!-- 分组 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="group"
            label="分组"
            width="150"
          >
          </el-table-column>
          <!-- 操作 -->
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button
                @click="editStatusCode(scope.row)"
                type="text"
                size="small"
                >编辑</el-button
              >
              <el-button
                @click="showDeleteDialog(scope.$index)"
                type="text"
                size="small"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </template>
    </data-list>
    <!-- 删除状态码的弹窗 -->
    <el-dialog
      title="删除状态码"
      :visible.sync="deleteDialogVisible"
      width="30%"
    >
      <span>此操作不可撤销，确认删除此状态码吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteDialogVisible = false">取 消</el-button>
        <el-button type="danger" @click="deleteStatusCode">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑状态码的弹窗 -->
    <el-dialog
      title="编辑状态码"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="initEditDialog"
    >
      <el-form class="editForm" label-position="top" :model="newStatusInfo">
        <el-form-item label="状态码分组">
          <el-select
            v-model="newStatusInfo.group"
            placeholder="请选择分组"
            size="small"
            :disabled="newStatusInfo.group != '' && statusCode_now != ''"
            style="width: 100%"
          >
            <el-option
              v-for="item in groupList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态码列表">
          <el-table
            class="newStatusCode"
            :data="newStatusInfo.data"
            style="width: 100%"
          >
            <el-table-column prop="code" label="状态码">
              <template slot-scope="scope">
                <el-input
                  v-model="newStatusInfo.data[scope.$index].code"
                  size="mini"
                  @input="insertRow(scope.$index)"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="msg" label="描述">
              <template slot-scope="scope">
                <el-input
                  v-model="newStatusInfo.data[scope.$index].msg"
                  size="mini"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column
              prop="code"
              label="操作"
              v-if="statusCode_now == ''"
            >
              <template slot-scope="scope">
                <el-button
                  @click="deleteNewStatusCode(scope.$index)"
                  size="mini"
                  v-show="scope.$index != newStatusInfo.data.length - 1"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addStatusCode">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import navigationBox from "@/components/navigationBox.vue";
import dataList from "@/components/dataList.vue";

export default {
  name: "statusCodeList",
  components: {
    navigationBox,
    dataList,
  },
  data() {
    return {
      deleteDialogVisible: false,
      editDialogVisible: false,
      groupList: [
        {
          name: "用户模块",
        },
        {
          name: "登录模块",
        },
      ],
      nowGroup: "", // 当前分组
      statusCodeList_backup: [], // 进行状态码搜索之前备份的状态码信息，取消搜索之后直接赋给 statusCodeList 并清空
      statusCodeList: [
        {
          id: "1",
          code: "00000",
          msg: "成功",
          group: "用户模块",
        },
        {
          id: "2",
          code: "00002",
          msg: "失败",
          group: "用户模块",
        },
        {
          id: "3",
          code: "00003",
          msg: "权限不足",
          group: "用户模块",
        },
        {
          id: "4",
          code: "00004",
          msg: "密码错误",
          group: "登录模块",
        },
        {
          id: "5",
          code: "00005",
          msg: "用户名已存在",
          group: "登录模块",
        },
      ],
      newStatusInfo: {
        group: "",
        data: [
          {
            code: "",
            msg: "",
          },
        ],
      },
      statusCode_now: null, // 现在正在操作的状态码的行索引值
    };
  },
  mounted() {
    this.getGroupList();
    this.getStatusCodeInfo();
  },
  methods: {
    /**
     * 选择一个分组
     * @param {String} groupName 分组名
     */
    selectGroup(groupName) {
      this.nowGroup = groupName;
      if (groupName == "all") {
        this.statusCodeList = this.statusCodeList_backup;
        return;
      }
      // 保留当前选中分组的状态码
      let filteredList = [];
      this.statusCodeList_backup.forEach((statusCode) => {
        if (statusCode.group == groupName) {
          filteredList.push(statusCode);
        }
      });
      this.statusCodeList = filteredList;
    },
    /**
     * 新增一个分组
     * @param {String} groupName 分组名称
     */
    addGroup(groupName) {
      this.$axios
        .post({
          url: "/api_management/statusGrps/addGroup",
          params: {
            grpName: groupName,
            pid: this.$route.query.projectId,
          },
          formdata: true,
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            this.getGroupList();
          }
        });
    },
    /**
     * 编辑分组
     * @param {String} groupId 此分组原来的名字
     * @param {String} groupName 分组名称
     */
    editGroup(groupId, groupName) {
      this.$axios
        .post({
          url: "/api_management/statusGrps/updateGroup",
          params: {
            id: groupId,
            grpName: groupName,
          },
          formdata: true,
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            this.getGroupList();
          }
        });
    },
    /**
     * 删除分组
     * @param {String} groupId 分组名称
     */
    deleteGroup(groupId) {
      this.$axios
        .post({
          url: "/api_management/statusGrps/deleteGroup",
          params: {
            groupId: groupId,
          },
          formdata: true,
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            let index = null;
            this.groupList.forEach((group, i) => {
              if (group.id == groupId) {
                index = i;
              }
            });
            this.groupList.splice(index, 1);

            this.$refs.navigationBox.groupList_show.forEach((group, i) => {
              if (group.id == groupId) {
                index = i;
              }
            });
            this.$refs.navigationBox.groupList_show.splice(index, 1);
          }
        });
    },
    /**
     * 点击新建或编辑状态码的回调函数
     * @param {String} row 当前要编辑的行的信息
     */
    editStatusCode(row) {
      this.statusCode_now = row; // 用来判断是新建还是编辑，展示弹窗对应的模块
      if (row != "") {
        // 是编辑操作
        this.newStatusInfo.group = row.group;
        this.newStatusInfo.data[0].code = row.code;
        this.newStatusInfo.data[0].msg = row.msg;
      }
      this.editDialogVisible = true;
    },
    /**
     * 获取分组
     */
    getGroupList() {
      this.$axios
        .get({
          url: "/api_management/statusGrps/getAllByPid",
          params: {
            pid: this.$route.query.projectId,
          },
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            this.groupList = [];
            res.data.forEach((group) => {
              this.groupList.push({
                id: group.id,
                name: group.grpName,
              });
            });
            this.$refs.navigationBox.groupList_show = JSON.parse(
              JSON.stringify(this.groupList)
            );
          }
        });
    },
    /**
     * 获取状态码数据
     */
    getStatusCodeInfo() {
      this.$axios
        .get({
          url: "/api_management/status/queryAllInPrj",
          params: {
            projectId: this.$route.query.projectId,
          },
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            this.statusCodeList = [];
            res.data.forEach((status) => {
              this.statusCodeList.push({
                id: status.id,
                code: status.statusCode,
                msg: status.description,
              });
            });

            // 获得数据后同时进行进行备份
            this.statusCodeList_backup = JSON.parse(
              JSON.stringify(this.statusCodeList)
            );
          }
        });
    },
    /**
     * 根据关键词筛选状态码
     * @param {String} keyWord 要进行搜索的关键词
     */
    search(keyWord) {
      // 保留含有关键词的状态码
      let filteredList = [];
      this.statusCodeList_backup.forEach((codeInfo) => {
        if (codeInfo.code.includes(keyWord) || codeInfo.msg.includes(keyWord)) {
          filteredList.push(codeInfo);
        }
      });
      this.statusCodeList = filteredList;
    },
    /**
     * 修改状态码的信息
     */
    changeStatusCodeInfo() {
      // this.$axios.post({
      //   url:"/api_management/status/updateStatus",
      //   params:{
      //     id
      //   },
      //   formdata:true
      // })
    },
    /**
     * 点击删除状态码的回调函数
     * @param {Number} index 行索引
     */
    showDeleteDialog(index) {
      this.deleteDialogVisible = true;
      this.statusCode_now = index;
    },
    /**
     * 删除状态码
     */
    deleteStatusCode() {
      this.$axios
        .post({
          url: "/api_management/status/deleteStatus",
          params: {
            ids: `[${this.statusCodeList_backup[this.statusCode_now].id}]`,
          },
          formdata: true,
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            this.statusCodeList.splice(this.statusCode_now, 1);
            this.statusCodeList_backup.splice(this.statusCode_now, 1);
            this.deleteDialogVisible = false;
            this.statusCode_now = null;
          }
        });
    },
    /**
     * 删除待新建状态码列表的某一项
     * @param {Number} index 行索引
     */
    deleteNewStatusCode(index) {
      this.newStatusInfo.data.splice(index, 1);
    },
    /**
     * 在最后一行输入信息时新增一列
     * @param {Number} index 行索引
     */
    insertRow(index) {
      if (this.statusCode_now != "") {
        // 此时为编辑已有的状态码，不自动新增行
        return;
      }
      if (index == this.newStatusInfo.data.length - 1) {
        this.$set(this.newStatusInfo.data, this.newStatusInfo.data.length, {
          code: "",
          msg: "",
        });
      }
    },
    /**
     * 新增新编辑的状态码
     */
    addStatusCode() {
      let statuses = [];
      this.newStatusInfo.data.forEach((el) => {
        statuses.push({
          statusCode: el.code,
          description: el.msg,
        });
      });
      statuses.pop(); // 最后一个是空的，要删掉

      this.$axios
        .post({
          url: "/api_management//status/addStatus",
          params: {
            statuses: JSON.stringify(statuses),
            gid: this.newStatusInfo.group,
          },
          formdata: true,
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            this.statusCodeList = [];
            this.getStatusCodeInfo();
            this.statusCode_now = null;
            this.editDialogVisible = false;
          }
        });
    },
    /**
     * 关闭编辑状态码弹窗的时候清楚输入框的数据
     */
    initEditDialog() {
      this.newStatusInfo = {
        group: "",
        data: [
          {
            code: "",
            msg: "",
          },
        ],
      };
    },
  },
};
</script>

<style scoped>
* {
  transition: none;
}
.statusCodeList {
  height: calc(100vh - 120px);
  width: calc(100% - 30px);
  display: flex;
  margin: 17px 15px;
  box-sizing: border-box;
  border: 1px solid #d9d9d9;
  background-color: #ffffff;
}
.editForm /deep/ .el-form-item__label {
  line-height: 16px;
  padding-bottom: 8px;
  font-weight: bold;
}
.newStatusCode /deep/ td {
  padding: 2px 0 !important;
}
</style>