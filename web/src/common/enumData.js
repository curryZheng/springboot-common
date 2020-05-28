/**
 * 常量数据
 */
const enumData = {
  enterpriseNature:[{key:1,value:'国企'},{key:1,value:'国企2'},{key:1,value:'国企3'},{key:1,value:'国企4'},{key:1,value:'国企5'}],
  // 类型
  circleType: [{ key: 1, value: '动态' }, { key: 2, value: '文章' }],
  stationIds: [{ key: 1, value: '武汉' }, { key: 2, value: '长沙' }],
  getEnumValue: function(enumDatas, key) {

    if (enumData == null && enumData.size > 0) {
      return ''
    }
    var value = ''
    enumDatas.forEach(item => {
      if (item.key === key) {
        value = item.value
      }
    })
    return value
  }
}

export default enumData
