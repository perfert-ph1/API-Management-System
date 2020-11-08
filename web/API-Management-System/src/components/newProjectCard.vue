<template>
  <el-dialog :title="title" :visible.sync="showNewProjectDialog" width="600px">
    <el-form :model="newProjectInfo" :hide-required-asterisk="true">
      <el-form-item label="项目名称">
        <el-input v-model="newProjectInfo.name" :clearable="true"></el-input>
      </el-form-item>
      <el-form-item label="项目类型">
        <el-select
          v-model="newProjectInfo.type"
          placeholder="请选择项目类型"
          style="width: 100%"
        >
          <el-option value="Web">Web</el-option>
          <el-option value="App">App</el-option>
          <el-option value="PC">PC</el-option>
          <el-option value="硬件互联网">硬件互联网</el-option>
          <el-option value="其他">其他</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="版本号">
        <el-input v-model="newProjectInfo.version" :clearable="true"></el-input>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="newProjectInfo.remarks" :clearable="true"></el-input>
      </el-form-item>
      <el-form-item label="项目成员" v-show="newProject">
        <el-select
          style="width: 100%"
          v-model="newProjectInfo.members"
          multiple
          remote
          :remote-method="searchUser"
          filterable
          allow-create
          default-first-option
          placeholder="请输入其他成员的用户名"
          :loading="isSearchingUser"
          loading-text="正在搜索"
          no-match-text="请检查用户名是否存在"
        >
          <el-option
            v-for="user in searchResult"
            :key="user.userName"
            :value="user.userName"
          >
            <img :src="user.profilePhoto" alt="用户头像" />
            <span>{{ user.userName }}</span>
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="showNewProjectDialog = false">取 消</el-button>
      <el-button type="primary" @click="submitProjectInfo">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: "newProjectCard",
  props: {
    title: String,
    newProject: {
      type: Boolean,
      default: true,
    },
    projectInfo: Object,
  },
  data() {
    return {
      showNewProjectDialog: false,
      isSearchingUser: false, // 选择成员时，搜索内容时展示加载动画
      newProjectInfo: {
        name: "",
        type: "",
        version: "",
        remarks: "",
        members: [],
      },
      searchResult: [
        // 搜索成员时返回的结果
        {
          userName: "Psychic",
          profilePhoto: "~@/assets/iamges/profilePhoto.png",
        },
      ],
    };
  },
  mounted() {},
  methods: {
    /**
     * 搜索用户
     */
    searchUser() {
      this.isSearchingUser = true;
    },
    /**
     * 显示新建项目的弹窗
     */
    showNewProjectCard() {
      this.showNewProjectDialog = true;
      for (const key in this.projectInfo) {
        this.newProjectInfo[key] = this.projectInfo[key];
      }
    },
    /**
     * 提交项目基本信息，关闭新建项目的弹窗
     */
    submitProjectInfo() {
      this.showNewProjectDialog = false;
      // todo：发起请求
      // 通知父页面重新获取数据
      this.$emit("refreshPage");
    },
  },
};
</script>

<style scoped>
.el-form-item{
margin-bottom: 5px;
}
</style>