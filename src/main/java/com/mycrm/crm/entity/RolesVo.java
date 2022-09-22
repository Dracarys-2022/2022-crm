package com.mycrm.crm.entity;

<<<<<<< HEAD
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "角色扩展类实体")
public class RolesVo extends Roles{
    @ApiModelProperty(value = "父权限名称")
=======
import lombok.Data;

@Data
public class RolesVo extends Roles{
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String functionName;
}
