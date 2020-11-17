const mutations = {
  // 设置用户名
  setUserName(state, payload) {
    state.userName = payload.userName;
  },
  // 设置用户 token
  setToken(state, payload) {
    state.token = payload.token;
  }
}

export default mutations