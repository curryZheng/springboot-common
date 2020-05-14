package com.ygxc.aqjy.req.user;

import com.ygxc.aqjy.common.structure.RequestPageHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询角色列表req")
public class RoleQueryReq extends RequestPageHead {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("名称")
    private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
