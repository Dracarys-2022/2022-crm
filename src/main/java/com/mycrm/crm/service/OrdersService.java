package com.mycrm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycrm.crm.entity.OrdersVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ly
 * @since 2022-07-17
 */
public interface OrdersService extends IService<Orders> {
    IPage<OrdersVo> toInventory(IPage<?> page);
    IPage<OrdersVo> toInventoryOut(IPage<?> page);
    IPage<OrdersVo> toInventoryLack(IPage<?> page);
    Integer total(int type);
    IPage<OrdersVo> listPage(Page<OrdersVo> page);
}
