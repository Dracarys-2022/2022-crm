package com.ly.crm.entity;

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
 * @author ly
 * @since 2022-07-17
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
    private String opername;
    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户身份
     */
    private Integer status;

    /**
     * 是否可以登录
     */
    private Integer use;

    /**
     * 角色
     */
    private Integer roid;


}
