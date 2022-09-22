package com.mycrm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mycrm.crm.entity.Product;
import com.mycrm.crm.mapper.ProductMapper;
import com.mycrm.crm.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    ProductMapper mapper;

    @Override
    public IPage<Product> listPro(IPage<?> page,int status) {
        return mapper.listPro(page, status);
    }

    @Override
    public Integer total(int status) {
        return mapper.total(status);
    }
}
