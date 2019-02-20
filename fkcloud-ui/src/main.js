// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import comm from './assets/js/comm.js'
import list from './assets/js/List.js'
import './assets/css/egojiticon/iconfont.css'
import config from './assets/js/config'
import axios from 'axios'
import FkVueUtil from './assets/js/FkVueUtil'
Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(comm)
Vue.use(list)
Vue.prototype.$config = config
Vue.prototype.$axios = axios
Vue.prototype.$util = FkVueUtil

/* eslint-disable no-new */
// 使用钩子函数对路由进行权限跳转
let role = 'admin'
router.beforeEach((to, from, next) => {
  let token = FkVueUtil.cookie.getCookie(config.tokenKey)
  console.log('token:', token)
  if (!token && to.path !== '/login') {
    next('/login')
  } else if (to.meta.permission) {
    // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    role === 'admin' ? next() : next('/403')
  } else {
    next()
  }
})
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
