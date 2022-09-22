package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Visit;
import com.mycrm.crm.service.VisitService;
<<<<<<< HEAD
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
<<<<<<< HEAD
@Api(tags = "拜访记录管理") //  tags：你可以当作是这个组的名字。
=======
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
@RestController
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    VisitService visitService;
<<<<<<< HEAD
    @ApiOperation("添加记录")
    @PostMapping("/add")
=======
    @RequestMapping("/add")
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    public Object add(@RequestBody Visit visit){
        System.out.println(visit);
        visitService.save(visit);
        return 2;
    }
<<<<<<< HEAD
    @ApiOperation("查看记录")
    @GetMapping("/list")
=======
    @RequestMapping("/list")
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    public Object list(@RequestParam String current,@RequestParam String size){
        System.out.println(current+","+size);
        System.out.println("查询拜访记录");
        Map<String,Object> map=new HashMap<String,Object>();
        Page<Visit> page=new Page<>(Integer.parseInt(current),Integer.parseInt(size));
        IPage<Visit> list=visitService.page(page);
        System.out.println(list);
        map.put("data",list);
        map.put("total",visitService.count());
        return map;
    }
<<<<<<< HEAD
    @ApiOperation("删除记录")
    @PostMapping("/delete")
=======
    @RequestMapping("/delete")
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    public Object delete(@RequestBody Visit visit){
        System.out.println(visit.getVid());
        visitService.removeById(visit.getVid());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("data",visitService.list());
        map.put("total",visitService.count());
        return map;
    }
}
