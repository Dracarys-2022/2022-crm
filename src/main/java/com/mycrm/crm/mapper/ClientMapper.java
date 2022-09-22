package com.mycrm.crm.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycrm.crm.entity.ClientVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface ClientMapper extends BaseMapper<Client> {
    IPage<ClientVo> selectPage(Page<ClientVo> page, @Param(Constants.WRAPPER) Wrapper<ClientVo> queryWrapper);
    ClientVo selectById(@Param(Constants.WRAPPER) Wrapper<ClientVo> queryWrapper);

}
