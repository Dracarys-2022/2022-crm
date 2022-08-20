package com.ly.crm.service.impl;

import com.ly.crm.entity.Contact;
import com.ly.crm.mapper.ContactMapper;
import com.ly.crm.service.ContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ly
 * @since 2022-07-17
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

}
