import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'
import qs from 'qs'
import baseConfig from '../config'
axios.defaults.baseURL =baseConfig.apiUrl
axios.defaults.timeout = 60000


axios.interceptors.request.use(config => {
  const token = sessionStorage.getItem('Authorization')
  //统一添加token
   
  axios.defaults.headers.common['token'] = token;
  config.url=  baseConfig.apiUrl+config.url
  if (config.method === 'get') {
    config.paramsSerializer = function(params) {
      return qs.stringify(params, { indices: false })
    };
  }
  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(res => {
 
  const status = Number(res.status) || 200
  if (status !== 200) {
    Message({
      message: status,
      type: 'error'
    })
    return Promise.reject(new Error(res.data))
  }
  console.log("res.data.code"+res.data.code)
  const code = Number(res.data.code) || 0
  if (code !== 0) {
    Message({
      message: res.data.info,
      type: 'error'
    })
    return Promise.reject(new Error(res.data.info))
  }
  return res
}, error => {
  console.log(error.response)

  const data = error.response.data
  if (error.response.status === 401&&data == null) {

      router.push({ path: '/login' })
    
      Message({
        message: "请先登录",
        type: 'error'
      })
   
  } else if (data != null && data.info != null) {
    Message({
      message: data.info,
      type: 'error'
    })
  } else {
    Message({
      message: error,
      type: 'error'
    })
  }
  return Promise.reject(error)
})

export default axios
