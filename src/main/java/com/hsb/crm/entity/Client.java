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
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户编号
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 客户名称
     */
    private String cname;

    /**
     * 法人
     */
    private String legalperson;

    /**
     * 注册资金
     */
    private String registered;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 客户分类
     */
    private String category;

    /**
     * 银行账户
     */
    private String account;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 公司属性
     */
    private String quality;

    /**
     * 备注
     */
    private String remark;

    /**
     * 录入时间
     */
    private LocalDateTime createtime;

    /**
     * 最后修改日期
     */
    private LocalDateTime updatetime;

    private Integer operid;

    /**
     * 是否下架
     */
    private Integer status;


}
