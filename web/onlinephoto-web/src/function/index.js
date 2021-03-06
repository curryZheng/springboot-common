import enumData from '../common/enumData';
import ygxcUtils from '../common/ygxcUtils';

export default {

  setLoginUser:data=> {
    sessionStorage.setItem('user', JSON.stringify(data))
  },
  getLoginUser: function () {
    return JSON.parse(sessionStorage.getItem('user'))
  },
  
  enumData:()=> {
    return enumData;
  },
  ygxcUtils:()=> {
    return ygxcUtils;
  },
  hasValInArrayObj: function (arr, key, val) {
    for (let i = 0; i < arr.length; i++) {
      if (arr[i][key] == val)
        return i;
    }
    return -1;
  }
}
