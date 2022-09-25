package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户实体")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oid", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer oid;
    @ApiModelProperty(value = "用户名")
    private String oname;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "权限")
    private String permissions;
    @ApiModelProperty(value = "角色")
    private Integer roid;
    @ApiModelProperty(value = "电话")
    private  String phone;




}
