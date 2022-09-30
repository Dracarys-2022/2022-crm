package com.mycrm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Functions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycrm.crm.entity.FunctionsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface FunctionsService extends IService<Functions> {
    IPage<FunctionsVo> listPage(Page<FunctionsVo> page);
}
