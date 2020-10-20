<template>
  <div>
    <!-- 侧边栏 -->
    <el-menu class="sideBar" :collapse="true">
      <div class="logo"></div>
      <el-menu-item class="sideBarItem" index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">主题颜色</span>
      </el-menu-item>
      <el-menu-item class="sideBarItem" index="2">
        <i class="el-icon-setting"></i>
        <span slot="title">设置</span>
      </el-menu-item>
      <el-menu-item class="sideBarItem" index="3">
        <i class="el-icon-s-operation"></i>
        <span slot="title">收起/展开</span>
      </el-menu-item>
    </el-menu>
    <!-- 顶部栏 -->
    <el-menu class="topBar" mode="horizontal">
      <div class="userBox"></div>
      <el-menu-item class="menuItem" index="1">概要</el-menu-item>
      <el-menu-item class="menuItem" index="2">API 管理</el-menu-item>
      <el-menu-item class="menuItem" index="3">API 测试</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "name",
  data() {
    return {};
  },
};
</script>

<style scoped>
.sideBar {
  height: 100%;
  width: 50px;
  position: fixed;
  left: 0;
  z-index: 10;
}
.logo {
  height: 50px;
  width: 50px;
  border-radius: 50%;
  background-image: url(~@/assets/images/logo.png);
  background-size: 50% 50%;
  background-repeat: no-repeat;
  background-position: center center;
}
.sideBarItem {
  padding: 0;
}
.sideBarItem /deep/ .el-tooltip {
  outline: none;
  text-align: center;
  padding: 0 !important;
}
.topBar {
  margin-left: 51px;
  height: 50px;
}
.menuItem {
  height: 50px;
  line-height: 50px;
}
</style>