package com.ygxc.aqjy.req.user;

import javax.validation.constraints.NotBlank;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("根据URL查询权限req")
public class AuthByUrlFindReq extends RequestHead {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("URL")
	private String url;
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
