package com.ygxc.aqjy.common.structure;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求头
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
@ApiModel("分页请求头")
public class RequestPageHead extends RequestHead {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("页码")
	protected int current = 1;
	
	@ApiModelProperty("每页数量")
	protected int pageSize = 10;


	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
