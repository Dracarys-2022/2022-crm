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
@ApiModel(description = "角色实体")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "roid", type = IdType.AUTO)
<<<<<<< HEAD
    @ApiModelProperty(value = "主键")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private Integer roid;

    /**
     * 角色名称
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "角儿名称")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String roname;

    /**
     * 权限
     */
<<<<<<< HEAD
    @ApiModelProperty(value = "角色权限")
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String permissions;


}
