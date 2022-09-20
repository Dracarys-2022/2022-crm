package com.mycrm.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Inventory;
import com.mycrm.crm.entity.InventoryVo;
import com.mycrm.crm.entity.Orders;
import com.mycrm.crm.entity.Product;
import com.mycrm.crm.service.InventoryService;
import com.mycrm.crm.service.OrdersService;
import com.mycrm.crm.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "库存模块控制器")
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    ProductService productService;

    @PostMapping("add")
    @ApiOperation(value = "入库操作", notes = "增加一条入库信息")
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
        inventory.setType(0);
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

    @GetMapping("query")
    @ApiOperation(value = "查询库存", notes = "查询所有新进库存信息")
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

    @GetMapping("sub")
    @ApiOperation(value = "出库操作", notes = "进行一条数据的出库操作")
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

    @PutMapping("return")
    @ApiOperation(value = "退货操作", notes = "进行一条订单退货后的操作")
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

    @GetMapping("list")
    @ApiOperation(value = "获取库存信息", notes = "获得已出库记录")
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
    @GetMapping("listNull")
    @ApiOperation(value = "未出库记录", notes = "获得全部未出库的记录")
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
    @GetMapping("queryById")
    @ApiOperation(value = "查询库存信息", notes = "查询库存信息")
    public Object queryById(@RequestParam String id) {
        Map Rsmap = new HashMap();
        Inventory inventory = inventoryService.queryById(id);
        Rsmap.put("data", inventory);
        return Rsmap;
    }

    @PostMapping("update")
    @ApiOperation(value = "更新", notes = "更新一条库存信息")
    public Object update(@RequestBody Inventory inventory) {
        Map Rsmap = new HashMap();
        String id = inventory.getId();
        System.out.println(inventory.getPid());
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

    @GetMapping("getproduct")
    @ApiOperation(value = "获得商品名称", notes = "获得商品的名称")
    public Object getproduct() {
        Map Rsmap = new HashMap();
        QueryWrapper<Product> wrapper=new QueryWrapper();
        wrapper.select("pid","pname");
        List<Product> list=productService.list(wrapper);
        Rsmap.put("data", list);
        return Rsmap;
    }
    @GetMapping("lack")
    @ApiOperation(value = "库存不足", notes = "库存量不足进行提醒")
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