package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "客户实体扩展")

public class ContactVo extends Contact {
    @ApiModelProperty(value = "公司名称")
    private String cname;
    @ApiModelProperty(value = "录入人名称")
    private String oname;
}
