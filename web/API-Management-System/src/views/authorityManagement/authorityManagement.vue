<template>
  <div class="authorityManagement">
    <navigation-box
      @selectGroup="selectGroup"
      @addGroup="addGroup"
      @editGroup="editGroup"
      @deleteGroup="deleteGroup"
      :groupList="groupList"
      ref="navigationBox"
    >
      <span slot="buttonName">新建角色</span>
      <span slot="title">所有成员</span>
    </navigation-box>
    <data-list
      @search="search"
      @newSth="addMember"
      :totalNum="memberList.length"
    >
      <template v-slot:buttonName>新建状态码</template>
      <!-- 表格 -->
      <template v-slot:table>
        <el-table :data="memberList" border height="100%" max-height="100%">
          <!-- 账号 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="userName"
            label="账号"
            width="180"
          >
          </el-table-column>
          <!-- 角色 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="role"
            label="角色"
          >
          </el-table-column>
          <!-- 操作 -->
          <el-table-column label="操作" width="250">
            <template slot-scope="scope">
              <!-- 仅本人显示 -->
              <el-button
                @click="showQuitDialog"
                type="text"
                size="small"
                v-show="
                  scope.row.userName == $store.getters.getUserName &&
                  !scope.row.isAdministrator
                "
                >退出</el-button
              >
              <!-- 当操作者为管理员时，其他成员显示 -->
              <el-button
                @click="showDismissDialog(scope)"
                type="text"
                size="small"
                v-if="isAdministrator"
                v-show="scope.row.userName != $store.getters.getUserName"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </template>
    </data-list>
    <!-- 删除成员的弹窗 -->
    <el-dialog
      title="删除成员"
      :visible.sync="dismissDialogVisible"
      width="30%"
    >
      <span>删除后，该成员将无法查看此项目，是否继续？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dismissDialogVisible = false">取 消</el-button>
        <el-button type="warning" @click="dismiss">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 退出项目的弹窗 -->
    <el-dialog
      title="删除成员"
      :visible.sync="dismissDialogVisible"
      width="30%"
    >
      <span>退出后，将无法查看此项目，是否继续？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dismissDialogVisible = false">取 消</el-button>
        <el-button type="warning" @click="quit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import navigationBox from "@/components/navigationBox.vue";
import dataList from "@/components/dataList.vue";

export default {
  name: "authorityManagement",
  components: {
    navigationBox,
    dataList,
  },
  data() {
    return {
      isAdministrator: false, // 当前用户是否为管理员
      groupList: [
        {
          name: "前端开发人员",
        },
        {
          name: "后端开发人员",
        },
        {
          name: "软件测试人员",
        },
        {
          name: "设计师",
        },
      ],
      memberList: [
        {
          userName: "李俊贤",
          role: "后台开发人员",
          isAdministrator: true,
        },
        {
          userName: "李林飞",
          role: "前端开发人员",
          isAdministrator: false,
        },
        {
          userName: "黄博晓",
          role: "后台开发人员",
          isAdministrator: true,
        },
      ],
      memberList_backup: [],
      member_now: null, // 当前操作的成员
      dismissDialogVisible: false,
      quitDialogVisible: false,
    };
  },
  mounted() {
    this.getMemberList();
  },
  methods: {
    /**
     * 获取项目成员列表
     */
    getMemberList() {
      // todo 发起请求
      // 获得数据后同时进行进行备份
      this.memberList_backup = JSON.parse(JSON.stringify(this.memberList));
      // 检测当前用户是否为管理员
      this.memberList_backup.forEach((member) => {
        if (
          member.userName == this.$store.getters.getUserName &&
          member.isAdministrator
        ) {
          this.isAdministrator = true;
        }
      });
    },
    /**
     * 选择一个分组
     * @param {String} groupName 分组名
     */
    selectGroup(groupName) {
      this.nowGroup = groupName;
      if (groupName == "all") {
        this.memberList = this.memberList_backup;
        return;
      }
      // 保留当前选中分组的 api
      let filteredList = [];
      this.memberList_backup.forEach((memberInfo) => {
        if (memberInfo.group == groupName) {
          filteredList.push(memberInfo);
        }
      });
      this.memberList = filteredList;
    },
    /**
     * 新增一个分组
     * @param {String} groupName 分组名称
     */
    addGroup(groupName) {
      // todo：发起请求，增加分组
      this.groupList.push({
        name: groupName,
      });
      this.$refs.navigationBox.groupList_show.push({
        name: groupName,
      });
    },
    /**
     * 编辑分组
     * @param {String} oldName 此分组原来的名字
     * @param {String} groupName 分组名称
     */
    editGroup(oldName, groupName) {
      // todo：发起请求，更改分组名
      // 成功后：
      this.groupList.forEach((group) => {
        if (group.name == oldName) {
          group.name = groupName;
        }
      });
      this.$refs.navigationBox.groupList_show = JSON.parse(
        JSON.stringify(this.groupList)
      );
    },
    /**
     * 删除分组
     * @param {String} groupName 分组名称
     */
    deleteGroup(groupName) {
      // todo：发起请求，删除分组
      let index = null;
      this.groupList.forEach((group, i) => {
        if (group.name == groupName) {
          index = i;
        }
      });
      this.groupList.splice(index, 1);

      this.$refs.navigationBox.groupList_show.forEach((group, i) => {
        if (group.name == groupName) {
          index = i;
        }
      });
      this.$refs.navigationBox.groupList_show.splice(index, 1);
    },
    /**
     * 根据关键词筛选状态码
     * @param {String} keyWord 要进行搜索的关键词
     */
    search(keyWord) {
      // 保留含有关键词的状态码
      let filteredList = [];
      this.memberList_backup.forEach((member) => {
        if (member.userName.includes(keyWord)) {
          filteredList.push(member);
        }
      });
      this.memberList = filteredList;
    },
    /**
     * 添加成员
     */
    addMember() {
      // todo
    },
    /**
     * 点击退出的回调
     */
    showQuitDialog() {
      // setTimeout(() => {
      //   var backlen = history.length - 1;
      //   history.go(-backlen);
      //   // history.replaceState({}, null, "/home/overview");
      //   this.$router.push("/home/overview");
      // }, 0);

      this.quitDialogVisible = true;
    },
    /**
     * 自己退出项目
     */
    quit() {
      // todo 发起请求退出项目
      // 退出后跳转至首页
      this.$router.replace("/home/APImanagement");
    },
    /**
     * 点击删除成员的回调
     * @param {Object} rowInfo 该行的信息
     */
    showDismissDialog(rowInfo) {
      this.member_now = {
        index: rowInfo.$index,
        info: rowInfo.row,
      };
      this.dismissDialogVisible = true;
    },
    /**
     * 管理员移除成员
     */
    dismiss() {
      // todo 发起请求删除成员
      this.memberList.splice(this.member_now.index, 1);
      this.member_now = null;
      this.dismissDialogVisible = false;
    },
  },
  computed: {},
};
</script>

<style scoped>
* {
  transition: none;
}
.authorityManagement {
  height: calc(100vh - 120px);
  width: calc(100% - 30px);
  display: flex;
  margin: 17px 15px;
  box-sizing: border-box;
  border: 1px solid #d9d9d9;
  background-color: #ffffff;
}
.el-button {
  margin-left: 0;
  margin-right: 10px;
}
</style>