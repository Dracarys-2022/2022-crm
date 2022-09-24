package com.mycrm.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mycrm.crm.entity.UserLog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
public interface UserLogService extends IService<UserLog> {
 public List<UserLog> selectid();
}
