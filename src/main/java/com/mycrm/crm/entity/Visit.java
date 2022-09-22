package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

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
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
<<<<<<< HEAD
@ApiModel(description = "拜访实体")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
public class Visit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 拜访编号
     */
    @TableId(value = "vid", type = IdType.AUTO)
<<<<<<< HEAD
    @ApiModelProperty(value = "主键")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer vid;

    /**
     * 所属公司
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "所属公司")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String company;

    /**
     * 拜访客户
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "拜访客户")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String client;

    /**
     * 联系电话
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "联系电话")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String phone;

    /**
     * 陪同人员
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "陪同人员")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String accompany;

    /**
     * 拜访主题
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "拜访主题")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String theme;

    /**
     * 拜访目的
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "拜访目的")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String purpose;

    /**
     * 拜访前准备
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "拜访前准备")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String ready;

    /**
     * 拜访结果
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "拜访结果")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String outcome;

    /**
     * 客户态度
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "客户态度")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String attitude;

    /**
     * 活动经费
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "客户态度")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String fund;

    /**
     * 备注
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "备注")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String remark;

    /**
     * 记录人id
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "记录人id")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer operid;

    /**
     * 拜访时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
<<<<<<< HEAD
    @ApiModelProperty(value = "拜访时间")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private LocalDateTime createtime;


}
