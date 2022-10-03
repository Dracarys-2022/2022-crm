import Vue from 'vue'
import App from './App.vue'
import {initRouter} from './router'
import './theme/index.less'
import Antd from 'ant-design-vue'
import Viser from 'viser-vue'
import '@/mock'
import store from './store'
import 'animate.css/source/animate.css'
import Plugins from '@/plugins'
import {initI18n} from '@/utils/i18n'
import bootstrap from '@/bootstrap'
import 'moment/locale/zh-cn'
import elementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/display.css'
import axios from 'axios'

const router = initRouter(store.state.setting.asyncRoutes)
const i18n = initI18n('CN', 'US')
Vue.use(elementUI)
Vue.prototype.$confirm = elementUI.MessageBox.confirm
Vue.use(Antd)
Vue.config.productionTip = false
Vue.use(Viser)
Vue.use(Plugins)
axios.defaults.withCredentials=true
bootstrap({router, store, i18n, message: Vue.prototype.$message})
// 添加请求拦截器
axios.interceptors.request.use(config => {
  // 在发送请求之前做些什么
  //判断是否存在token，如果存在将每个页面header都添加token
  //if中的值为登陆时存入vuex中的token值
  //如果存在token值，将token放入请求头‘Authorization’中
  if(localStorage.getItem('access-admin')!==null){
  config.headers.common['Authorization']=localStorage.getItem('access-admin')
  }
  
  return config;
  }, error => {
  // 对请求错误做些什么
  return Promise.reject(error);
  });
new Vue({
  router,
  store,
  i18n,
  render: h => h(App),
}).$mount('#app')
