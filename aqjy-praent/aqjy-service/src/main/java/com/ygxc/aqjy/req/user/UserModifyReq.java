package com.ygxc.aqjy.req.user;

import javax.validation.constraints.NotBlank;

import com.ygxc.aqjy.common.structure.RequestHead;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("修改用户req")
public class UserModifyReq extends RequestHead {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("员工编号")
    private String userNo;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
