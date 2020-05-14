package com.ygxc.aqjy.common.structure;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 请求头
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
@ApiModel("请求头")
public class RequestHead implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "操作人ID", hidden = true)
	private String opUserId;
	
	@ApiModelProperty(value = "操作人", hidden = true)
	private String opUserName;
	
	@ApiModelProperty(value = "员工编号", hidden = true)
	private String opUserNo;
	
	public RequestHead() {
		this.opUserId = RequestCommParamsHolder.getString("opUserId");
		this.opUserName = RequestCommParamsHolder.getString("opUserName");
		this.opUserNo = RequestCommParamsHolder.getString("opUserNo");
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
		RequestCommParamsHolder.set("opUserId", opUserId);
	}

	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
		RequestCommParamsHolder.set("opUserName", opUserName);
	}

	public String getOpUserNo() {
		return opUserNo;
	}

	public void setOpUserNo(String opUserNo) {
		this.opUserNo = opUserNo;
		RequestCommParamsHolder.set("opUserNo", opUserNo);
	}
}
