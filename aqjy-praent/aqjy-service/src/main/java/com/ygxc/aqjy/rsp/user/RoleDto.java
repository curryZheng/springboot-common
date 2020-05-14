package com.ygxc.aqjy.rsp.user;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("角色dto")
@Data
public class RoleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    @ApiModelProperty("操作人ID")
    private String opUserId;

    @ApiModelProperty("操作人")
    private String opUserName;


}
