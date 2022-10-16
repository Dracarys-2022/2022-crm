package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.entity.Operator;
import com.mycrm.crm.entity.User;
import com.mycrm.crm.entity.UserLog;
import com.mycrm.crm.service.OperatorService;
import com.mycrm.crm.service.UserLogService;
import com.mycrm.crm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@Slf4j
@RestController
@RequestMapping("/user-log")
@Api(value = "用户日志控制器")
public class UserLogController {
    @Autowired
    UserLogService service;
    @Autowired
    UserService userService;
    @Autowired
    OperatorService operatorService;
    @GetMapping("select")
    @ApiOperation(value = "用户行为分页")
    public Page userlog(Integer page, Integer pagesize, String name){
       Page<UserLog> pageInfo=new Page<>(page,pagesize);
        int a=name.indexOf("-");
        int b;
        if(name=="合法用户"){
            b=1;
        }if(name=="不合法用户") {
            b=0;
        }else {
            b=99;
        }
        if(a==-1){
        }else {
            QueryWrapper<UserLog> queryWrapper=new QueryWrapper<>();
            queryWrapper.like(StringUtils.isNotEmpty(name),"create_time",name);
            queryWrapper.orderByDesc("create_time");
            service.page(pageInfo,queryWrapper);
            return pageInfo;
        }
        if(b==1||b==0){
            QueryWrapper<UserLog> queryWrapper2=new QueryWrapper<>();
            queryWrapper2.eq("status",b);
            queryWrapper2.orderByDesc("create_time");
            service.page(pageInfo,queryWrapper2);
            return pageInfo;
        }
        else {
            QueryWrapper<UserLog> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.like(StringUtils.isNotEmpty(name), "user", name);
            queryWrapper1.orderByDesc("create_time");
            service.page(pageInfo, queryWrapper1);
            return pageInfo;
        }
    }
    @PostMapping("/save")
    @ApiOperation(value = "记录用户行为")
    public String save(@RequestBody UserLog userLog, HttpServletRequest request){
        String user1=userLog.getUser();
        userLog.setBehavior(LocalTime.now()+"尝试登录");
        userLog.setCreateTime(LocalDateTime.now());
        String userAgent = request.getHeader("sec-ch-ua-platform");
        userLog.setUseragent(userAgent);
        QueryWrapper<Operator> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("oname",user1);
        Operator operator=operatorService.getOne(queryWrapper1);
        if(operator==null){
            userLog.setStatus(0);
        }else {
            userLog.setStatus(1);
        }
        service.save(userLog);
        Long id= Long.valueOf(userLog.getId());
        BaseContext.setCurrentId(id);
        System.err.println(BaseContext.getCurrentId());
        return "成功";
    }
}
