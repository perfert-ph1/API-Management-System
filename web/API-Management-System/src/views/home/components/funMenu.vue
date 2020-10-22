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
      <!-- 菜单 -->
      <el-menu class="meun" @select="changeFunModule">
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
    </div>
  </div>
</template>

<script>
export default {
  name: "funMenu",
  props: {
    hideFunMenu: Boolean,
    userInfo: Object,
  },
  data() {
    return {};
  },
  methods: {
    /**
     * 改变功能模块，跳转到对应页面
     * @param {String} key 模块名称
     */
    changeFunModule(key) {
      this.$router.replace(key);
    },
  },
};
</script>

<style scoped>
.funMenu {
  background: white;
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
  /* transform-origin: 0% center; */
  transition: all 0.5s;
}
.funMenuBox_close {
  /* transform: scaleX(0); */
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
</style>