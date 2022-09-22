package com.mycrm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "投诉实体")

public class Petition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "peid", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")

    private Integer peid;

    /**
     * 投诉内容
     */
    @ApiModelProperty(value = "投诉内容")

    private String content;

    /**
     * 记录人id
     */
    @ApiModelProperty(value = "记录人id")

    private Integer operid;

    /**
     * 处理人id
     */
    @ApiModelProperty(value = "处理人id")

    private Integer executor;

    /**
     * 结果反馈
     */
    @ApiModelProperty(value = "结果反馈")

    private String outcome;

    /**
     * 投诉时间
     */
    @ApiModelProperty(value = "投诉时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createtime;

    /**
     * 处理完成时间
     */
    @ApiModelProperty(value = "处理完成时间")
    private LocalDateTime endtime;


}
