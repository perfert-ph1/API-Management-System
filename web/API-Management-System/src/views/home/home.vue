<template>
  <div class="home">
    <!-- 侧边栏 -->
    <side-bar @controlMenu="controlMenu"></side-bar>
    <!-- 功能菜单 -->
    <fun-menu :hideFunMenu="hideFunMenu" :userInfo="userInfo"></fun-menu>
    <!-- 顶部栏 -->
    <top-bar
      :hideFunMenu="hideFunMenu"
      @changeFunModule="changeFunModule"
    ></top-bar>
    <!-- 内容 -->
    <div class="content" :class="{ content_actived: !hideFunMenu }">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import funMenu from "./components/funMenu.vue";
import sideBar from "./components/sideBar.vue";
import topBar from "./components/topBar.vue";

export default {
  name: "name",
  components: {
    topBar,
    sideBar,
    funMenu,
  },
  data() {
    return {
      userInfo: {
        userName: "Lee",
        profilePhoto: require("@/assets/images/profilePhoto.png"),
      },
      hideFunMenu: true,
      fullWidth: document.body.clientWidth,
    };
  },
  mounted() {},
  methods: {
    /**
     * 控制功能列表的显示与隐藏
     */
    controlMenu() {
      this.hideFunMenu = !this.hideFunMenu;
    },

    /**
     * 改变功能模块
     * @param {String} key 模块名称
     */
    changeFunModule(key) {
      if (this.$route.name != key) {
        this.hideFunMenu = key == "overview" ? true : false; // 统计总览页面不显示功能列表
        this.$router.replace(key);
      }
    },
  },

  // 进入导航之前判断是否需要收起侧边栏
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      vm.hideFunMenu = to.name == "overview" ? true : false;
    });
  },

  // 导航更新之前判断是否需要收起侧边栏
  beforeRouteUpdate(to, from, next) {
    this.hideFunMenu = to.name == "overview" ? true : false;
    next();
  },

  beforeDestroy() {
    window.removeEventListener("resize", this.handleResize);
  },
};
</script>

<style scoped>
.home {
  min-width: 1080px;
}
.content {
  min-height: 100vh;
  margin-right: 0;
  margin-left: 51px;
  padding-top: 51px;
  box-sizing: border-box;
  transition: margin-left 0.5s;
  background-color: #f0f2f5;
}
.content_actived {
  margin-left: 251px;
}
</style>