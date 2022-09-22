package com.mycrm.crm.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
 * @author ly
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "产品实体")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer pid;

    /**
     * 产品名
     */
    @ApiModelProperty(value = "产品名")
    private String pname;

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String category;
    /**
     * 成本价
     */
    @ApiModelProperty(value = "成本价")
    private BigDecimal price;

    /**
     * 规格单位
     */
    @ApiModelProperty(value = "规格单位")
    private String units;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private Integer specification;

    /**
     * 保质期
     */
    @ApiModelProperty(value = "保质期")
    private String shelflife;
    @ApiModelProperty(value = "市场价")
    private BigDecimal marketprice;

    /**
     * 是否下架；0为下架，1为上架
     */
    @ApiModelProperty(value = "产品状态")
    private Integer status;

    /**
     * 类型 1为产品 2为服务
     */
    @ApiModelProperty(value = "产品类型")
    private Integer type;


}
