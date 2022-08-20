package com.ly.crm.entity;

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
 * @author ly
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务id
     */
    private String sid;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 成交价
     */
    private BigDecimal price;

    /**
     * 客户联系人编号
     */
    private Integer coid;

    /**
     * 客户公司id
     */
    private Integer cid;

    private LocalDateTime createtime;

    /**
     * 填写记录的销售id
     */
    private Integer operid;


}
