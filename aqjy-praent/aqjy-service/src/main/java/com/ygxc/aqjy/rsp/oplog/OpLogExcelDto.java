package com.ygxc.aqjy.rsp.oplog;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("操作日志导出dto")
@Data
public class OpLogExcelDto implements Serializable {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty("操作时间")
    private Timestamp opTime;

    @ApiModelProperty("功能名称")
    private String fnName;

    @ApiModelProperty("操作结果，0：失败；1：成功；")
    private Integer result;

    @ApiModelProperty("操作员工编号")
    private String opUserNo;

    @ApiModelProperty("操作人")
    private String opUserName;
    
    @ApiModelProperty("操作时间")
    private String opTimeStr;
    
    @ApiModelProperty("操作结果，0：失败；1：成功；")
    private String resultStr;

	
}
