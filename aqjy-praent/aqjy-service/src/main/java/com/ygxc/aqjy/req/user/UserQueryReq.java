package com.ygxc.aqjy.req.user;

import com.ygxc.aqjy.common.structure.RequestPageHead;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("查询用户列表req")
public class UserQueryReq extends RequestPageHead {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("员工编号")
    private String userNo;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("角色ID")
    private String roleId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
