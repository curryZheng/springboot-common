package com.ygxc.aqjy.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户对象 sys_user
 * 
 * @author aqjy
 */
@Data
@TableName(value = "sys_user")
public class SysUser  
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @TableId
    private Long userId;

    /** 部门ID */
    private Long deptId;



    /** 登录名称 */
    
    private String loginName;

    /** 用户名称 */
   
    private String userName;

    /** 用户邮箱 */
   
    private String email;

    /** 手机号码 */
    
    private String phonenumber;

    /** 用户性别 */
   
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 盐加密 */
    private String salt;

    /** 帐号状态（0正常 1停用） */
   
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登陆IP */
    
    private String loginIp;

    /** 最后登陆时间 */
   
    private Date loginDate;

   
}
