package com.ygxc.aqjy.req.sys;
import javax.validation.constraints.NotEmpty;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiOperation("踢出登陆用户请求参数")
public class kickUserReq extends RequestHead {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@ApiModelProperty("用户姓名")
	private String username;
}
