import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/dashboard'
    },
    {
      path: '/Home',
      component: resolve => require(['../components/comms/Home.vue'], resolve),
      meta: { title: '首页' },
      children: [
        {
          path: '/images/index',
          component: resolve => require(['../components/page/admin/images/index.vue'], resolve),
          meta: { title: '应用市场' }
        },
        {
          path: '/container/index',
          component: resolve => require(['../components/page/admin/container/index.vue'], resolve),
          meta: { title: '我的应用' }
        },
        {
          path: '/upms/dic',
          component: resolve => require(['../components/page/upms/dic/index.vue'], resolve),
          meta: { title: '字典管理' }
        },
        {
          path: '/upms/office',
          component: resolve => require(['../components/page/upms/office/index.vue'], resolve),
          meta: { title: '机构管理' }
        },
        {
          path: '/ui/cluster',
          component: resolve => require(['../components/page/plugins/Index.vue'], resolve),
          meta: { title: '服务管理' }
        },
        {
          path: '/action',
          component: resolve => require(['../components/page/Action.vue'], resolve),
          meta: { title: '服务管理' }
        },
        {
          path: '/dashboard',
          component: resolve => require(['../components/page/Dashboard.vue'], resolve),
          meta: { title: '系统首页' }
        },
        {
          path: '/works/edit',
          component: resolve => require(['../components/page/works/Edit.vue'], resolve),
          meta: { title: 'work_edit' }
        },
        {
          path: '/works/run',
          component: resolve => require(['../components/page/works/Run.vue'], resolve),
          meta: { title: 'work_run' }
        }
      ]
    },
    {
      path: '/login',
      component: resolve => require(['../components/page/Login.vue'], resolve)
    },
    {
      path: '/404',
      component: resolve => require(['../components/page/404.vue'], resolve)
    }
  ]
})
