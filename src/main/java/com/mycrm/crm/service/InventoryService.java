package com.mycrm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycrm.crm.entity.InventoryVo;


public interface InventoryService extends IService<Inventory> {
    Integer sumExixt(int pid);
    Integer total();
    Integer totalNull();
    IPage<InventoryVo> listVo(Page<InventoryVo> page);
    IPage<InventoryVo> listVoNull(Page<InventoryVo> page);
    InventoryVo queryById(String id);
}
