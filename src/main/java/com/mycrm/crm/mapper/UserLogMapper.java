package com.mycrm.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycrm.crm.entity.UserLog;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
public interface UserLogMapper extends BaseMapper<UserLog> {
    public List<UserLog> selectid();
}
