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
      }, {
        path: 'APImanagement/project',
        redirect: 'APImanagement/project/projectOverview',
      }, {
        path: 'APImanagement/project',
        name: 'project',
        component: () => import('@/views/project/project'),
        children: [{
          path: 'projectOverview',
          name: 'projectOverview',
          component: () => import('@/views/projectOverview/projectOverview')
        }, {
          path: 'APIList',
          name: 'APIList',
          component: () => import('@/views/APIList/APIList')
        }, {
          path: 'newAPI',
          name: 'newAPI',
          component: () => import('@/views/newAPI/newAPI')
        }, {
          path: 'APIQuickTest',
          name: 'APIQuickTest',
          component: () => import('@/views/APIQuickTest/APIQuickTest')
        }, {
          path: 'statusCodeList',
          name: 'statusCodeList',
          component: () => import('@/views/statusCodeList/statusCodeList')
        }, {
          path: 'projectDocumentList',
          name: 'projectDocumentList',
          component: () => import('@/views/projectDocumentList/projectDocumentList')
        }, {
          path: 'authorityManagement',
          name: 'authorityManagement',
          component: () => import('@/views/authorityManagement/authorityManagement')
        }, {
          path: 'projectSetting',
          name: 'projectSetting',
          component: () => import('@/views/projectSetting/projectSetting')
        }, {
          path: 'operationLog',
          name: 'operationLog',
          component: () => import('@/views/operationLog/operationLog')
        }]
      }]
    }
  ]
})
