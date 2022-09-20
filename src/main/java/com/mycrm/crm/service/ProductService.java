package com.mycrm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mycrm.crm.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
public interface ProductService extends IService<Product> {
    IPage<Product> listPro(IPage<?> page,int status);
    Integer total(int  status);
}
