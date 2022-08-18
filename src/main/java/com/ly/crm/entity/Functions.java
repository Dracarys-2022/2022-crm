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
public class Functions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能id
     */
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    /**
     * 功能名称
     */
    private String fname;


}
