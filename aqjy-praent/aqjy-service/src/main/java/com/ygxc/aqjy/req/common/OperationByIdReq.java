package com.ygxc.aqjy.req.common;

import javax.validation.constraints.NotBlank;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用req baseId
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
@ApiModel("根据ID进行操作req")
public class OperationByIdReq extends RequestHead {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@ApiModelProperty("ID")
    private String id;

	public OperationByIdReq() {
	}

	public OperationByIdReq(String id) {
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
