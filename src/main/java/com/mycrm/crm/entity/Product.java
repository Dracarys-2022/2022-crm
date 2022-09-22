package com.mycrm.crm.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
 * </p>
 *
 * @author ly
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
<<<<<<< HEAD
@ApiModel(description = "产品实体")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "pid", type = IdType.AUTO)
<<<<<<< HEAD
    @ApiModelProperty(value = "主键")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer pid;

    /**
     * 产品名
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "产品名")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String pname;

    /**
     * 分类名
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "分类名")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String category;
    /**
     * 成本价
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "成本价")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private BigDecimal price;

    /**
     * 规格单位
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "规格单位")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String units;

    /**
     * 规格
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "规格")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer specification;

    /**
     * 保质期
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "保质期")
    private String shelflife;
    @ApiModelProperty(value = "市场价")
=======
    private String shelflife;

>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private BigDecimal marketprice;

    /**
     * 是否下架；0为下架，1为上架
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "产品状态")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer status;

    /**
     * 类型 1为产品 2为服务
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "产品类型")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer type;


}
