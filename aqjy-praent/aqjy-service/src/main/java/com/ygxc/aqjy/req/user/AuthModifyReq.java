package com.ygxc.aqjy.req.user;

import javax.validation.constraints.NotBlank;

import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("修改权限req")
public class AuthModifyReq extends RequestHead {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("权限编号")
    private String code;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("父ID")
    private String parentId;
    
    @ApiModelProperty("排序，只影响平级")
    private Integer sort;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getSort() {
		return sort;
	}
}
