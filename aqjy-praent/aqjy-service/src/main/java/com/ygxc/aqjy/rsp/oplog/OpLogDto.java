package com.ygxc.aqjy.rsp.oplog;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("操作日志dto")
@Data
public class OpLogDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("操作时间")
    private Timestamp opTime;

    @ApiModelProperty("功能ID")
    private String fnId;

    @ApiModelProperty("功能编号")
    private String fnCode;

    @ApiModelProperty("功能名称")
    private String fnName;

    @ApiModelProperty("功能URL")
    private String fnUrl;

    @ApiModelProperty("请求内容")
    private String req;

    @ApiModelProperty("响应内容")
    private String rsp;

    @ApiModelProperty("操作结果，0：失败；1：成功；")
    private Integer result;

    @ApiModelProperty("操作员工编号")
    private String opUserNo;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    @ApiModelProperty("操作人ID")
    private String opUserId;

    @ApiModelProperty("操作人")
    private String opUserName;


}
