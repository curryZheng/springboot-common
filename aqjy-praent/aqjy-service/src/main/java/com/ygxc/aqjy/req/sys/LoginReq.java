package com.ygxc.aqjy.req.sys;

import javax.validation.constraints.NotBlank;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录req")
public class LoginReq extends RequestHead {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("用户名")
	private String username;
	
	@NotBlank
	@ApiModelProperty("密码")
	private String password;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
