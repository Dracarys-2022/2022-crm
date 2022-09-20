package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户实体扩展")
public class OperatorVo extends Operator {
    @ApiModelProperty(value = "角色名称")
    private String roname;
}
