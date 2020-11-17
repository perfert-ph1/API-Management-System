/**
 * @description 对 axios 进行二次封装
 */

import axios from 'axios';
import store from '@/store/store';

// 设置超时时间
axios.defaults.timeout = 10000;
// 设置请求头信息
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
// 设置基础 url
axios.defaults.baseURL = 'http://120.77.14.175:8080';
// // 跨域时是否需要使用凭证
// axios.defaults.withCredentials = true;

// 请求拦截器
axios.interceptors.request.use(
  config => {
    // 在请求头中添加 token
    if (config.url != '/api_management/user/login') {
      const token = localStorage.getItem('token');
      if (token != '') {
        config.headers.common['token'] = token;
      }

      config.headers['Authorization'] = token;
    }

    return config;
  },
  err => {
    return Promise.reject(err);
  }
);

// 响应拦截器
axios.interceptors.response.use(
  res => {
    if (res.config.url == `/api_management/user/login` && res.headers.token) {
      //如果是登录接口且登陆成功
      const token = res.headers.token;
      // const token = "QAQ"
      store.commit('setToken', {
        token: token,
      });
      localStorage.setItem('token', token);
    }
    return res;
  },
  err => {
    return Promise.reject(err);
  });

/**
 * get 方法，对应 get 请求
 * @param {String} url 请求的url地址
 * @param {Object} params 请求时携带的参数
 */
const get = ({ url, params = {} }) => {
  return new Promise((resolve, reject) => {
    axios.get(url, {
      params: params
    }).then(res => {
      resolve(res.data)
    }).catch(err => {
      reject(err.data)
    })
  })
}

/**
 * post方法，对应post请求
 * @param {String} url 请求的url地址
 * @param {Object} params 请求时携带的参数
 */
const post = ({ url, params = {}, formdata = false }) => {
  if (formdata) {
    const transform = (params) => {
      let ret = '';
      for (let it in params) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(params[it]) + '&';
      }
      return ret.slice(0, ret.length - 1);
    }
    params = transform(params);
  }

  return new Promise((resolve, reject) => {
    axios.post(url, params)
      .then(res => {
        resolve(res.data)
      })
      .catch(err => {
        reject(err.data)
      })
  })
}

export default {
  get,
  post,
};