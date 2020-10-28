<template>
  <div class="APImanagement">
    <div class="topBar">
      <div class="funName">API 研发管理</div>
      <div class="createBox" @click="dialogFormVisible = true">
        <div class="createButton">
          <div class="createProject">
            <i class="el-icon-plus"></i>
            <span>新建 API 项目</span>
          </div>
          <div class="createOption" @click="controlTypeCard">
            <i class="el-icon-caret-bottom"></i>
            <ul class="selectList" v-show="showTypeCard">
              <li @click.stop="controlTypeCard">新建项目</li>
              <li @click.stop="controlTypeCard">新建分组</li>
            </ul>
          </div>
        </div>
      </div>
      <!-- 新建项目的弹出框 -->
      <el-dialog title="新建项目" :visible.sync="dialogFormVisible">
        <el-form :model="newProjectInfo" :hide-required-asterisk="true">
          <el-form-item label="项目名称">
            <el-input
              v-model="newProjectInfo.name"
              :clearable="true"
            ></el-input>
          </el-form-item>
          <el-form-item label="项目类型">
            <el-select
              v-model="newProjectInfo.region"
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
            <el-input
              v-model="newProjectInfo.version"
              :clearable="true"
            ></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              v-model="newProjectInfo.remarks"
              :clearable="true"
            ></el-input>
          </el-form-item>
          <el-form-item label="项目成员">
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
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">
            确 定
          </el-button>
        </div>
      </el-dialog>
    </div>

    <project-list :projectInfoList="projectInfoList"></project-list>
  </div>
</template>

<script>
import projectList from "./components/projectList.vue";

export default {
  name: "APImanagement",
  components: {
    projectList,
  },
  data() {
    return {
      showTypeCard: false,
      projectInfoList: [
        {
          id: "0",
          name: "API-management-system",
          version: "V 1.0.2",
          remarks: "API 管理测试系统",
          type: "Web",
          lastUpdateTime: "2020.10.23 08:43",
        },
        {
          id: "1",
          name: "wishingWall",
          version: "V 1.2.0",
          remarks:
            "许愿墙啊许愿墙啊许愿墙啊许愿墙啊许愿墙啊许愿墙啊许愿墙啊许愿墙",
          type: "Web",
          lastUpdateTime: "2020.10.23 08:43",
        },
      ],
      dialogFormVisible: false,
      newProjectInfo: {
        name: "",
        type: "",
        version: "",
        remarks: "",
        members: [],
      },
      isSearchingUser: false, // 选择成员时，搜索内容时展示加载动画
      searchResult: [
        {
          userName: "Psychic",
          profilePhoto: "~@/assets/iamges/profilePhoto.png",
        },
      ], // 搜索成员时返回的结果
    };
  },
  methods: {
    /**
     * 控制新建类型菜单的显示与隐藏
     */
    controlTypeCard() {
      this.showTypeCard = !this.showTypeCard;
    },
    /**
     * 搜索用户
     */
    searchUser() {
      this.isSearchingUser = true;
    },
  },
};
</script>

<style scoped>
.topBar {
  padding: 20px;
  background-color: white;
  box-shadow: 2px 3px 12px #dbdbdb;
  user-select: none;
}
.funName {
  margin-bottom: 15px;
  font-size: 18px;
  font-weight: 700;
  color: #333333;
}
.createBox {
  width: 100%;
  font-size: 0;
}
.createButton {
  display: inline-block;
  color: #ffffff;
  border-radius: 3px;
  cursor: pointer;
  transition: all 0.2s;
  background-color: #3c95ee;
}
.createProject {
  height: 26px;
  line-height: 26px;
  padding: 3px 10px 3px 7px;
  border-radius: 3px 0 0 3px;
  display: inline-block;
  vertical-align: middle;
  transition: all 0.2s;
}
.createProject:hover {
  background-color: #61b0ff;
}
.createProject:active {
  background-color: #499df1;
}
.createProject > span,
.createProject > i {
  font-size: 16px;
}
.createProject > span {
  margin-left: 3px;
}
.createOption {
  width: 36px;
  line-height: 30px;
  display: inline-block;
  font-size: 22px;
  color: #b4d3cc;
  text-align: center;
  background-color: #158aff;
  vertical-align: middle;
  transition: all 0.2s;
  position: relative;
}
.createOption:hover {
  background-color: #0080ff;
}
.createOption:active {
  background-color: #0176eb;
}
.selectList {
  width: 80px;
  position: absolute;
  top: 32px;
  right: 0;
  padding: 0;
  margin: 5px 0 0 0;
  font-size: 10px;
  list-style-type: none;
  background-color: white;
  border: 1px solid #dddddd;
  border-radius: 5px;
  cursor: default;
  color: #4b4b4b;
  box-shadow: 4px 4px 10px #d1d1d1;
}
.selectList > li {
  line-height: 18px;
  margin: 10px 15px;
  cursor: pointer;
  border-bottom: 1px solid #ffffff;
}
.selectList > li:hover {
  border-bottom: 1px solid #1890ff;
  color: #1890ff;
}
.el-form-item {
  margin-bottom: 0 !important;
}
</style>