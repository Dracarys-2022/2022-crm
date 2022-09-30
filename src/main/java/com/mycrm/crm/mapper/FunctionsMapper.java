package com.mycrm.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Functions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycrm.crm.entity.FunctionsVo;
import com.mycrm.crm.entity.OrdersVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface FunctionsMapper extends BaseMapper<Functions> {
    IPage<FunctionsVo> listPage(Page<FunctionsVo> page);
}
