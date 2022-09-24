package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycrm.crm.entity.Functions;
import com.mycrm.crm.entity.Roles;
import com.mycrm.crm.service.FunctionsService;
import com.mycrm.crm.service.RolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ly
 * @since 2022-07-17
 */
@Api(tags = "功能管理") //  tags：你可以当作是这个组的名字。
@RestController
@RequestMapping("/functions")
public class FunctionsController {
    @Autowired
    FunctionsService functionsService;
    @Autowired
    RolesService rolesService;
    @ApiOperation("功能的添加")
    @PostMapping("/add")
    public Object add(@RequestBody Functions functions){
        System.out.println(functions);
        Map<String,Object> map=new HashMap<String,Object>();
        if (functionsService.save(functions)){
            map.put("msg","添加成功！");
        }else{
            map.put("msg","添加失败！");
        }
        return map;
    }
    @PostMapping("/add1")
    public Object add1(@RequestBody Functions functions){
        System.out.println(functions.getFatherid());
        Map<String,Object> map=new HashMap<String,Object>();
        if (functionsService.save(functions)){
            map.put("msg","添加成功！");
        }else{
            map.put("msg","添加失败！");
        }
        return map;
    }
    @PostMapping("/list")
    @GetMapping("/list")
    public Object list(){
        System.out.println("查询数据");
//        System.out.println(rolesService.list());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("one",functionsService.list());
        map.put("two","查询成功！");
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        return map.get("one");
    }
    @PostMapping("/listFunction")

    @GetMapping("/listFunction")
    public Object listFunction(){
        System.out.println("查询数据");
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<Functions> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("fatherid",0);
        map.put("one",functionsService.list(queryWrapper));
        map.put("two","查询成功！");
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        return map.get("one");
    }
    @PostMapping("/listFunction1")
    @GetMapping("/listFunction1")
//    public Object listFunction1(@RequestParam("fid") int fid){
        public Object listFunction1(@RequestBody Functions functions){
        System.out.println("查询数据");
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<Functions> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("fatherid",functions.getFid());
        map.put("one",functionsService.list(queryWrapper));
        map.put("two","查询成功！");
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        return map.get("one");
    }
    @PostMapping("/delete")
    @GetMapping("/delete")
    public Object delete(@RequestBody Functions functions){
        System.out.println("删除数据");
        System.out.println(functions.getFid());
        functionsService.removeById(functions.getFid());
        return functionsService.list();
    }
    @PostMapping("/update")
    @GetMapping("/update")
    public Object update(@RequestBody Functions functions){
        System.out.println("查看某个数据");
        System.out.println(functions.getFid());
        System.out.println(functionsService.getById(functions.getFid()));
        return functionsService.getById(functions.getFid());
    }
    @PostMapping("/updateone")
    @GetMapping("/updateone")
    public Object updateone(@RequestBody Functions functions){
        System.out.println("修改数据");
        System.out.println(functions.getFid()+','+functions.getFname());
        QueryWrapper<Functions> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("fid",functions.getFid());
        functionsService.update(functions,queryWrapper);
        return 0;
    }
    @PostMapping("/beSureFunctions")
    @GetMapping("/beSureFunctions")
    public Object beSureFunctions(@RequestBody Roles roles){
        System.out.println("修改数据");
        System.out.println(roles.getRoid()+','+roles.getPermissions());
        QueryWrapper<Roles> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("roid",roles.getRoid());
        rolesService.update(roles,queryWrapper);
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
        return list;
    }
}
