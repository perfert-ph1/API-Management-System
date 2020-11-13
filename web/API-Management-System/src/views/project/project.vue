<template>
  <div class="project">
    <el-breadcrumb class="breadcrumb" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home/overview' }"
        >首页</el-breadcrumb-item
      >
      <el-breadcrumb-item :to="{ path: '/home/APImanagement' }">
        API 管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>{{ projectName }}</el-breadcrumb-item>
      <el-breadcrumb-item v-show="this.$route.name == 'newAPI'"
        >新建 API</el-breadcrumb-item
      >
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
.project {
  margin-top: 34px;
}
.project:before {
  content: "";
  display: table;
}
.breadcrumb {
  top: 51px;
  width: 100%;
  padding: 10px 15px;
  background: white;
  border-bottom: 1px solid #e5e5e5;
  position: fixed;
  z-index: 10;
}
</style>