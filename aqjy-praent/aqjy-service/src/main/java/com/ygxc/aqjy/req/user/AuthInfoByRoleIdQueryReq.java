package com.ygxc.aqjy.req.user;

import javax.validation.constraints.NotBlank;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("根据角色ID查询权限相关信息列表req")
public class AuthInfoByRoleIdQueryReq extends RequestHead {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("角色ID")
	private String roleId;

	public AuthInfoByRoleIdQueryReq() {
		super();
	}

	public AuthInfoByRoleIdQueryReq(String roleId) {
		super();
		this.roleId = roleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
