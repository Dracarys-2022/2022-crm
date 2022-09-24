package com.mycrm.crm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
 * @author ly
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

@ApiModel(description = "订单实体")

public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */

    @ApiModelProperty(value = "主键，订单编号")

    private String oid;

    /**
     * 实际付款
     */

    @ApiModelProperty(value = "实际付款")

    private BigDecimal price;

    /**
     * 数量
     */

    @ApiModelProperty(value = "订单数量")

    private Integer numbers;

    /**
     * 客户联系人编号
     */

    @ApiModelProperty(value = "客户编号")

    private Integer coid;

    /**
     * 客户公司id
     */

    @ApiModelProperty(value = "客户公司id")

    private Integer cid;

    /**
     * 产品id
     */

    @ApiModelProperty(value = "产品id")

    private Integer pid;

    /**
     * 下单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")

    @ApiModelProperty(value = "下单日期")

    private LocalDateTime createtime;

    /**
     * 交货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")

    @ApiModelProperty(value = "交货日期")

    private LocalDateTime deadline;

    /**
     * 销售员id

    @ApiModelProperty(value = "销售员id")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer operid;

    /**
     * 点单状态：0是未确认，1就是已确定，2退货
     */

    @ApiModelProperty(value = "订单状态")
    private Integer state;
    @ApiModelProperty(value = "订单类型")


    private Integer type;

}
