package com.ygxc.aqjy.req.user;

import javax.validation.constraints.NotBlank;

import com.ygxc.aqjy.common.structure.RequestHead;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("修改角色req")
public class RoleModifyReq extends RequestHead {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名称")
    private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
