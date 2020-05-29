let menu = {};

/**
 * 字体图标
 * @type {{name: string, icon: string, children: {}}}
 */
menu.sys = {
  name: '系统设定',
  icon: 'fa fa-list',
  children:[
    {
      name: '权限列表',
      icon: 'fa fa-list',
      path: '/auth',
    },
    {
      name: '用户列表',
      icon: 'fa fa-list',
      path: '/user',
    },
    {
      name: '在线用户列表',
      icon: 'fa fa-list',
      path: '/onlineUser',
    }, 
    {
      name: '角色列表',
      icon: 'fa fa-list',
      path: '/role',
    },  
  ]
};
menu.opLog = {
  name: '操作日志管理',
  icon: 'fa fa-th',
  children:[
    {
      name: '操作日志管理',
      icon: 'fa fa-list',
      path: '/opLog',
    },
  ]      
};




export default menu;

