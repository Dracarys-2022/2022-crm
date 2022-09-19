package com.mycrm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mycrm.crm.entity.Petition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycrm.crm.entity.PetitionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ly
 * @since 2022-07-17
 */
public interface PetitionService extends IService<Petition> {
    Integer num();
    Integer total();
    IPage<PetitionVo> listPetition(IPage<?> page);
    Integer totalNull();
    IPage<PetitionVo> listPetitionNull(IPage<?> page);
}
