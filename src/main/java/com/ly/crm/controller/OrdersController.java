package com.ly.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.crm.entity.Orders;
import com.ly.crm.entity.OrdersVo;
import com.ly.crm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @RequestMapping("toInventory")
    public Object toInventory(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        Page<OrdersVo> page = new Page<>(current, size);
        IPage<OrdersVo> list = ordersService.toInventory(page);
        int total = ordersService.total(1);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        return Rsmap;
    }

    @RequestMapping("toInventoryOut")
    public Object toInventoryOut(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        Page<OrdersVo> page = new Page<>(current, size);
        IPage<OrdersVo> list = ordersService.toInventoryOut(page);
        int total = ordersService.total(2);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        return Rsmap;
    }
    @RequestMapping("toInventoryLack")
    public Object toInventoryLack(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        Page<OrdersVo> page = new Page<>(current, size);
        IPage<OrdersVo> list = ordersService.toInventoryLack(page);
        int total = ordersService.total(3);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        return Rsmap;
    }
    @RequestMapping("add")
    public Object add(@RequestBody Orders orders) {
        Map Rsmap = new HashMap();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(date);
        Random random = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            rs.append(random.nextInt(10));
        }
        String oid = (dateStr + rs);
        orders.setOid(oid);
        orders.setType(0);
        if (ordersService.save(orders)) {
            Rsmap.put("msg", "添加成功");
            Rsmap.put("code", 0001);// 0001添加成功
        } else {
            Rsmap.put("msg", "添加失败");
            Rsmap.put("code", 0002);// 0001添加成功
        }
        return Rsmap;
    }

    @RequestMapping("list")
    public Object list(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        Page<Orders> page = new Page<>(current, size);
        IPage<Orders> list=ordersService.page(page);
        int total = ordersService.total(1);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        return Rsmap;
    }
}
