package com.hsb.crm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.crm.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.crm.entity.ClientVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface ClientService extends IService<Client> {
    IPage<ClientVo> selectPage(Page<ClientVo> page, @Param(Constants.WRAPPER) Wrapper<ClientVo> queryWrapper);
    ClientVo selectById(@Param(Constants.WRAPPER) Wrapper<ClientVo> queryWrapper);
}
