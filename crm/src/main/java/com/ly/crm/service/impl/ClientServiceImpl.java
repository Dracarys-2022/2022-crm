package com.ly.crm.service.impl;

import com.ly.crm.entity.Client;
import com.ly.crm.mapper.ClientMapper;
import com.ly.crm.service.ClientService;
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
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

}
