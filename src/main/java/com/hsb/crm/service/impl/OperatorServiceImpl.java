package com.hsb.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.crm.entity.Operator;
import com.hsb.crm.entity.OperatorVo;
import com.hsb.crm.mapper.OperatorMapper;
import com.hsb.crm.service.OperatorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Service
public class OperatorServiceImpl extends ServiceImpl<OperatorMapper, Operator> implements OperatorService {
    @Autowired
    OperatorMapper operatorMapper;
    @Override
    public IPage<OperatorVo> selectPage(Page<OperatorVo> page, Wrapper<OperatorVo> queryWrapper) {
        return operatorMapper.selectPage(page,queryWrapper);
    }

    @Override
    public OperatorVo selectById(Wrapper<OperatorVo> queryWrapper) {
        return operatorMapper.selectById(queryWrapper);
    }

    @Override
    public List<OperatorVo> queryList(Wrapper<OperatorVo> queryWrapper) {
        return operatorMapper.queryList(queryWrapper);
    }
}
