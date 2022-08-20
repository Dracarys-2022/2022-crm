package com.ly.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.crm.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.crm.entity.InventoryVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface InventoryMapper extends BaseMapper<Inventory> {
    Integer sumExixt(int pid);
    Integer total();
    Integer totalNull();
    IPage<InventoryVo> listVo(Page<InventoryVo> page);
    IPage<InventoryVo> listVoNull(Page<InventoryVo> page);
}
