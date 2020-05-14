package com.ygxc.aqjy.rsp.user;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("权限dto")
@Data
public class AuthDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("权限编号")
    private String code;
    
    @ApiModelProperty("1菜单  2按钮")
    private String type;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("父ID")
    private String parentId;
    
    @ApiModelProperty("排序，只影响平级")
    private Integer sort;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    @ApiModelProperty("操作人ID")
    private String opUserId;

    @ApiModelProperty("操作人")
    private String opUserName;

}
