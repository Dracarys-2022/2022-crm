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

@ApiModel(description = "角色实体")


public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "roid", type = IdType.AUTO)

    @ApiModelProperty(value = "主键")

    private Integer roid;

    /**
     * 角色名称
     */

    @ApiModelProperty(value = "角儿名称")


    private String roname;

    /**
     * 权限
     */

    @ApiModelProperty(value = "角色权限")

    private String permissions;


}
