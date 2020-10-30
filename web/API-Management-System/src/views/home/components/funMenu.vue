<template>
  <div class="funMenuBox" :class="{ funMenuBox_close: hideFunMenu }">
    <div class="funMenu">
      <!-- 用户信息 -->
      <div class="userInfo">
        <el-avatar
          class="profilePhoto"
          size="large"
          :src="userInfo.profilePhoto"
        ></el-avatar>
        <span class="userName">{{ userInfo.userName }}</span>
      </div>
      <!-- 项目列表的菜单 -->
      <el-menu
        class="meun"
        :style="{ display: isProjectList ? 'block' : 'none' }"
        @select="changeFunModule"
        :default-openeds="['function', 'dataAnalysis']"
        :default-active="nowModule"
      >
        <el-submenu index="function">
          <template slot="title">功能</template>
          <el-menu-item index="APImanagement">
            <i class="el-icon-s-management"></i>
            <span>API 研发管理</span></el-menu-item
          >
          <el-menu-item index="APItest">
            <i class="el-icon-s-promotion"></i>
            <span>API 快速测试</span></el-menu-item
          >
        </el-submenu>
        <el-submenu index="dataAnalysis">
          <template slot="title">数据分析</template>
          <el-menu-item index="analysisReport">
            <i class="el-icon-s-data"></i>
            <span>分析报表</span>
          </el-menu-item>
        </el-submenu>
      </el-menu>
      <!-- 项目详情的菜单 -->
      <el-menu
        class="meun"
        :style="{ display: isProjectList ? 'none' : 'block' }"
        @select="changeProjectFunModule"
        :default-openeds="['project', 'management']"
        :default-active="nowProjectModule"
      >
        <el-submenu index="project">
          <template slot="title">项目</template>
          <el-menu-item index="projectOverview">
            <i class="el-icon-s-marketing"></i>
            <span>项目概况</span></el-menu-item
          >
          <el-menu-item index="API">
            <i class="el-icon-s-ticket"></i>
            <span>API</span></el-menu-item
          >
          <el-menu-item index="statusCode">
            <i class="el-icon-s-order"></i>
            <span>状态码文档</span></el-menu-item
          >
          <el-menu-item index="projectDocument">
            <i class="el-icon-s-claim"></i>
            <span>项目文档</span></el-menu-item
          >
        </el-submenu>
        <el-submenu index="management">
          <template slot="title">管理</template>
          <el-menu-item index="authorityManagement">
            <i class="el-icon-s-custom"></i>
            <span>权限管理</span>
          </el-menu-item>
          <el-menu-item index="projectSetting">
            <i class="el-icon-s-tools"></i>
            <span>项目设置</span>
          </el-menu-item>
          <el-menu-item index="journal">
            <i class="el-icon-s-platform"></i>
            <span>日志</span>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {
  name: "funMenu",
  props: {
    hideFunMenu: Boolean,
    userInfo: Object,
    isProjectList: Boolean,
  },
  data() {
    return {};
  },
  methods: {
    /**
     * 通知父组件改变功能模块，跳转到对应页面
     * @param {String} key 模块名称
     */
    changeFunModule(key) {
      this.$emit("changeFunModule", key);
    },

    /**
     * 通知父组件改变项目内功能模块
     * @param {String} key 模块名称
     */
    changeProjectFunModule(key) {
      this.$emit("changeProjectFunModule", key);
    },
  },

  computed: {
    nowModule() {
      return this.$route.path.split("/")[2];
    },
    nowProjectModule() {
      return this.$route.name;
    },
  },
};
</script>

<style scoped>
.funMenu {
  background: white;
  user-select: none;
}
.funMenuBox {
  height: 100%;
  width: 200px;
  overflow: hidden;
  position: fixed;
  left: 51px;
  top: 0;
  box-sizing: border-box;
  border-right: 1px solid #e6e6e6;
  transition: all 0.5s;
}
.funMenuBox_close {
  width: 0;
}
.funMenu {
  height: 100%;
  width: 200px;
}
.userInfo {
  height: 50px;
  width: 100%;
  line-height: 50px;
  border-bottom: 1px solid #e6e6e6;
  padding-left: 12px;
}
.profilePhoto {
  vertical-align: middle;
}
.userName {
  line-height: 50px;
  color: #333333;
  font-size: 30px;
  vertical-align: middle;
  margin-left: 5px;
}
.menu {
  /* width: auto !important; */
}
</style>