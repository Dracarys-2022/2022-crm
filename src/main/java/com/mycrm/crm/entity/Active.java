package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "活动实体")
public class Active implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "活动名称")
    private String name;
    @ApiModelProperty(value = "时间")
    private String time;
    @ApiModelProperty(value = "开始时间")
    private String starttime;
    @ApiModelProperty(value = "结束时间")
    private String endtime;
    @ApiModelProperty(value = "参与人")
    private String people;


}
