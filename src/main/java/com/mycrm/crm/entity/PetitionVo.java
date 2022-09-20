package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "投诉扩展实体")
public class PetitionVo extends Petition {
    @ApiModelProperty(value = "职工姓名")
    private String opername;
    @ApiModelProperty(value = "操作人姓名")
    private String exname;
}
