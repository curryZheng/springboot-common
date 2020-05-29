/**
 * 工具方法
 */
const ygxcUtils = {
 
  /**
   *
   * 时间格式化
   */
  timeformat: function(datetime) {
    if (datetime == null && datetime == undefined) {
      return ''
    }
    var year = datetime[0] // 获取完整的年份(4位,1970)
    var month = datetime[1] // 获取月份(0-11,0代表1月,用的时候记得加上1)
    var date = datetime[2] // 获取日(1-31)
    var time = datetime[3] // 获取时间(从1970.1.1开始的毫秒数)
    var hours = datetime[4] // 获取小时数(0-23)
    var miu = (datetime[5] === undefined ? '0' : datetime[5]) // 获取分钟数(0-59)
    return year + '-' + (month >= 10 ? month : '0' + month) + '-' + (date >= 10 ? date : '0' + date) + 'T' + (time >= 10 ? time : '0' + time) + ':' + (hours >= 10 ? hours : '0' + hours) + ':' + (miu >= 10 ? miu : '0' + miu)+"Z"
  },

  timeformats: function(datetime) {
    if (datetime == null && datetime == undefined) {
      return ''
    }
    var year = datetime[0] // 获取完整的年份(4位,1970)
    var month = datetime[1] // 获取月份(0-11,0代表1月,用的时候记得加上1)
    var date = datetime[2] // 获取日(1-31)
    var time = datetime[3] // 获取时间(从1970.1.1开始的毫秒数)
    var hours = datetime[4] // 获取小时数(0-23)
    var miu = (datetime[5] === undefined ? '0' : datetime[5]) // 获取分钟数(0-59)
    return year + '/' + (month >= 10 ? month : '0' + month) + '/' + (date >= 10 ? date : '0' + date) + '   ' + (time >= 10 ? time : '0' + time) + ':' + (hours >= 10 ? hours : '0' + hours) + ':' + (miu >= 10 ? miu : '0' + miu)
  },
  /**
   *
   * 时间转换字符串
   */
  timeConvString: function(newTime) {
    if (newTime == null && newTime == undefined) {
      return ''
    }
    let str = ''
    const date = new Date(newTime)
    const year = date.getFullYear() + '-'
    const month = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
    const data = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate())
    const getHours = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':'
    const getMinutes = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':'
    const getSeconds = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds())
    str += year + month + data + 'T' + getHours + getMinutes + getSeconds
    // console.log(typeof(str));
    return newTime == null ? '' : str
  },
  /**
	 * 获得本周的开始时间
	 * 
	 * @returns
	 */
	getStartDayOfWeek: function() {
    var now = new Date(); // 当前日期
    var nowDayOfWeek = now.getDay(); // 今天本周的第几天
    var nowDay = now.getDate(); // 当前日
    var nowMonth = now.getMonth(); // 当前月
    var nowYear = now.getYear(); // 当前年
    nowYear += (nowYear < 2000) ? 1900 : 0;

    var day = nowDayOfWeek || 7;
		return new Date();  
	},
	/**
	 * 获得本周的结束时间
	 * 
	 * @returns
	 */
	getEndDayOfWeek: function() {
    var now = new Date(); // 当前日期
    var nowDayOfWeek = now.getDay(); // 今天本周的第几天
    var nowDay = now.getDate(); // 当前日
    var nowMonth = now.getMonth(); // 当前月
    var nowYear = now.getYear(); // 当前年
    nowYear += (nowYear < 2000) ? 1900 : 0;
    var day = nowDayOfWeek || 7;
		return new Date();   
  },
  /**
	 * @param 日期格式化
	 * @returns {String}
	 */
	formatDate: function(date) {
		var myyear = date.getFullYear();
		var mymonth = date.getMonth() + 1;
		var myweekday = date.getDate();
 
		if (mymonth < 10) {
			mymonth = "0" + mymonth;
		}
		if (myweekday < 10) {
			myweekday = "0" + myweekday;
    }
    console.log(myyear + "-" + mymonth + "-" + myweekday)
		return (myyear + "-" + mymonth + "-" + myweekday);
	},

  //克隆整个对象（深拷贝）
  cloneObj:function(initalObj,finalObj){
    var obj = finalObj || {};
    for (var i in initalObj) {
        var prop = initalObj[i];
  
        // 避免相互引用对象导致死循环，如initalObj.a = initalObj的情况
        if(prop === obj) {
            continue;
        }
  
        if (typeof prop === 'object') {
            obj[i] = (prop.constructor === Array) ? [] : {};
            arguments.callee(prop, obj[i]);
        } else {
            obj[i] = prop;
        }
    }
    return obj;
},
  infomation: (result) => {
    // console.log(this)
    if (result) {
      if (result.code === 200) {
        this.notify({
          title: '操作提示',
          message: '操作成功',
          duration: 0
        })
      } else if (result.code === 500) {
        this.notify({
          title: '错误提示',
          message: result.info,
          duration: 0
        })
      }
    }
  },
  isNullOrEmpty: function(val) {
    if (typeof val === 'boolean') {
      return false
    }
    if (typeof val === 'number') {
      return false
    }
    if (val instanceof Array) {
      if (val.length === 0) return true
    } else if (val instanceof Object) {
      if (JSON.stringify(val) === '{}') return true
    } else {
      if (val === 'null' || val == null || val === 'undefined' || val === undefined || val === '') return true
      return false
    }
    return false
  },

  

}

export default ygxcUtils
