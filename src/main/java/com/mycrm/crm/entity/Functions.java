package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(description = "功能实体")
public class Functions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能id
     */
    @TableId(value = "fid", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer fid;

    /**
     * 功能名称
     */
    @ApiModelProperty(value = "功能名称")
    private String fname;
    @ApiModelProperty(value = "上一级父功能")
    private Integer fatherid;


}
