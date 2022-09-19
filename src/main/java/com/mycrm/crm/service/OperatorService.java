package com.mycrm.crm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycrm.crm.entity.OperatorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface OperatorService extends IService<Operator> {
    IPage<OperatorVo> selectPage(Page<OperatorVo> page, @Param(Constants.WRAPPER) Wrapper<OperatorVo> queryWrapper);
    OperatorVo selectById(@Param(Constants.WRAPPER) Wrapper<OperatorVo> queryWrapper);
    List<OperatorVo> queryList(@Param(Constants.WRAPPER) Wrapper<OperatorVo> queryWrapper);
}
