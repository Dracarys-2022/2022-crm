package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "角色扩展类实体")
public class RolesVo extends Roles{
    @ApiModelProperty(value = "父权限名称")
    private String functionName;
}
