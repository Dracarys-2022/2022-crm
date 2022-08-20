package com.hsb.crm.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.crm.entity.Contact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsb.crm.entity.ContactVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface ContactMapper extends BaseMapper<Contact> {
    IPage<ContactVo> selectPage(Page<ContactVo> page,@Param(Constants.WRAPPER) Wrapper<ContactVo> queryWrapper);
    ContactVo selectById(@Param(Constants.WRAPPER) Wrapper<ContactVo> queryWrapper);

}
