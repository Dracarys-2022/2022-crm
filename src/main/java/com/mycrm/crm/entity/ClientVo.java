package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "客户公司实体扩展")
public class ClientVo extends Client {
    @ApiModelProperty(value = "录入人姓名")

    private String oname;
}
