package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "拜访实体")
public class Visit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 拜访编号
     */
    @TableId(value = "vid", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer vid;

    /**
     * 所属公司
     */
    @ApiModelProperty(value = "所属公司")
    private String company;

    /**
     * 拜访客户
     */
    @ApiModelProperty(value = "拜访客户")
    private String client;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 陪同人员
     */
    @ApiModelProperty(value = "陪同人员")
    private String accompany;

    /**
     * 拜访主题
     */
    @ApiModelProperty(value = "拜访主题")
    private String theme;

    /**
     * 拜访目的
     */
    @ApiModelProperty(value = "拜访目的")
    private String purpose;

    /**
     * 拜访前准备
     */
    @ApiModelProperty(value = "拜访前准备")
    private String ready;

    /**
     * 拜访结果
     */
    @ApiModelProperty(value = "拜访结果")
    private String outcome;

    /**
     * 客户态度
     */
    @ApiModelProperty(value = "客户态度")
    private String attitude;

    /**
     * 活动经费
     */
    @ApiModelProperty(value = "客户态度")
    private String fund;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 记录人id
     */
    @ApiModelProperty(value = "记录人id")
    private Integer operid;

    /**
     * 拜访时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    @ApiModelProperty(value = "拜访时间")
    private LocalDateTime createtime;


}
