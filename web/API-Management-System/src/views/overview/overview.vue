<template>
  <div class="overview">
    <div class="massage">{{ massage }}</div>
    <el-collapse class="menu" v-model="activeNames">
      <el-collapse-item class="item" title="应用" name="fun">
        <div class="fun">
          <div class="funBox" @click="changeFunModule('APImanagement')">
            <div class="funName_en">API Management</div>
            <div class="funName_ch">API 研发管理</div>
          </div>
          <div class="funBox" @click="changeFunModule('APItest')">
            <div class="funName_en">API Test</div>
            <div class="funName_ch">API 测试</div>
          </div>
          <div class="funBox" @click="changeFunModule('AnalysisReport')">
            <div class="funName_en">Analysis Report</div>
            <div class="funName_ch">分析报表</div>
          </div>
        </div>
      </el-collapse-item>
      <el-collapse-item class="item" title="我的项目" name="project">
        <div
          class="projectBox"
          v-for="project in projectList"
          :key="project.name"
        >
          <div class="point"></div>
          <div class="projectName">{{ project.name }}</div>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
export default {
  name: "overview",
  data() {
    return {
      userName: "",
      massage: "",
      activeNames: ["fun", "project"],
      projectList: [
        {
          name: "API-management-system",
        },
        {
          name: "wishingWall",
        },
        {
          name: "homework",
        },
      ],
    };
  },
  mounted() {
    this.userName = this.$store.getters.getUserName;
    this.getMassage();
  },
  methods: {
    /**
     * 根据当前时间展示不同的欢迎语
     */
    getMassage() {
      let time = new Date().toString().split(" ")[4].split(":")[0];
      let msg = "";
      if (time < 5 || time >= 22) {
        msg = "夜深了，早点休息吧";
      } else if (time > 5 && time <= 9) {
        msg = "早上好，开始新的一天吧！";
      } else if (time > 9 && time <= 11) {
        msg = "上午好，记得适当休息哦";
      } else if (time > 11 && time <= 14) {
        msg = "中午好，休息一会儿吧";
      } else if (time > 14 && time <= 19) {
        msg = "下午好，欢迎回来，一起努力吧";
      } else {
        msg = "晚上好，记得适当休息哦~";
      }
      this.massage = `${this.userName}，${msg}`;
    },
    /**
     * 跳转到对应功能的页面
     * @param {String} funName 功能名称（与路由同名）
     */
    changeFunModule(funName) {
      this.$router.replace(funName);
    },
  },
};
</script>

<style scoped>
.massage {
  height: 90px;
  width: 100%;
  line-height: 90px;
  padding: 0 20px;
  box-sizing: border-box;
  font-size: 24px;
  background-color: white;
}
.menu {
  user-select: none;
}
.item {
  margin-bottom: 10px;
  background-color: white;
}
.item /deep/ .el-collapse-item__header {
  padding: 0 20px;
  font-size: 14px;
  color: #999999;
}
.fun {
  width: 100%;
  padding: 25px 20px 0;
  border-top: 1px solid #e6e6e6;
}
.funBox {
  height: 100%;
  min-width: 190px;
  display: inline-block;
  margin-right: 15px;
  padding: 15px;
  border-radius: 3px;
  color: #ffffff;
  cursor: pointer;
  user-select: none;
  transition: all 0.2s;
}
.funBox:hover {
  box-shadow: 3px 3px 8px #999999;
}
.funBox:nth-child(1) {
  background-image: linear-gradient(left, #e7b468, #e6a23c);
}
.funBox:nth-child(2) {
  background-image: linear-gradient(left, #f38585, #f56c6c);
}
.funBox:nth-child(3) {
  background-image: linear-gradient(left, #81c261, #67c23a);
}
.funName_en {
  letter-spacing: 1px;
  font-size: 18px;
  font-weight: bold;
}
.funName_ch {
  font-size: 16px;
}
.projectBox {
  width: 100%;
  margin: 5px 0;
  padding: 0 20px;
  cursor: pointer;
  transition: all 0.2s;
}
.projectBox:hover {
  background-color: #dfdfdf;
}
.projectBox:active {
  background-color: #cecece;
}
.point {
  height: 6px;
  width: 6px;
  border-radius: 50%;
  display: inline-block;
  vertical-align: middle;
  background-color: #53a8ff;
}
.projectName {
  font-size: 16px;
  font-weight: 400;
  display: inline-block;
  vertical-align: middle;
  margin-left: 10px;
  color: #3f3f3f;
}
</style>