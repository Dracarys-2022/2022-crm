package com.mycrm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Orders;
import com.mycrm.crm.entity.OrdersVo;
import com.mycrm.crm.mapper.OrdersMapper;
import com.mycrm.crm.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public IPage<OrdersVo> listPage(Page<OrdersVo> page) {
        return mapper.listPage(page);
    }
}
