<template>
  <el-menu class="sideBar" :collapse="true">
    <div class="logoBox">
      <div class="logo"></div>
    </div>
    <el-menu-item class="sideBarItem" index="1" disabled>
      <i class="el-icon-menu"></i>
      <span slot="title">主题颜色</span>
    </el-menu-item>
    <el-menu-item class="sideBarItem" index="2" disabled>
      <i class="el-icon-setting"></i>
      <span slot="title">设置</span>
    </el-menu-item>
    <el-menu-item class="sideBarItem switch" index="3" @click="controlMenu">
      <i class="el-icon-s-operation"></i>
      <span slot="title">收起/展开</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "sideBar",
  data() {
    return {};
  },
  methods: {
    /**
     * 通知父组件控制功能列表的显示与隐藏
     */
    controlMenu() {
      this.$emit("controlMenu");
    },
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
.logoBox {
  height: 50px;
  width: 50px;
  border-bottom: 1px solid #e6e6e6;
}
.logo {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-image: url("~@/assets/images/logo.png");
  background-size: 50% 50%;
  background-repeat: no-repeat;
  background-position: center center;
}
.sideBarItem {
  padding: 0 !important;
}
.sideBarItem /deep/ .el-tooltip {
  outline: none;
  text-align: center;
  padding: 0 !important;
}
.switch {
  width: 50px;
  position: fixed;
  bottom: 0;
}
</style>