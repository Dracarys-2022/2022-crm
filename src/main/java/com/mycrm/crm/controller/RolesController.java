package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.entity.Roles;
import com.mycrm.crm.entity.UserLog;
import com.mycrm.crm.service.ActiveService;
import com.mycrm.crm.service.RolesService;
import com.mycrm.crm.entity.Functions;
import com.mycrm.crm.service.FunctionsService;

import com.mycrm.crm.service.UserLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

@Api(tags = "角色管理") //  tags：你可以当作是这个组的名字。


@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    RolesService rolesService;
    @Autowired
    FunctionsService functionsService;
    @Autowired
    ActiveService activeService;
    @Autowired
    UserLogService userLogService;


    @PostMapping("/queryList")
    @ApiOperation("查看角色")
    public Object listRoles()
    {
        QueryWrapper<Roles> queryWrapper= new QueryWrapper<>();
        queryWrapper.ne("roname","系统管理员");
        List<Roles> list =rolesService.list(queryWrapper);
        return list;
    }



    @PostMapping("/add")
    @ApiOperation("添加角色")
    public Object add(@RequestBody Roles roles){
        System.out.println(roles);
        Map<String,Object> map=new HashMap<String,Object>();
        if (rolesService.save(roles)){
            map.put("msg","添加成功！");
        }else{
            map.put("msg","添加失败！");
        }
        return map;
    }

    @PostMapping("/list")
    @ApiOperation("查看角色")
    public Object list(){
        System.out.println("查询数据");
        Map<String,Object> map=new HashMap<String,Object>();
        List<Roles> list=rolesService.list();
        for (int i = 0; i <list.size() ; i++) {
            String a=list.get(i).getPermissions();
            if (a!=null & !"".equals(a)){
                String[] aa= a.split(",");
                StringBuilder sb=new StringBuilder();
                for (int j = 0; j < aa.length; j++) {
                    Functions f=functionsService.getById(Integer.parseInt(aa[j]));
                    sb.append(f.getFname()).append(",");
                }
                System.out.println(sb);
                list.get(i).setPermissions(sb.toString());
            }
        }
        map.put("one",list);
        map.put("two","查询成功！");
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"查看了角色管理相关操作");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));
        return map.get("one");
    }


    @PostMapping("/delete")
    @ApiOperation("删除角色")
    public Object delete(@RequestBody Roles roles){
        System.out.println("删除数据");
        System.out.println(roles.getRoid());
        rolesService.removeById(roles.getRoid());
        return rolesService.list();
    }


    @PostMapping("/update")
    @ApiOperation("修改角色信息")
    public Object update(@RequestBody Roles roles){
        System.out.println("查看某个数据");
        System.out.println(roles.getRoid());
        System.out.println(rolesService.getById(roles.getRoid()));
        return rolesService.getById(roles.getRoid());
    }


    @PostMapping("/roleOne")
    @ApiOperation("查看某个角色信息")
    public Object roleOne(@RequestBody Roles roles){
        System.out.println("查看某个数据");
        System.out.println(roles.getRoid());
        System.out.println(rolesService.getById(roles.getRoid()));
        return rolesService.getById(roles.getRoid());
    }


    @PostMapping("/updateone")
    @ApiOperation("修改角色信息")
    public Object updateone(@RequestBody Roles roles){
        System.out.println("修改数据");
        System.out.println(roles.getRoid()+','+roles.getRoname());
        QueryWrapper<Roles> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("roid",roles.getRoid());
        rolesService.update(roles,queryWrapper);
        return 0;
    }
}
