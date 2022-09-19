package com.mycrm.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mycrm.crm.entity.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Orders;
import com.mycrm.crm.entity.OrdersVo;
import com.mycrm.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
    @Autowired
    ProductService productService;
    @Autowired
    ClientService clientService;
    @Autowired
    ContactService contactService;
    @Autowired
    InventoryService inventoryService;

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


    @RequestMapping("/add")
    public Object add(@RequestBody Orders orders) {
        System.out.println(orders);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(date);
        Random random = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            rs.append(random.nextInt(10));
        }
        String c = dateStr + rs;
        orders.setOid(c);
        ordersService.save(orders);
        return 2;
    }

    @RequestMapping("/getlist")
    public Object getList() {
        System.out.println("kaishicha");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("product", productService.list());
        map.put("c", clientService.list());
        map.put("co", contactService.list());
        return map;
    }

    @RequestMapping("/list")
    public Object list(@RequestParam String current, @RequestParam String size) {
        System.out.println("kaishicha");
        Map<String, Object> map = new HashMap<String, Object>();
        Page<OrdersVo> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        IPage<OrdersVo> list = ordersService.listPage(page);
        for (int i = 0; i < list.getRecords().size(); i++) {
            if (list.getRecords().get(i).getState() == 1) {
                list.getRecords().get(i).setStaname("产品");
                if (list.getRecords().get(i).getType() == 0) {
                    list.getRecords().get(i).setTyname("未确认订单");
                } else if (list.getRecords().get(i).getType() == 1) {
                    list.getRecords().get(i).setTyname("订单已确认");
                } else if (list.getRecords().get(i).getType() == 2) {
                    list.getRecords().get(i).setTyname("已出库");
                } else if (list.getRecords().get(i).getType() == 3) {
                    list.getRecords().get(i).setTyname("库存不足");
                } else if (list.getRecords().get(i).getType() == 4) {
                    list.getRecords().get(i).setTyname("退货");
                } else if (list.getRecords().get(i).getType() == 5) {
                    list.getRecords().get(i).setTyname("订单已完成");
                }
            } else if (list.getRecords().get(i).getState() == 2) {
                list.getRecords().get(i).setStaname("服务");
                list.getRecords().get(i).setTyname("已完成服务");
            }
        }
        map.put("data", list);
        map.put("total", ordersService.count());
        return map;
    }

    @RequestMapping("/request")
    public Object request(@RequestParam String oid, @RequestParam String current, @RequestParam String size) {
        System.out.println("kaishicha");
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("type", 1).eq("oid", oid);
        ordersService.update(updateWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        Page<OrdersVo> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        IPage<OrdersVo> list = ordersService.listPage(page);
        for (int i = 0; i < list.getRecords().size(); i++) {
            if (list.getRecords().get(i).getState() == 1) {
                list.getRecords().get(i).setStaname("产品");
                if (list.getRecords().get(i).getType() == 0) {
                    list.getRecords().get(i).setTyname("未确认订单");
                } else if (list.getRecords().get(i).getType() == 1) {
                    list.getRecords().get(i).setTyname("订单已确认");
                } else if (list.getRecords().get(i).getType() == 2) {
                    list.getRecords().get(i).setTyname("已出库");
                } else if (list.getRecords().get(i).getType() == 3) {
                    list.getRecords().get(i).setTyname("库存不足");
                } else if (list.getRecords().get(i).getType() == 4) {
                    list.getRecords().get(i).setTyname("退货");
                } else if (list.getRecords().get(i).getType() == 5) {
                    list.getRecords().get(i).setTyname("订单已完成");
                }
            } else if (list.getRecords().get(i).getState() == 2) {
                list.getRecords().get(i).setStaname("服务");
                list.getRecords().get(i).setTyname("已完成服务");
            }
        }
        map.put("data", list);
        map.put("total", ordersService.count());
        return map;
    }

    @RequestMapping("/out")
    public Object out(@RequestParam String oid, @RequestParam String current, @RequestParam String size) {
        System.out.println("kaishicha");
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("type", 2).eq("oid", oid);
        ordersService.update(updateWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        Page<OrdersVo> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        IPage<OrdersVo> list = ordersService.listPage(page);
        for (int i = 0; i < list.getRecords().size(); i++) {
            if (list.getRecords().get(i).getState() == 1) {
                list.getRecords().get(i).setStaname("产品");
                if (list.getRecords().get(i).getType() == 0) {
                    list.getRecords().get(i).setTyname("未确认订单");
                } else if (list.getRecords().get(i).getType() == 1) {
                    list.getRecords().get(i).setTyname("订单已确认");
                } else if (list.getRecords().get(i).getType() == 2) {
                    list.getRecords().get(i).setTyname("已出库");
                } else if (list.getRecords().get(i).getType() == 3) {
                    list.getRecords().get(i).setTyname("库存不足");
                } else if (list.getRecords().get(i).getType() == 4) {
                    list.getRecords().get(i).setTyname("退货");
                } else if (list.getRecords().get(i).getType() == 5) {
                    list.getRecords().get(i).setTyname("订单已完成");
                }
            } else if (list.getRecords().get(i).getState() == 2) {
                list.getRecords().get(i).setStaname("服务");
                list.getRecords().get(i).setTyname("已完成服务");
            }
        }
        map.put("data", list);
        map.put("total", ordersService.count());
        return map;
    }

    @RequestMapping("/return")
    public Object returnst(@RequestParam String oid, @RequestParam String current, @RequestParam String size) {
        System.out.println("kaishicha");
        QueryWrapper<Orders> wrapper = new QueryWrapper<Orders>();
        wrapper.eq("oid", oid);
        Orders orders = ordersService.getOne(wrapper);
        Inventory inventory = new Inventory();
        inventory.setId(oid);
        QueryWrapper<Product> wrapper1 = new QueryWrapper<Product>();
        wrapper.eq("pid", orders.getPid());
        Product product = productService.getOne(wrapper1);
        inventory.setPrice(product.getPrice());
        inventory.setPid(orders.getPid());
        inventory.setNumbers(orders.getNumbers());
        inventory.setExist(orders.getNumbers());
        inventory.setType(1);
        if (inventory.getState() == 0) {
            inventory.setLocation("A");
        } else {
            inventory.setLocation("C");
        }
        //等索哥做完，继续完成
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();

        updateWrapper.set("type", 4).eq("oid", oid);
        ordersService.update(updateWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        Page<OrdersVo> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        IPage<OrdersVo> list = ordersService.listPage(page);
        for (int i = 0; i < list.getRecords().size(); i++) {
            if (list.getRecords().get(i).getState() == 1) {
                list.getRecords().get(i).setStaname("产品");
                if (list.getRecords().get(i).getType() == 0) {
                    list.getRecords().get(i).setTyname("未确认订单");
                } else if (list.getRecords().get(i).getType() == 1) {
                    list.getRecords().get(i).setTyname("订单已确认");
                } else if (list.getRecords().get(i).getType() == 2) {
                    list.getRecords().get(i).setTyname("已出库");
                } else if (list.getRecords().get(i).getType() == 3) {
                    list.getRecords().get(i).setTyname("库存不足");
                } else if (list.getRecords().get(i).getType() == 4) {
                    list.getRecords().get(i).setTyname("退货");
                } else if (list.getRecords().get(i).getType() == 5) {
                    list.getRecords().get(i).setTyname("订单已完成");
                }
            } else if (list.getRecords().get(i).getState() == 2) {
                list.getRecords().get(i).setStaname("服务");
                list.getRecords().get(i).setTyname("已完成服务");
            }
        }
        map.put("data", list);
        map.put("total", ordersService.count());
        return map;
    }

    @RequestMapping("/complate")
    public Object complate(@RequestParam String oid, @RequestParam String current, @RequestParam String size) {
        System.out.println("kaishicha");
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("type", 5).eq("oid", oid);
        ordersService.update(updateWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        Page<OrdersVo> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));
        IPage<OrdersVo> list = ordersService.listPage(page);
        for (int i = 0; i < list.getRecords().size(); i++) {
            if (list.getRecords().get(i).getState() == 1) {
                list.getRecords().get(i).setStaname("产品");
                if (list.getRecords().get(i).getType() == 0) {
                    list.getRecords().get(i).setTyname("未确认订单");
                } else if (list.getRecords().get(i).getType() == 1) {
                    list.getRecords().get(i).setTyname("订单已确认");
                } else if (list.getRecords().get(i).getType() == 2) {
                    list.getRecords().get(i).setTyname("已出库");
                } else if (list.getRecords().get(i).getType() == 3) {
                    list.getRecords().get(i).setTyname("库存不足");
                } else if (list.getRecords().get(i).getType() == 4) {
                    list.getRecords().get(i).setTyname("退货");
                } else if (list.getRecords().get(i).getType() == 5) {
                    list.getRecords().get(i).setTyname("订单已完成");
                }
            } else if (list.getRecords().get(i).getState() == 2) {
                list.getRecords().get(i).setStaname("服务");
                list.getRecords().get(i).setTyname("已完成服务");
            }
        }
        map.put("data", list);
        map.put("total", ordersService.count());
        return map;
    }

    @RequestMapping("/prefit")
    public Object prefit() {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", 5);
        List<Orders> list = ordersService.list(queryWrapper);
        int aa1 = 0;
        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i).getPid();
            Product product = productService.getById(a);
            int aaa = list.get(i).getNumbers();
            int aa = list.get(i).getPrice().intValue() - (aaa * product.getPrice().intValue());
            aa1 = aa1 + aa;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("product", aa1);
        return map;
    }

    @RequestMapping("/prefit1")
    public Object prefit1() {
        QueryWrapper<Orders> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("state", 2);
        List<Orders> list1 = ordersService.list(queryWrapper1);
        int bb = 0;
        for (int j = 0; j < list1.size(); j++) {
            int a = list1.get(j).getPid();
            Product product = productService.getById(a);
            int aa = list1.get(j).getPrice().intValue() - (product.getPrice().intValue());
            bb = bb + aa;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("service", bb);
        return map;
    }

    @RequestMapping("/allpro")
    public Object allPro(@RequestParam int oid) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Product> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("type", oid);
        List<Product> products = productService.list(queryWrapper1);
        map.put("product", products);
        return map;
    }
    @RequestMapping("selectcid")
    public Object selectcid1(@RequestParam int cid){
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<Contact> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cid",cid);
        List<Contact> list=contactService.list(queryWrapper);
        map.put("data",list);
        return map;
    }
}
