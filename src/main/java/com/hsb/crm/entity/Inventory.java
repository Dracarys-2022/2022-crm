package com.hsb.crm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存批次编号，根据时间生成
     */
    private String id;

    /**
     * 产品id
     */
    private Integer pid;

    /**
     * 进货量
     */
    private Integer numbers;

    /**
     * 现有量
     */
    private Integer exist;

    /**
     * 进货价
     */
    private BigDecimal price;

    /**
     * 记录人id
     */
    private Integer operid;

    /**
     * 仓库位置
     */
    private String location;

    /**
     * 生产日期
     */
    private LocalDateTime producetime;

    /**
     * 生产截止日期
     */
    private LocalDateTime endtime;

    /**
     * 入库时间
     */
    private LocalDateTime createtime;

    /**
     * 0新进，1退换
     */
    private Integer type;

    /**
     * 0为正常，1为异常
     */
    private Integer state;


}
