package com.ly.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.crm.entity.Inventory;
import com.ly.crm.entity.InventoryVo;
import com.ly.crm.entity.Orders;
import com.ly.crm.entity.Product;
import com.ly.crm.service.InventoryService;
import com.ly.crm.service.OrdersService;
import com.ly.crm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    ProductService productService;

    @RequestMapping("add")
    public Object stockIn(@RequestBody Inventory inventory) {
        Map Rsmap = new HashMap();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(date);
        Random random = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            rs.append(random.nextInt(10));
        }
        String order_id = (dateStr + rs);
        inventory.setId(order_id);
        inventory.setExist(inventory.getNumbers());
        System.out.println(inventory);
        if (inventoryService.save(inventory)) {
            Rsmap.put("code", 0001);// 0001添加成功
            Rsmap.put("msg", "入库成功");
        } else {
            Rsmap.put("code", 0002);// 0002添加失败
            Rsmap.put("msg", "入库失败");
        }
        return Rsmap;
    }

    @RequestMapping("query")
    public Object query() {
        Map<Object, Object> Rsmap = new HashMap<Object, Object>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type", 0);
        List list = ordersService.list(wrapper);
        Rsmap.put("data", list);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("msg", "查询成功");
        return Rsmap;
    }

    @RequestMapping("sub")
    public Object stockOut(@RequestParam String oid) {
        Map Rsmap = new HashMap();
        QueryWrapper<Inventory> wrapper = new QueryWrapper<Inventory>();
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("oid", oid);
        Orders orders = ordersService.getOne(qw);
        Integer numbers = orders.getNumbers();
        //先判断该种商品库存是否足够
        int SumExist = inventoryService.sumExixt(orders.getPid());
        if (SumExist > numbers) {
            //库存足够
            wrapper.eq("pid", orders.getPid()).orderByAsc("endtime");
            List<Inventory> list = inventoryService.list(wrapper);
            int i = 0;
            while (numbers != 0) {
                if (list.get(i).getExist() > numbers) {
                    list.get(i).setExist(list.get(i).getExist() - numbers);
                    inventoryService.updateById(list.get(i));
                    numbers = 0;
                } else {
                    numbers = numbers - list.get(i).getExist();
                    list.get(i).setExist(0);
                    inventoryService.updateById(list.get(i));
                    i++;
                }
            }
            orders.setType(2);
            System.out.println(orders);
            ordersService.update(orders, qw);
            Rsmap.put("code", 0001);// 0001添加成功
            Rsmap.put("msg", "出库成功");
        } else {
            Rsmap.put("code", 0002);// 0002添加失败
            Rsmap.put("msg", "出库失败,库存不足");
        }
        return Rsmap;
    }

    @RequestMapping("return")
    public Object returnById(@RequestBody Inventory inventory) {
        Map Rsmap = new HashMap();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(date);
        Random random = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            rs.append(random.nextInt(10));
        }
        String id = (dateStr + rs);
        inventory.setId(id);
        inventory.setExist(inventory.getNumbers());
        System.out.println(inventory);
        if (inventoryService.save(inventory)) {
            Rsmap.put("code", 0001);// 0001添加成功
            Rsmap.put("msg", "退换成功");
        } else {
            Rsmap.put("code", 0002);// 0002添加失败
            Rsmap.put("msg", "退换失败");
        }
        return Rsmap;
    }

    @RequestMapping("list")
    public Object list(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        //QueryWrapper<Inventory> wrapper = new QueryWrapper<>();
        int total = inventoryService.total();
        Page<InventoryVo> page = new Page<>(current, size);
        //wrapper.orderByDesc("createtime");
        IPage<InventoryVo> list = inventoryService.listVo(page);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        return Rsmap;
    }
    @RequestMapping("listNull")
    public Object listNull(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        int total = inventoryService.totalNull();
        Page<InventoryVo> page = new Page<>(current, size);
        IPage<InventoryVo> list = inventoryService.listVoNull(page);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        return Rsmap;
    }
    @RequestMapping("queryById")
    public Object queryById(@RequestBody Map<String, String> map) {
        Map Rsmap = new HashMap();
        String id = map.get("id");
        Inventory inventory = inventoryService.getById(id);
        Rsmap.put("data", inventory);
        return Rsmap;
    }

    @RequestMapping("update")
    public Object update(@RequestBody Inventory inventory) {
        Map Rsmap = new HashMap();
        String id = inventory.getId();
        if (inventoryService.updateById(inventory)) {
            Inventory new_inventory = inventoryService.getById(id);
            Rsmap.put("data", new_inventory);
            Rsmap.put("msg", "修改成功");
            Rsmap.put("code", 0001);
        } else {
            Rsmap.put("msg", "修改失败");
            Rsmap.put("code", 0002);
        }
        return Rsmap;
    }

    @RequestMapping("getproduct")
    public Object getproduct() {
        Map Rsmap = new HashMap();
        QueryWrapper<Product> wrapper=new QueryWrapper();
        wrapper.select("pid","pname");
        List<Product> list=productService.list(wrapper);
        Rsmap.put("data", list);
        return Rsmap;
    }
    @RequestMapping("lack")
    public Object lack(@RequestParam String oid){
        Map Rsmap = new HashMap();
        QueryWrapper<Orders> wrapper=new QueryWrapper();
        wrapper.eq("oid",oid);
        Orders orders=ordersService.getOne(wrapper);
        orders.setType(3);
        if (ordersService.update(orders,wrapper)){
            Rsmap.put("msg", "已通知销售员库存不足");
            Rsmap.put("code", 0001);
        }else {
            Rsmap.put("msg", "修改失败");
            Rsmap.put("code", 0002);
        }
        return Rsmap;
    }
}