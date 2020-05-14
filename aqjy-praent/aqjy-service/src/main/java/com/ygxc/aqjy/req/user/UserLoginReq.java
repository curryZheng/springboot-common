package com.ygxc.aqjy.req.user;

import javax.validation.constraints.NotBlank;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录查询用户req")
public class UserLoginReq extends RequestHead {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@ApiModelProperty("用户名")
	private String username;
	
	public UserLoginReq() {
		super();
	}

	public UserLoginReq(String username) {
		super();
		this.username = username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
