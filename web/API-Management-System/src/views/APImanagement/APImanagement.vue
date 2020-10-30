<template>
  <div class="APImanagement">
    <div class="topBar">
      <div class="funName">API 研发管理</div>
      <div class="createBox" @click="showNewProjectDialog">
        <div class="createButton">
          <div class="createProject">
            <i class="el-icon-plus"></i>
            <span>新建 API 项目</span>
          </div>
          <div class="createOption" @click.stop="controlTypeCard">
            <i class="el-icon-caret-bottom"></i>
            <ul class="selectList" v-show="showTypeCard">
              <li @click.stop="controlTypeCard">新建项目</li>
              <li @click.stop="controlTypeCard">新建分组</li>
            </ul>
          </div>
        </div>
      </div>
      <!-- 新建项目的弹出框 -->
      <new-project-card
        ref="newProjectCard"
        title="新建项目"
        @refreshPage="refreshPage"
      ></new-project-card>
    </div>

    <project-list :projectInfoList="projectInfoList"></project-list>
  </div>
</template>

<script>
import newProjectCard from "../../components/newProjectCard.vue";
import projectList from "./components/projectList.vue";

export default {
  name: "APImanagement",
  components: {
    projectList,
    newProjectCard,
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
     * 关闭新建项目的弹窗
     */
    showNewProjectDialog() {
      this.$refs.newProjectCard.showNewProjectCard();
    },
    /**
     * 重新获取数据
     */
    refreshPage() {
      // todo
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