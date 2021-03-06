package com.ygxc.aqjy.req.oplog;

import java.sql.Timestamp;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("创建操作日志req")
public class OpLogCreateReq extends RequestHead {
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty("操作时间")
    private Timestamp opTime;

    @ApiModelProperty("功能ID")
    private String fnId;

    @ApiModelProperty("功能编号")
    private String fnCode;

    @ApiModelProperty("功能名称")
    private String fnName;

    @ApiModelProperty("功能URL")
    private String fnUrl;

    @ApiModelProperty("请求内容")
    private String req;

    @ApiModelProperty("响应内容")
    private String rsp;

    @ApiModelProperty("操作结果，0：失败；1：成功；")
    private Integer result;
    
    @ApiModelProperty("操作人ID")
	private String opUserId;
	
	@ApiModelProperty("操作人")
	private String opUserName;
	
	@ApiModelProperty("员工编号")
	private String opUserNo;

	public Timestamp getOpTime() {
		return opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}

	public String getFnId() {
		return fnId;
	}

	public void setFnId(String fnId) {
		this.fnId = fnId;
	}

	public String getFnCode() {
		return fnCode;
	}

	public void setFnCode(String fnCode) {
		this.fnCode = fnCode;
	}

	public String getFnName() {
		return fnName;
	}

	public void setFnName(String fnName) {
		this.fnName = fnName;
	}

	public String getFnUrl() {
		return fnUrl;
	}

	public void setFnUrl(String fnUrl) {
		this.fnUrl = fnUrl;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getRsp() {
		return rsp;
	}

	public void setRsp(String rsp) {
		this.rsp = rsp;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}

	public String getOpUserNo() {
		return opUserNo;
	}

	public void setOpUserNo(String opUserNo) {
		this.opUserNo = opUserNo;
	}

	
}
