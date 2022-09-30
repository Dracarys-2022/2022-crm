package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.entity.Orders;
import com.mycrm.crm.entity.Product;
import com.mycrm.crm.entity.Service;
import com.mycrm.crm.entity.UserLog;
import com.mycrm.crm.service.ActiveService;
import com.mycrm.crm.service.ProductService;
import com.mycrm.crm.service.UserLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ly
 * @since 2022-07-17
 */
@RestController
@Api(tags = "产品模块控制器")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ActiveService activeService;
    @Autowired
    UserLogService userLogService;

    @GetMapping("list")
    @ApiOperation("获得所有已上架的产品")
    public Object list(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        Page<Product> page = new Page<>(current, size);
        IPage<Product> list = productService.listPro(page, 1);
        int total = productService.total(1);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"查看了产品模块相关操作");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return Rsmap;
    }

    @GetMapping("listOut")
    @ApiOperation("获得所有已下架的产品")
    public Object listOut(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        Page<Product> page = new Page<>(current, size);
        IPage<Product> list = productService.listPro(page, 0);
        int total = productService.total(0);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("data", list);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("total", total);
        return Rsmap;
    }

    @GetMapping("changeStatus")
    @ApiOperation("根据pid修改产品状态")
    public Object changeStatus(@RequestParam int pid) {
        Map Rsmap = new HashMap();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("pid", pid);
        Product product = productService.getOne(wrapper);
        int status = product.getStatus();
        if (status == 1) {
            product.setStatus(0);
            productService.update(product, wrapper);
        } else {
            product.setStatus(1);
            productService.update(product, wrapper);
        }
        Rsmap.put("msg", "修改状态成功");
        Rsmap.put("code", 0001);
        return Rsmap;
    }

    @GetMapping("queryByPid")
    @ApiOperation("根据pid查询产品")
    public Object queryByPid(@RequestParam int pid) {
        Map Rsmap = new HashMap();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("pid", pid);
        Product product = productService.getOne(wrapper);
        Rsmap.put("data", product);
        Rsmap.put("msg", "查询成功");
        Rsmap.put("code", 0001);
        return Rsmap;
    }

    @PostMapping("update")
    @ApiOperation("修改产品")
    public Object update(@RequestBody Product product) {
        Map Rsmap = new HashMap();
        int pid = product.getPid();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("pid", pid);
        productService.update(product, wrapper);
        Rsmap.put("msg", "修改成功");
        Rsmap.put("code", 0001);
        return Rsmap;
    }

    @PostMapping("/add")
    @ApiOperation("添加新产品")
    public Object add(@RequestBody Product product) {
        productService.save(product);
        return 0;
    }
}
