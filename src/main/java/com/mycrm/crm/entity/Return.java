package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Return implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退货id
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    /**
     * 订单id
     */
    private String oid;

    /**
     * 数量
     */
    private Integer numbers;

    /**
     * 退换原因
     */
    private String reason;

    /**
     * 处理方法
     */
    private Integer method;

    /**
     * 是否审核，0未审核，1已审核
     */
    private Integer status;

    /**
     * 退货时间
     */
    private LocalDateTime createtime;


}
