package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycrm.crm.entity.Roles;
import com.mycrm.crm.service.RolesService;
import com.mycrm.crm.entity.Functions;
import com.mycrm.crm.service.FunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Autowired
    FunctionsService functionsService;
    @RequestMapping("/add")
    public Object add(@RequestBody Roles roles){
        System.out.println(roles);
        rolesService.save(roles);
        return 2;
    }
    @RequestMapping("/list")
    public Object list(){
        System.out.println("查询数据");
        Map<String,Object> map=new HashMap<String,Object>();
        List<Roles> list=rolesService.list();
        for (int i = 0; i <list.size() ; i++) {
            String a=list.get(i).getPermissions();
            if (a!=null){
                String[] aa= a.split(",");
                StringBuilder sb=new StringBuilder();
                for (int j = 0; j < aa.length; j++) {
                    Functions f=functionsService.getById(Integer.parseInt(aa[j]));
                    sb.append(f.getFname()).append(",");
                }
                list.get(i).setPermissions(sb.toString());
            }
        }
        map.put("one",list);
        map.put("two","查询成功！");
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        return map.get("one");
    }
    @RequestMapping("/delete")
    public Object delete(@RequestBody Roles roles){
        System.out.println("删除数据");
        System.out.println(roles.getRoid());
        rolesService.removeById(roles.getRoid());
        return rolesService.list();
    }
    @RequestMapping("/update")
    public Object update(@RequestBody Roles roles){
        System.out.println("查看某个数据");
        System.out.println(roles.getRoid());
        System.out.println(rolesService.getById(roles.getRoid()));
        return rolesService.getById(roles.getRoid());
    }
    @RequestMapping("/roleOne")
    public Object roleOne(@RequestBody Roles roles){
        System.out.println("查看某个数据");
        System.out.println(roles.getRoid());
        System.out.println(rolesService.getById(roles.getRoid()));
        return rolesService.getById(roles.getRoid());
    }
    @RequestMapping("/updateone")
    public Object updateone(@RequestBody Roles roles){
        System.out.println("修改数据");
        System.out.println(roles.getRoid()+','+roles.getRoname());
        QueryWrapper<Roles> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("roid",roles.getRoid());
        rolesService.update(roles,queryWrapper);
        return 0;
    }
}
