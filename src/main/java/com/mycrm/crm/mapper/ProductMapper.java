package com.mycrm.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mycrm.crm.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {
    IPage<Product> listPro(IPage<?> page,int status);
    Integer total(int  status);
}
