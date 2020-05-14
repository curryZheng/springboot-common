package com.ygxc.aqjy.rsp.sys;

import java.io.Serializable;
import java.util.List;

import com.ygxc.aqjy.rsp.user.AuthDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("登录结果信息dto")
@Data
public class LoginDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
    private String id;

	@ApiModelProperty("用户名")
    private String username;
    
    @ApiModelProperty("姓名")
    private String name;
    
    @ApiModelProperty("员工编号")
    private String userNo;
    
    @ApiModelProperty("token")
    private String token;
    
    @ApiModelProperty("角色ID")
    private String roleId;
    
    @ApiModelProperty("权限代号列表")
    private List<String> authCodeList;



}
