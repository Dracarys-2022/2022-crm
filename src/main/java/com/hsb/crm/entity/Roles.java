package com.hsb.crm.entity;

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
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "roid", type = IdType.AUTO)
    private Integer roid;

    /**
     * 角色名称
     */
    private String roname;

    /**
     * 权限
     */
    private String permissions;


}
