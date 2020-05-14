package com.ygxc.aqjy.rsp.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("登录用户dto")
@Data
public class UserLoginDto extends UserDto {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("密码")
    private String password;
	
	
}
