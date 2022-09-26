package com.mycrm.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.entity.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Orders;
import com.mycrm.crm.entity.OrdersVo;
import com.mycrm.crm.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Api(tags = "订单管理") //  tags：你可以当作是这个组的名字。


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
    @Autowired
    ActiveService activeService;
    @Autowired
    UserLogService userLogService;

    @ApiOperation("显示库存信息")
    @GetMapping("toInventory")


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
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"查看了出库操作");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return Rsmap;
    }

    @ApiOperation("已出库库存信息")
    @GetMapping("toInventoryOut")

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

    @ApiOperation("库存不足信息")
    @GetMapping("toInventoryLack")


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


    @ApiOperation("添加订单")
    @PostMapping("/add")


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

    @ApiOperation("查询所有下拉菜单")
    @PostMapping("/getlist")


    @RequestMapping("/getlist")

    public Object getList() {
        System.out.println("kaishicha");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("product", productService.list());
        map.put("c", clientService.list());
        map.put("co", contactService.list());
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"查看了订单录入");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return map;
    }

    @ApiOperation("查询所有订单信息")
    @GetMapping("/list")


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
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" 查看了订单记录");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return map;
    }

    @ApiOperation("请求出库")
    @GetMapping("/request")

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

    @ApiOperation("出库")
    @GetMapping("/out")


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

    @ApiOperation("退换")
    @PostMapping("/return")


    @RequestMapping("/return")

    public Object returnst(@RequestBody Map<Object, String> Datemap) throws ParseException {
        System.out.println("kaishicha");
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Orders> wrapper = new QueryWrapper<Orders>();
        wrapper.eq("oid", Datemap.get("oid"));
        Orders orders = ordersService.getOne(wrapper);
        Inventory inventory = new Inventory();
        inventory.setId((String) Datemap.get("oid"));
        int productid = orders.getPid();
        QueryWrapper<Product> wrapper1 = new QueryWrapper<Product>();
        wrapper1.eq("pid", orders.getPid());
        Product product = productService.getOne(wrapper1);
        inventory.setPrice(product.getPrice());
        inventory.setPid(orders.getPid());
        inventory.setNumbers(orders.getNumbers());
        inventory.setExist(orders.getNumbers());
        inventory.setType(1);
        System.out.println(Datemap.get("productstatue"));
        int state = Integer.parseInt(Datemap.get("productstatue"));
        inventory.setState(state);
        if (state == 0) {
            inventory.setLocation("A");
        } else {
            inventory.setLocation("C");
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(Datemap.get("procreatetime")));
        inventory.setProducetime(new SimpleDateFormat("yyyy-MM-dd").parse(Datemap.get("procreatetime")));
        inventory.setEndtime(new SimpleDateFormat("yyyy-MM-dd").parse(Datemap.get("prodeadline")));
        LocalDateTime createtime = LocalDateTime.now();
        inventory.setCreatetime(createtime);
        if (inventoryService.save(inventory))
            map.put("msg", "添加成功");
        else
            map.put("msg", "添加");
        //等索哥做完，继续完成
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("type", 4).eq("oid", Datemap.get("oid"));
        ordersService.update(updateWrapper);
        Page<OrdersVo> page = new Page<>(Integer.parseInt((String) Datemap.get("current")), Integer.parseInt((String) Datemap.get("size")));
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

    @ApiOperation("完成订单")
    @GetMapping("/complate")


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

    @ApiOperation("查看利润信息")
    @GetMapping("/prefit")


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

    @ApiOperation("查看利润信息")
    @GetMapping("/prefit1")


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
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"查看了利润管理");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return map;
    }

    @ApiOperation("查看所有产品信息")
    @GetMapping("/allpro")


    @RequestMapping("/allpro")

    public Object allPro(@RequestParam int oid) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Product> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("type", oid);
        List<Product> products = productService.list(queryWrapper1);
        map.put("product", products);
        return map;
    }

    @ApiOperation("根据客户查找联系人信息")
    @GetMapping("selectcid")


    @RequestMapping("selectcid")

    public Object selectcid1(@RequestParam int cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Contact> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        List<Contact> list = contactService.list(queryWrapper);
        map.put("data", list);
        return map;
    }
}
