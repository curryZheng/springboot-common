/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/3/26 14:29:11                           */
/*==============================================================*/


drop table if exists t_auth;

drop table if exists t_role;

drop table if exists t_role_auth;

drop table if exists t_user;

drop table if exists t_op_log;

/*==============================================================*/
/* Table: t_auth                                                */
/*==============================================================*/
create table t_auth
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(50) comment '名称',
   code                 varchar(100) comment '权限编号',
   url                  varchar(1000) comment 'URL',
   parent_id            varchar(32) comment '父ID',
   sort                 int(8) comment '排序，只影响平级',
   create_time          timestamp(3) null default NULL comment '创建时间',
   update_time          timestamp(3) null default NULL comment '修改时间',
   is_delete            int(1) comment '是否删除，0：否；1：是',
   op_user_id           varchar(32) comment '操作人ID',
   op_user_name         varchar(50) comment '操作人',
   primary key (id)
);

alter table t_auth comment '权限表';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(50) comment '名称',
   create_time          timestamp(3) null default NULL comment '创建时间',
   update_time          timestamp(3) null default NULL comment '修改时间',
   is_delete            int(1) comment '是否删除，0：否；1：是',
   op_user_id           varchar(32) comment '操作人ID',
   op_user_name         varchar(50) comment '操作人',
   primary key (id)
)
charset = UTF8;

alter table t_role comment '职位角色表';

/*==============================================================*/
/* Table: t_role_auth                                           */
/*==============================================================*/
create table t_role_auth
(
   role_id              varchar(32) comment '角色ID',
   auth_id              varchar(32) comment '权限ID'
);

alter table t_role_auth comment '角色权限关系表';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(50) comment '姓名',
   user_no              varchar(32) comment '员工编号',
   username             varchar(50) comment '用户名',
   password             varchar(100) comment '密码',
   role_id              varchar(32) comment '角色ID',
   create_time          timestamp(3) null default NULL comment '创建时间',
   update_time          timestamp(3) null default NULL comment '修改时间',
   is_delete            int(1) comment '是否删除，0：否；1：是',
   op_user_id           varchar(32) comment '操作人ID',
   op_user_name         varchar(50) comment '操作人',
   primary key (id)
)
charset = UTF8;

alter table t_user comment '用户表';

/*==============================================================*/
/* Table: t_op_log                                              */
/*==============================================================*/
create table t_op_log
(
   id                   varchar(32) not null comment 'ID',
   op_time              timestamp(3) null comment '操作时间',
   fn_id                varchar(32) comment '功能ID',
   fn_code              varchar(50) comment '功能编号',
   fn_name              varchar(100) comment '功能名称',
   fn_url               varchar(200) comment '功能URL',
   req                  varchar(5000) comment '请求内容',
   rsp                  varchar(5000) comment '响应内容',
   result               int(1) comment '操作结果，0：失败；1：成功；',
   op_user_no           varchar(32) comment '操作员工编号',
   create_time          timestamp(3) null default NULL comment '创建时间',
   update_time          timestamp(3) null default NULL comment '修改时间',
   is_delete            int(1) comment '是否删除，0：否；1：是',
   op_user_id           varchar(32) comment '操作人ID',
   op_user_name         varchar(50) comment '操作人',
   primary key (id)
)
charset = UTF8;

alter table t_op_log comment '操作日志表';
