package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;
    /**
     * 用户名
     */
    private String oname;
    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否可以登录
     */
    private Integer status;

    /**
     * 角色
     */
    private Integer roid;


}
