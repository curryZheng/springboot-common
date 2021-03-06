/*
 * Welcome to use the TableGo Tools.
 * 
 * http://www.tablego.cn
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author: bianj
 * Email: tablego@qq.com
 * Version: 6.6.6
 */
package com.ygxc.aqjy.entity.user;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ygxc.aqjy.common.structure.IBusinessEntity;


/**
 * 用户表(t_user)
 * 
 * 
 * 
 */
@TableName("t_user")
public class UserEntity implements java.io.Serializable, IBusinessEntity {
    /** 版本号 */
    private static final long serialVersionUID = -3947475914179286362L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** ID */
    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    /** 姓名 */
    private String name;

    /** 员工编号 */
    private String userNo;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 角色ID */
    private String roleId;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 是否删除，0：否；1：是 */
    private Integer isDelete;

    /** 操作人ID */
    private String opUserId;

    /** 操作人 */
    private String opUserName;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */

    /**
     * 获取ID
     * 
     * @return ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置ID
     * 
     * @param id
     *          ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取姓名
     * 
     * @return 姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置姓名
     * 
     * @param name
     *          姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取员工编号
     * 
     * @return 员工编号
     */
    public String getUserNo() {
        return this.userNo;
    }

    /**
     * 设置员工编号
     * 
     * @param userNo
     *          员工编号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取用户名
     * 
     * @return 用户名
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 设置用户名
     * 
     * @param username
     *          用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     * 
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     * 
     * @param password
     *          密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取角色ID
     * 
     * @return 角色ID
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 设置角色ID
     * 
     * @param roleId
     *          角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     * 
     * @param createTime
     *          创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     * 
     * @return 修改时间
     */
    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置修改时间
     * 
     * @param updateTime
     *          修改时间
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否删除，0：否；1：是
     * 
     * @return 是否删除
     */
    public Integer getIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置是否删除，0：否；1：是
     * 
     * @param isDelete
     *          是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取操作人ID
     * 
     * @return 操作人ID
     */
    public String getOpUserId() {
        return this.opUserId;
    }

    /**
     * 设置操作人ID
     * 
     * @param opUserId
     *          操作人ID
     */
    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    /**
     * 获取操作人
     * 
     * @return 操作人
     */
    public String getOpUserName() {
        return this.opUserName;
    }

    /**
     * 设置操作人
     * 
     * @param opUserName
     *          操作人
     */
    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    /* This code was generated by TableGo tools, mark 2 end. */
}
