package com.mycrm.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycrm.crm.entity.InventoryVo;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMapper extends BaseMapper<Inventory> {
    Integer sumExixt(int pid);
    Integer total();
    Integer totalNull();
    IPage<InventoryVo> listVo(Page<InventoryVo> page);
    IPage<InventoryVo> listVoNull(Page<InventoryVo> page);
    InventoryVo queryById(String id);

}
