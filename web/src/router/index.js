import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
let RouteList = [
  {
    path: '/',
    component: resolve => require(['~/views/Layout/App.vue'], resolve),
    meta: {
      title: '首页',
      keepAlive: false,
    },
    children: [
      {
        path: '/user',
        name: 'user',
        meta: {
          title: '用户列表',
          keepAlive: true
        },
        component: resolve => require(['~/views/system/user.vue'], resolve),
      },
      {
        path: '/auth',
        name: 'auth',
        meta: {
          title: '权限列表',
          keepAlive: false
        },
        component: resolve => require(['~/views/system/auth.vue'], resolve),
      },
      {
        path: '/onlineUser',
        name: 'onlineUser',
        meta: {
          title: '在线用户',
          keepAlive: false
        },
        component: resolve => require(['~/views/system/onlineUser.vue'], resolve),
      },
      {
        path: '/personal',
        name: 'Personal',
        meta: {
          title: '个人中心',
          keepAlive: true
        },
        component: resolve => require(['~/views/Personal/Index.vue'], resolve),
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    meta: {
      title: '后台登录',
      keepAlive: false
    },
    components: {
      blank: resolve => require(['~/views/Login/Login.vue'], resolve),
    }
  },

]




export default new Router({routes: RouteList})


