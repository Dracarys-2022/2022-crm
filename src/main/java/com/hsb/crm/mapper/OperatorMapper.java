package com.hsb.crm.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.crm.entity.Operator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsb.crm.entity.OperatorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface OperatorMapper extends BaseMapper<Operator> {
    IPage<OperatorVo> selectPage(Page<OperatorVo> page, @Param(Constants.WRAPPER) Wrapper<OperatorVo> queryWrapper);
    OperatorVo selectById(@Param(Constants.WRAPPER) Wrapper<OperatorVo> queryWrapper);
    List<OperatorVo> queryList(@Param(Constants.WRAPPER) Wrapper<OperatorVo> queryWrapper);
}
