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
public class Petition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "peid", type = IdType.AUTO)
    private Integer peid;

    /**
     * 投诉内容
     */
    private String content;

    /**
     * 记录人id
     */
    private Integer operid;

    /**
     * 处理人id
     */
    private Integer executor;

    /**
     * 结果反馈
     */
    private String outcome;

    /**
     * 投诉时间
     */
    private LocalDateTime createtime;

    /**
     * 处理完成时间
     */
    private LocalDateTime endtime;


}
