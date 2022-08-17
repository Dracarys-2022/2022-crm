package com.ly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ly.crm.entity.Orders;
import com.ly.crm.entity.OrdersVo;
import com.ly.crm.mapper.OrdersMapper;
import com.ly.crm.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ly
 * @since 2022-07-17
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Autowired
    OrdersMapper mapper;


    @Override
    public IPage<OrdersVo> toInventory(IPage<?> page) {
        return mapper.toInventory(page);
    }

    @Override
    public IPage<OrdersVo> toInventoryOut(IPage<?> page) {
        return mapper.toInventoryOut(page);
    }

    @Override
    public IPage<OrdersVo> toInventoryLack(IPage<?> page) {
        return mapper.toInventoryLack(page);
    }

    @Override
    public Integer total(int type) {
        return mapper.total(type);
    }
}
