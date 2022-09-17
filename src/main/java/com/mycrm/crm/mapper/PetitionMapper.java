package com.mycrm.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mycrm.crm.entity.Petition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycrm.crm.entity.PetitionVo;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionMapper extends BaseMapper<Petition> {
    Integer num();
    Integer total();
    IPage<PetitionVo> listPetition(IPage<?> page);
    Integer totalNull();
    IPage<PetitionVo> listPetitionNull(IPage<?> page);
}
