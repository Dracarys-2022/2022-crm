package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Visit;
import com.mycrm.crm.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    VisitService visitService;
    @RequestMapping("/add")
    public Object add(@RequestBody Visit visit){
        System.out.println(visit);
        visitService.save(visit);
        return 2;
    }
    @RequestMapping("/list")
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
    @RequestMapping("/delete")
    public Object delete(@RequestBody Visit visit){
        System.out.println(visit.getVid());
        visitService.removeById(visit.getVid());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("data",visitService.list());
        map.put("total",visitService.count());
        return map;
    }
}
