package com.ygxc.aqjy.rsp.user;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色维护的权限树dto")
public class AuthTreeForRoleDto extends AuthDto {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("是否有权限，0：否；1：是；")
	private Integer isAuth;
	
	@ApiModelProperty("子权限列表")
    private List<AuthTreeForRoleDto> childList = new ArrayList<>();
	
	/**
	 * 添加子节点
	 * @param dto
	 */
	public void addChild(AuthTreeForRoleDto dto) {
		this.childList.add(dto);
	}

	public List<AuthTreeForRoleDto> getChildList() {
		return childList;
	}

	public void setChildList(List<AuthTreeForRoleDto> childList) {
		this.childList = childList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}
	
	public Integer getIsAuth() {
		return isAuth;
	}
}
