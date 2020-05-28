
//开发环境
const devUrl = 'http://192.168.3.3:5130';
//生产环境
const proUrl = 'http://apiUrl.com';


export default {
  apiUrl : __DEV__ ? devUrl : proUrl,
  apiPrefix : "api",

}

