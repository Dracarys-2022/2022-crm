package com.mycrm.crm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
<<<<<<< HEAD
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
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
<<<<<<< HEAD
@ApiModel(description = "订单实体")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "主键，订单编号")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String oid;

    /**
     * 实际付款
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "实际付款")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private BigDecimal price;

    /**
     * 数量
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "订单数量")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer numbers;

    /**
     * 客户联系人编号
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "客户编号")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer coid;

    /**
     * 客户公司id
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "客户公司id")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer cid;

    /**
     * 产品id
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "产品id")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer pid;

    /**
     * 下单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
<<<<<<< HEAD
    @ApiModelProperty(value = "下单日期")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private LocalDateTime createtime;

    /**
     * 交货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
<<<<<<< HEAD
    @ApiModelProperty(value = "交货日期")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private LocalDateTime deadline;

    /**
     * 销售员id
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "销售员id")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer operid;

    /**
     * 点单状态：0是未确认，1就是已确定，2退货
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "订单状态")
    private Integer type;
    @ApiModelProperty(value = "订单类型")
=======
    private Integer type;
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer state;

}
