package com.ly.crm.entity;

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
 * @author ly
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coid", type = IdType.AUTO)
    private Integer coid;

    /**
     * 公司id
     */
    private Integer cid;

    /**
     * 客户名称
     */
    private String coname;

    /**
     * 家庭地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 录入时间
     */
    private LocalDateTime createtime;

    /**
     * 最后修改时间
     */
    private LocalDateTime updatetime;

    /**
     * 录入人id
     */
    private Integer operid;

    /**
     * 是否下架
     */
    private Integer status;


}
