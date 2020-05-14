package com.ygxc.aqjy.common.structure;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页参数")
public class PageRequest {

    @ApiModelProperty(value = "当前页 默认 1 ", example = "1")
    private long current = 1;

    @ApiModelProperty(value = "分页每页数据数 默认 20", example = "200")
    private long size = 200;

}