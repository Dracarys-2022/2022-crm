package com.mycrm.crm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Ͷ����չʵ��")
public class PetitionVo extends Petition {
    @ApiModelProperty(value = "ְ������")
    private String oname;
    @ApiModelProperty(value = "����������")
    private String exname;
}
