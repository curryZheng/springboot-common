package com.ygxc.aqjy.req.user;

import java.util.List;

import javax.validation.constraints.NotBlank;
import com.ygxc.aqjy.common.structure.RequestHead;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改角色权限req")
public class RoleAuthModifyReq extends RequestHead {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@ApiModelProperty("角色ID")
	private String roleId;
	
	@ApiModelProperty("权限ID列表")
	private List<String> authIdList;

	
}
