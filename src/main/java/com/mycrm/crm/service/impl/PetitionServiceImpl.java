package com.mycrm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mycrm.crm.entity.Petition;
import com.mycrm.crm.entity.PetitionVo;
import com.mycrm.crm.mapper.PetitionMapper;
import com.mycrm.crm.service.PetitionService;
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
public class PetitionServiceImpl extends ServiceImpl<PetitionMapper, Petition> implements PetitionService {
    @Autowired
    private PetitionMapper mapper;

    @Override
    public Integer num() {
        return mapper.num();
    }

    @Override
    public Integer total() {
        return mapper.total();
    }

    @Override
    public IPage<PetitionVo> listPetition(IPage<?> page) {
        return mapper.listPetition(page);
    }

    @Override
    public Integer totalNull() {
        return mapper.totalNull();
    }

    @Override
    public IPage<PetitionVo> listPetitionNull(IPage<?> page) {
        return mapper.listPetitionNull(page);
    }
}
