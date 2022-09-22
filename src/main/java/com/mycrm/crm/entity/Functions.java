package com.mycrm.crm.entity;

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
 * 
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
<<<<<<< HEAD
@ApiModel(description = "功能实体")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
public class Functions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能id
     */
    @TableId(value = "fid", type = IdType.AUTO)
<<<<<<< HEAD
    @ApiModelProperty(value = "主键")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer fid;

    /**
     * 功能名称
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "功能名称")
    private String fname;
    @ApiModelProperty(value = "上一级父功能")
=======
    private String fname;
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer fatherid;


}
