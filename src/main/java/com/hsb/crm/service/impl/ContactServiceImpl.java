package com.hsb.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.crm.entity.Contact;
import com.hsb.crm.entity.ContactVo;
import com.hsb.crm.mapper.ContactMapper;
import com.hsb.crm.service.ContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {
    @Autowired
    ContactMapper contactMapper;
    @Override
    public IPage<ContactVo> selectPage(Page<ContactVo> page, Wrapper<ContactVo> queryWrapper) {
        return contactMapper.selectPage(page,queryWrapper);
    }

    @Override
    public ContactVo selectById(@Param(Constants.WRAPPER) Wrapper<ContactVo> queryWrapper) {
        return contactMapper.selectById(queryWrapper);
    }
}
