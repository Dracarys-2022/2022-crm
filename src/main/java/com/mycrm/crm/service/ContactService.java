package com.mycrm.crm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Contact;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycrm.crm.entity.ContactVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface ContactService extends IService<Contact> {
    IPage<ContactVo> selectPage(Page<ContactVo> page, @Param(Constants.WRAPPER) Wrapper<ContactVo> queryWrapper);
    ContactVo selectById(@Param(Constants.WRAPPER) Wrapper<ContactVo> queryWrapper);
}
