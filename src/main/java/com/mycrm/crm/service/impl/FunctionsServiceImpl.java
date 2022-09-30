package com.mycrm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Functions;
import com.mycrm.crm.entity.FunctionsVo;
import com.mycrm.crm.mapper.FunctionsMapper;
import com.mycrm.crm.service.FunctionsService;
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
public class FunctionsServiceImpl extends ServiceImpl<FunctionsMapper, Functions> implements FunctionsService {
    @Autowired
    FunctionsMapper mapper;
    @Override
    public IPage<FunctionsVo> listPage(Page<FunctionsVo> page) {
        return mapper.listPage(page);
    }
}
