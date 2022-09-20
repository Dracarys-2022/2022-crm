package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

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
 * @author hsb
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(description = "客户实体")

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coid", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")

    private Integer coid;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")

    private Integer cid;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")

    private String coname;

    /**
     * 家庭地址
     */
    @ApiModelProperty(value = "家庭地址")

    private String address;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")

    private String phone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")

    private String remark;

    /**
     * 录入时间
     */
    @ApiModelProperty(value = "录入时间")

    private LocalDateTime createtime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")

    private LocalDateTime updatetime;

    /**
     * 录入人id
     */
    @ApiModelProperty(value = "录入人id")

    private Integer operid;

    /**
     * 是否下架
     */
    @ApiModelProperty(value = "是否流失 ")

    private Integer status;


}
