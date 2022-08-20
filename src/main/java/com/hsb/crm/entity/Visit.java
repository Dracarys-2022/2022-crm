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
public class Visit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 拜访编号
     */
    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    /**
     * 所属公司
     */
    private String company;

    /**
     * 拜访客户
     */
    private String client;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 陪同人员
     */
    private String accompany;

    /**
     * 拜访主题
     */
    private String theme;

    /**
     * 拜访目的
     */
    private String purpose;

    /**
     * 拜访前准备
     */
    private String ready;

    /**
     * 拜访结果
     */
    private String outcome;

    /**
     * 客户态度
     */
    private String attitude;

    /**
     * 活动经费
     */
    private String fund;

    /**
     * 备注
     */
    private String remark;

    /**
     * 记录人id
     */
    private Integer operid;

    /**
     * 拜访时间
     */
    private LocalDateTime createtime;


}
