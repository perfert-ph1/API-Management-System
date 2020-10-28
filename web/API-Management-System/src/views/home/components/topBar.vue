<template>
  <el-menu
    class="topBar"
    :class="{ topBar_actived: !hideFunMenu }"
    :default-active="this.$route.name"
    @select="changeFunModule"
    mode="horizontal"
  >
    <div class="userBox"></div>
    <el-menu-item class="menuItem" index="overview">总览</el-menu-item>
    <el-menu-item class="menuItem" index="APImanagement">API 管理</el-menu-item>
    <el-menu-item class="menuItem" index="APItest">API 测试</el-menu-item>
    <el-menu-item class="menuItem" index="analysisReport"
      >分析报表</el-menu-item
    >
  </el-menu>
</template>

<script>
export default {
  name: "topBar",
  props: {
    hideFunMenu: Boolean,
  },
  data() {
    return {};
  },
  mounted() {},
  methods: {
    /**
     * 通知父组件改变功能模块
     * @param {String} key 模块名称
     */
    changeFunModule(key) {
      this.$emit("changeFunModule", key);
    },
  },
};
</script>

<style scoped>
.topBar {
  height: 50px;
  width: 100%;
  position: fixed;
  top: 0;
  margin-left: 51px;
  /* transform-origin: 0% center; */
  transition: all 0.5s;
}
.topBar_actived {
  /* transform: translateX(200px); */
  margin-left: 251px;
}
.menuItem {
  height: 50px;
  line-height: 50px;
  user-select: none;
}
</style>