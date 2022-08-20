package com.ly.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ly.crm.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.crm.entity.OrdersVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
    IPage<OrdersVo> toInventory(IPage<?> page);
    IPage<OrdersVo> toInventoryOut(IPage<?> page);
    IPage<OrdersVo> toInventoryLack(IPage<?> page);
    Integer total(int type);
}
