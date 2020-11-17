<template>
  <div class="home">
    <!-- 侧边栏 -->
    <side-bar @controlMenu="controlMenu"></side-bar>
    <!-- 功能菜单 -->
    <fun-menu
      :hideFunMenu="hideFunMenu"
      :userInfo="userInfo"
      :isProjectList="isProjectList"
      @changeFunModule="changeFunModule"
      @changeProjectFunModule="changeProjectFunModule"
      style="z-index: 10"
    ></fun-menu>
    <!-- 顶部栏 -->
    <top-bar
      :hideFunMenu="hideFunMenu"
      :isProjectList="isProjectList"
      @changeFunModule="changeFunModule"
      style="z-index: 10"
    ></top-bar>
    <!-- 内容 -->
    <div class="content" :class="{ content_actived: !hideFunMenu }">
      <router-view :userName="userInfo.userName"></router-view>
    </div>
  </div>
</template>

<script>
import funMenu from "./components/funMenu.vue";
import sideBar from "./components/sideBar.vue";
import topBar from "./components/topBar.vue";

export default {
  name: "home",
  components: {
    topBar,
    sideBar,
    funMenu,
  },
  data() {
    return {
      userInfo: {
        id: "",
        userName: "",
        profilePhoto: require("@/assets/images/profilePhoto.png"),
      },
      hideFunMenu: true,
      fullWidth: document.body.clientWidth,
      isProjectList: true,
    };
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    /**
     * 获取用户的相关信息
     */
    getUserInfo() {
      this.$axios
        .get({
          url: "/api_management/user/getUserInfo",
        })
        .then((res) => {
          console.log(res);
          if (res.result == "200") {
            const data = res.data;
            this.userInfo = {
              id: data.id,
              userName: data.username,
              profilePhoto: data.headerUrl,
            };

            this.$store.commit("setUserName", {
              userName: this.userInfo.username,
            });
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
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
        this.$router.replace(`/home/${key}`);
      }
    },

    /**
     * 改变项目内功能模块
     * @param {String} key 模块名称
     */
    changeProjectFunModule(key) {
      if (this.$route.name != key) {
        this.$router.push({
          path: `/home/APImanagement/project/${key}`,
          query: this.$route.query,
        });
      }
    },
  },

  // 进入导航之前判断是否需要收起侧边栏
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      vm.hideFunMenu = to.name == "overview" ? true : false;
      vm.isProjectList = to.path.split("/")[3] == "project" ? false : true;
    });
  },

  // 导航更新之前判断是否需要收起侧边栏
  beforeRouteUpdate(to, from, next) {
    this.isProjectList = to.path.split("/")[3] == "project" ? false : true;
    this.hideFunMenu = to.name == "overview" ? true : false;
    next();
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