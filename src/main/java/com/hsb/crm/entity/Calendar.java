package com.hsb.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Calendar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日程id
     */
    @TableId(value = "caid", type = IdType.AUTO)
    private Integer caid;

    /**
     * 日程内容
     */
    private String info;

    /**
     * 录入人id
     */
    private Integer operid;

    /**
     * 日程时间
     */
    private LocalDateTime datetime;


}
