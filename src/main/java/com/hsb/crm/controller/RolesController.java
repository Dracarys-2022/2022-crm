package com.hsb.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsb.crm.entity.Client;
import com.hsb.crm.entity.Roles;
import com.hsb.crm.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    RolesService rolesService;
    @RequestMapping("/queryList")
    public Object listRoles()
    {
        QueryWrapper<Roles> queryWrapper= new QueryWrapper<>();
        queryWrapper.ne("roname","系统管理员");
        List<Roles> list =rolesService.list(queryWrapper);
        return list;
    }

}
