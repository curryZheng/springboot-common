package com.ygxc.aqjy.rsp.user;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("权限树dto")
public class AuthTreeDto extends AuthDto {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty("子权限列表")
    private List<AuthTreeDto> childList = new ArrayList<>();
    
    /**
	 * 添加子节点
	 * @param dto
	 */
	public void addChild(AuthTreeDto dto) {
		this.childList.add(dto);
	}

	public List<AuthTreeDto> getChildList() {
		return childList;
	}

	public void setChildList(List<AuthTreeDto> childList) {
		this.childList = childList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
