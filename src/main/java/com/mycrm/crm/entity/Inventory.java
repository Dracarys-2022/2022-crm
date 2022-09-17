package com.mycrm.crm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date producetime;

    /**
     * 生产截止日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endtime;

    /**
     * 入库时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
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
