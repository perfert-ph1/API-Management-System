<template>
  <div class="login">
    <div class="topBar">
      <div class="wrapper">
        <div class="logo"></div>
        <div class="SystemName">API 管理测试系统</div>
      </div>
    </div>
    <!-- 登录 -->
    <div class="formBox" v-show="showLoginForm">
      <div class="formTitle">登录</div>
      <div class="message">欢迎使用我们的系统......</div>
      <div class="formItem">
        <div class="type">您的账号</div>
        <el-input
          v-model="userName"
          placeholder="请输入账号"
          prefix-icon="el-icon-user"
          :clearable="true"
        />
      </div>
      <div class="formItem">
        <div class="type">密码</div>
        <el-input
          v-model="password"
          placeholder="请输入密码"
          prefix-icon="el-icon-lock"
          show-password
        />
      </div>
      <div class="button" @click="login">登录</div>
      <div class="link">
        <span>没有账号？</span><a @click="switchPage">立即注册</a>
      </div>
    </div>
    <!-- 注册 -->
    <div class="formBox" v-show="!showLoginForm">
      <div class="formTitle">注册</div>
      <div class="message">欢迎您注册！</div>
      <div class="formItem">
        <div class="type">您的用户名</div>
        <el-input
          v-model="userName"
          prefix-icon="el-icon-user"
          :clearable="true"
          @input="checkUserName"
        />
      </div>
      <div class="formItem">
        <div class="type">请设置登录密码</div>
        <el-input v-model="password" prefix-icon="el-icon-lock" show-password />
      </div>
      <div class="button" @click="register">注册</div>
      <div class="link">
        <span>已有账号？</span><a @click="switchPage">直接登录</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      showLoginForm: true,
      userName: "",
      password: "",
      ableToRequest: true, // 用来实现节流函数
      timer: null, // 用来保存定时器
    };
  },
  methods: {
    /**
     * 登录
     */
    login() {
      console.log("登录");
      this.$router.push("home/overview")
    },
    /**
     * 注册
     */
    register() {
      this.showLoginForm = true;
      console.log("注册");
    },
    /**
     * 转到注册或登录的页面
     */
    switchPage() {
      this.showLoginForm = !this.showLoginForm;
    },
    /**
     * 发起请求，判断用户名是否可用
     */
    judgeAvailability() {
      // 在这里发起网络请求
    },
    /**
     * 节流函数，每两秒请求一次
     */
    checkUserName() {
      this.timer = null;
      if (this.ableToRequest) {
        this.ableToRequest = false;
        this.judgeAvailability();
        console.log("发起请求");
        this.timer = setTimeout(() => {
          this.ableToRequest = true;
          this.judgeAvailability();
        }, 2000);
      }
    },
  },
};
</script>

<style scoped>
.login {
  min-width: 1000px;
}
.topBar {
  height: 50px;
  border-bottom: 1px solid #d9d9d9;
  text-align: center;
}
.wrapper {
  height: 50px;
  display: inline-block;
  line-height: 50px;
}
.logo {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  background-image: url(~@/assets/images/logo.png);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  display: inline-block;
  vertical-align: middle;
}
.SystemName {
  color: #5a5a5a;
  font-size: 30px;
  display: inline-block;
  vertical-align: middle;
}
.formBox {
  width: 400px;
  margin: 200px auto 0;
}
.formTitle {
  font-size: 26px;
  color: #409eff;
  font-weight: 800;
}
.message {
  font-size: 26px;
  color: #333333;
  font-weight: 800;
  margin-bottom: 30px;
}
.type {
  margin-top: 10px;
  margin-bottom: 10px;
  font-size: 16px;
  font-weight: 700;
}
.button {
  width: 100%;
  height: 40px;
  line-height: 40px;
  margin-top: 25px;
  color: white;
  font-size: 16px;
  text-align: center;
  background-color: #53a8ff;
  border-radius: 4px;
  cursor: pointer;
  user-select: none;
  letter-spacing: 3px;
}
.button:hover {
  background-color: #79bbff;
}
.button:active {
  background-color: #409eff;
}
.link {
  margin: 20px 0;
  font-size: 16px;
  text-align: right;
}
.link > a {
  color: #1890ff;
  text-decoration: none;
  cursor: pointer;
}
a:hover {
  text-decoration: underline;
}
</style>