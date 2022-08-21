package com.mycrm.crm.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 产品名
     */
    private String pname;

    /**
     * 分类名
     */
    private String category;

    /**
     * 库存量
     */
    private Integer inventory;

    /**
     * 成本价
     */
    private BigDecimal price;

    /**
     * 规格单位
     */
    private String units;

    /**
     * 规格
     */
    private Integer specification;

    /**
     * 保质期
     */
    private String shelflife;

    private BigDecimal marketprice;

    /**
     * 是否下架；0为下架，1为上架
     */
    private Integer status;

    /**
     * 类型 1为产品 2为服务
     */
    private Integer type;


}
