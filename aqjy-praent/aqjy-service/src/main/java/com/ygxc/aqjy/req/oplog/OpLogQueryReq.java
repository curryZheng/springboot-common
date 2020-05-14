package com.ygxc.aqjy.req.oplog;

import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import com.ygxc.aqjy.common.structure.RequestPageHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询操作日志列表")
public class OpLogQueryReq extends RequestPageHead {
	private static final long serialVersionUID = 1L;

	@NotNull
	@ApiModelProperty("操作时间起")
    private Timestamp opTimeBegin;
	
	@NotNull
	@ApiModelProperty("操作时间止")
    private Timestamp opTimeEnd;
	
	@ApiModelProperty("功能ID")
    private String fnId;

    @ApiModelProperty("功能编号")
    private String fnCode;
    
    @ApiModelProperty("功能URL关键词")
    private String fnUrlKeyword;
    
    @ApiModelProperty("操作结果，0：失败；1：成功；")
    private Integer result;
    
    @ApiModelProperty("操作人ID")
    private String opUserId;
    
    @ApiModelProperty("请求内容关键词")
    private String reqKeyword;

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

	public String getFnUrlKeyword() {
		return fnUrlKeyword;
	}

	public void setFnUrlKeyword(String fnUrlKeyword) {
		this.fnUrlKeyword = fnUrlKeyword;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public String getReqKeyword() {
		return reqKeyword;
	}

	public void setReqKeyword(String reqKeyword) {
		this.reqKeyword = reqKeyword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getOpTimeBegin() {
		return opTimeBegin;
	}

	public void setOpTimeBegin(Timestamp opTimeBegin) {
		this.opTimeBegin = opTimeBegin;
	}

	public Timestamp getOpTimeEnd() {
		return opTimeEnd;
	}

	public void setOpTimeEnd(Timestamp opTimeEnd) {
		this.opTimeEnd = opTimeEnd;
	}
}
