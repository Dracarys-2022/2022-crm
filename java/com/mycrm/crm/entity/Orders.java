package com.mycrm.crm.entity;

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
    private LocalDateTime createtime;

    /**
     * 交货日期
     */
    private LocalDateTime deadline;

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
