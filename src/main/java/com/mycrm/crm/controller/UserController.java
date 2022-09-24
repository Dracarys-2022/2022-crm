package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.entity.User;
import com.mycrm.crm.entity.UserLog;
import com.mycrm.crm.service.UserLogService;
import com.mycrm.crm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@RestController
@RequestMapping("/user")
@Api(value = "活动控制器")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserLogService userLogService;

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public User login(@RequestBody User user, HttpServletRequest request){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user",user.getUser());
        queryWrapper.eq("password",user.getPassword());
        User user1=service.getOne(queryWrapper);
        request.getSession().setAttribute("token",user.getUser());
        if(user1==null){
//            List<UserLog> userLog=userLogService.selectid();
//            String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"因为账号或者密码错误登录失败");
//            userLog.get(0).setBehavior(behavior);
//            userLogService.updateById(userLog.get(0));
            Integer id= BaseContext.getCurrentId().intValue();
            UserLog userLog=userLogService.getById(id);
            String behavior=userLog.getBehavior().concat(" "+ LocalDateTime.now()+"因为账号或者密码错误登录失败");
            userLog.setBehavior(behavior);
            userLogService.updateById(userLog);
            user.setCode(0);
           return user;
        }
//        List<UserLog> userLog=userLogService.selectid();
//        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"登录成功");
//        userLog.get(0).setBehavior(behavior);
//        userLogService.updateById(userLog.get(0));
        Integer id= BaseContext.getCurrentId().intValue();
        UserLog userLog=userLogService.getById(id);
        String behavior=userLog.getBehavior().concat(" "+ LocalDateTime.now()+"登录成功");
        userLog.setBehavior(behavior);
        userLogService.updateById(userLog);
        user1.setCode(1);
        return user1;
    }
    @GetMapping("用户更新")
    @ApiOperation(value = "添加活动")
    public  String update( String user,String password,String password1){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user",user);
        queryWrapper.eq("password",password);
        User user1=service.getOne(queryWrapper);
        if(user1==null){
            return "请检查你的账户名密码";
        }else {
            user1.setPassword(password1);
            service.updateById(user1);
            return "修改密码成功";
        }


    }
}
