import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    }, {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login/login')
    }, {
      path: '/home',
      name: 'home',
      component: () => import('@/views/home/home'),
      children: [{
        path: 'overview',
        name: 'overview',
        component: () => import('@/views/overview/overview')
      }]
    }
  ]
})
