package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "投诉扩展类")
public class PetitionVo extends Petition {
    @ApiModelProperty(value = "记录人姓名")
    private String oname;
    @ApiModelProperty(value = "操作员姓名")
    private String exname;
}
