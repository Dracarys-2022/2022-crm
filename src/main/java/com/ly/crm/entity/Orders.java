package com.ly.crm.entity;

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
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String oid;

    /**
     * 实际付款
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer numbers;

    /**
     * 客户联系人编号
     */
    private Integer coid;

    /**
     * 客户公司id
     */
    private Integer cid;

    /**
     * 产品id
     */
    private Integer pid;

    /**
     * 下单日期
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createtime;

    /**
     * 交货日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date deadline;

    /**
     * 销售员id
     */
    private Integer operid;

    /**
     * 点单状态：0是未确认，1就是已确定，2退货
     */
    private Integer type;
    private Integer state;

}
