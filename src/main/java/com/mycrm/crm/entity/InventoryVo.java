package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "�����չʵ��")

public class InventoryVo extends Inventory{
    @ApiModelProperty(value = "��Ʒ����")
    private String pname;
    @ApiModelProperty(value = "��Ʒ����")
    private String units;
}
