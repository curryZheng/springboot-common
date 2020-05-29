import Vue from 'vue'
import store from './store'
import ElementUI from 'element-ui'
import './theme/index.css'
import './assets/css/font-awesome.min.css'
import './assets/css/style.css'
import router from './router'
import Config from './config'
import Function from './function'
import Vant from 'vant';
import 'vant/lib/index.css'
import App from './App.vue'
import VueAxios from 'vue-axios'
import axios from './axios/axios'
import Utils from './common/ygxcUtils.js';
import enumData from './common/enumData.js';

Vue.prototype.$Config = Config
Vue.prototype.$Func = Function
Vue.prototype.$utils=Utils;
Vue.prototype.$enumData=enumData;
Vue.use(Vant);
// 注册全局axios
Vue.use(VueAxios,axios)
Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  window.document.title = to.meta.title?to.meta.title+'-'+Config.siteName:Config.siteName;
  console.log(to.path)
  if(to.path == '/mobileImport/'||to.path == '/mobileImporttest/'){
    next();
   }else{
      if (!sessionStorage.getItem(Config.tokenKey) && to.path != '/login') {
        next({path: '/login'});
    
      } else {
        next();
      }
   }

});
router.afterEach(transition => {

});


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
