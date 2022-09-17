package com.mycrm.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycrm.crm.entity.OrdersVo;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
    IPage<OrdersVo> toInventory(IPage<?> page);
    IPage<OrdersVo> toInventoryOut(IPage<?> page);
    IPage<OrdersVo> toInventoryLack(IPage<?> page);
    Integer total(int type);
    IPage<OrdersVo> listPage(Page<OrdersVo> page);
}
