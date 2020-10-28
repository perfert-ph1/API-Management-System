import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

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
      }, {
        path: 'APImanagement',
        name: 'APImanagement',
        component: () => import('@/views/APImanagement/APImanagement')
      }, {
        path: 'APItest',
        name: 'APItest',
        component: () => import('@/views/APItest/APItest')
      }, {
        path: 'analysisReport',
        name: 'analysisReport',
        component: () => import('@/views/analysisReport/analysisReport')
      }]
    }
  ]
})
