package com.ly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.crm.entity.Inventory;
import com.ly.crm.entity.InventoryVo;
import com.ly.crm.mapper.InventoryMapper;
import com.ly.crm.service.InventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {
    @Autowired InventoryMapper mapper;
    @Override
    public Integer sumExixt(int pid) {
        return mapper.sumExixt(pid);
    }

    @Override
    public Integer total() {
        return mapper.total();
    }

    @Override
    public Integer totalNull() {
        return mapper.totalNull();
    }

    @Override
    public IPage<InventoryVo> listVo(Page<InventoryVo> page) {
        return mapper.listVo(page);
    }

    @Override
    public IPage<InventoryVo> listVoNull(Page<InventoryVo> page) {
        return mapper.listVoNull(page);
    }
}
