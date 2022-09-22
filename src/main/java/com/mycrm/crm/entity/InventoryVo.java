package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "库存扩展类")

public class InventoryVo extends Inventory{
    @ApiModelProperty(value = "商品名称")
    private String pname;
    @ApiModelProperty(value = "单位")
    private String units;
}
