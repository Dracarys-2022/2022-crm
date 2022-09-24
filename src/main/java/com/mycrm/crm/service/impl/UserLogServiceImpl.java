package com.mycrm.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycrm.crm.entity.UserLog;
import com.mycrm.crm.mapper.UserLogMapper;
import com.mycrm.crm.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {
    @Autowired
    UserLogMapper mapper;
    @Override
    public List<UserLog> selectid() {
        return mapper.selectid();
    }
}
