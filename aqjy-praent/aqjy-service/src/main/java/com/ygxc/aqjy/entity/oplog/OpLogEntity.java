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
package com.ygxc.aqjy.entity.oplog;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 操作日志表(t_op_log)
 * 
 * @author Qiaoxin.Hong
 * @version 1.0.0 2020-03-30
 */
@TableName("t_op_log")
public class OpLogEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 1465908258276470143L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** ID */
    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    /** 操作时间 */
    private Timestamp opTime;

    /** 功能ID */
    private String fnId;

    /** 功能编号 */
    private String fnCode;

    /** 功能名称 */
    private String fnName;

    /** 功能URL */
    private String fnUrl;

    /** 请求内容 */
    private String req;

    /** 响应内容 */
    private String rsp;

    /** 操作结果，0：失败；1：成功； */
    private Integer result;

    /** 操作员工编号 */
    private String opUserNo;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 是否删除，0：否；1：是 */
    @TableLogic()
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
     * 获取操作时间
     * 
     * @return 操作时间
     */
    public Timestamp getOpTime() {
        return this.opTime;
    }

    /**
     * 设置操作时间
     * 
     * @param opTime
     *          操作时间
     */
    public void setOpTime(Timestamp opTime) {
        this.opTime = opTime;
    }

    /**
     * 获取功能ID
     * 
     * @return 功能ID
     */
    public String getFnId() {
        return this.fnId;
    }

    /**
     * 设置功能ID
     * 
     * @param fnId
     *          功能ID
     */
    public void setFnId(String fnId) {
        this.fnId = fnId;
    }

    /**
     * 获取功能编号
     * 
     * @return 功能编号
     */
    public String getFnCode() {
        return this.fnCode;
    }

    /**
     * 设置功能编号
     * 
     * @param fnCode
     *          功能编号
     */
    public void setFnCode(String fnCode) {
        this.fnCode = fnCode;
    }

    /**
     * 获取功能名称
     * 
     * @return 功能名称
     */
    public String getFnName() {
        return this.fnName;
    }

    /**
     * 设置功能名称
     * 
     * @param fnName
     *          功能名称
     */
    public void setFnName(String fnName) {
        this.fnName = fnName;
    }

    /**
     * 获取功能URL
     * 
     * @return 功能URL
     */
    public String getFnUrl() {
        return this.fnUrl;
    }

    /**
     * 设置功能URL
     * 
     * @param fnUrl
     *          功能URL
     */
    public void setFnUrl(String fnUrl) {
        this.fnUrl = fnUrl;
    }

    /**
     * 获取请求内容
     * 
     * @return 请求内容
     */
    public String getReq() {
        return this.req;
    }

    /**
     * 设置请求内容
     * 
     * @param req
     *          请求内容
     */
    public void setReq(String req) {
        this.req = req;
    }

    /**
     * 获取响应内容
     * 
     * @return 响应内容
     */
    public String getRsp() {
        return this.rsp;
    }

    /**
     * 设置响应内容
     * 
     * @param rsp
     *          响应内容
     */
    public void setRsp(String rsp) {
        this.rsp = rsp;
    }

    /**
     * 获取操作结果，0：失败；1：成功；
     * 
     * @return 操作结果
     */
    public Integer getResult() {
        return this.result;
    }

    /**
     * 设置操作结果，0：失败；1：成功；
     * 
     * @param result
     *          操作结果
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 获取操作员工编号
     * 
     * @return 操作员工编号
     */
    public String getOpUserNo() {
        return this.opUserNo;
    }

    /**
     * 设置操作员工编号
     * 
     * @param opUserNo
     *          操作员工编号
     */
    public void setOpUserNo(String opUserNo) {
        this.opUserNo = opUserNo;
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
