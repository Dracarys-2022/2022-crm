package com.mycrm.crm.service.impl;

import com.mycrm.crm.entity.Product;
import com.mycrm.crm.mapper.ProductMapper;
import com.mycrm.crm.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
