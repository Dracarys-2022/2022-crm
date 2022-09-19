package com.mycrm.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mycrm.crm.entity.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Orders;
import com.mycrm.crm.entity.OrdersVo;
import com.mycrm.crm.service.ClientService;
import com.mycrm.crm.service.ContactService;
import com.mycrm.crm.service.OrdersService;
import com.mycrm.crm.service.ProductService;
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
    @Autowired
    ProductService productService;
    @Autowired
    ClientService clientService;
    @Autowired
    ContactService contactService;

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
    // @RequestMapping("add")
    // public Object add(@RequestBody Orders orders) {
    //     Map Rsmap = new HashMap();
    //     Date date = new Date();
    //     DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    //     String dateStr = dateFormat.format(date);
    //     Random random = new Random();
    //     StringBuilder rs = new StringBuilder();
    //     for (int i = 0; i < 3; i++) {
    //         rs.append(random.nextInt(10));
    //     }
    //     String oid = (dateStr + rs);
    //     orders.setOid(oid);
    //     orders.setType(0);
    //     if (ordersService.save(orders)) {
    //         Rsmap.put("msg", "添加成功");
    //         Rsmap.put("code", 0001);// 0001添加成功
    //     } else {
    //         Rsmap.put("msg", "添加失败");
    //         Rsmap.put("code", 0002);// 0001添加成功
    //     }
    //     return Rsmap;
    // }

    // @RequestMapping("list")
    // public Object list(@RequestParam int current, @RequestParam int size) {
    //     Map Rsmap = new HashMap();
    //     Page<Orders> page = new Page<>(current, size);
    //     IPage<Orders> list=ordersService.page(page);
    //     int total = ordersService.total(1);
    //     Rsmap.put("code", 0001);// 0001添加成功
    //     Rsmap.put("data", list);
    //     Rsmap.put("msg", "查询成功");
    //     Rsmap.put("total", total);
    //     return Rsmap;
    // }
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
//            BigDecimal number = new BigDecimal(0);
//            int value=aaa;
//            number=BigDecimal.valueOf((int)value);
//            BigDecimal aa1= list.get(i).getPrice().subtract(number.multiply(product.getPrice()));
//            aa.add(aa1);
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
//            list.get(i).getPrice().subtract(product.getPrice());
//            bb.add(list1.get(j).getPrice().subtract(product.getPrice()));
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
}
