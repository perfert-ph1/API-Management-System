<template>
  <div class="projectOverview">
    <el-breadcrumb class="breadcrumb" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home/overview' }"
        >首页</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/home/APImanagement' }">
        API 管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>{{ projectName }}</el-breadcrumb-item>
    </el-breadcrumb>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "project",
  data() {
    return {
      projectId: null,
      projectName: "",
    };
  },
  mounted() {
    this.projectId = this.$route.query.projectId;
    this.projectName = this.$route.query.projectName;
  },
  methods: {},
};
</script>

<style scoped>
.breadcrumb {
  padding: 10px 15px;
  background: white;
  border-bottom: 1px solid #e5e5e5;
}
</style>