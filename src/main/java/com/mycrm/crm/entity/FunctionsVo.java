package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "功能扩展实体")
public class FunctionsVo extends Functions{
    @ApiModelProperty(value = "级别")
    private String FunctionName;
}
