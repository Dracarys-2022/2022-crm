package com.hsb.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.crm.entity.Client;
import com.hsb.crm.entity.ClientVo;
import com.hsb.crm.mapper.ClientMapper;
import com.hsb.crm.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
    @Autowired
    ClientMapper clientMapper;
    @Override
    public IPage<ClientVo> selectPage(Page<ClientVo> page, Wrapper<ClientVo> queryWrapper) {
        return clientMapper.selectPage(page,queryWrapper);
    }

    @Override
    public ClientVo selectById(Wrapper<ClientVo> queryWrapper) {
        return clientMapper.selectById(queryWrapper);
    }
}
