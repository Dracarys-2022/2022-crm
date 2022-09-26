package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.entity.Operator;
import com.mycrm.crm.entity.User;
import com.mycrm.crm.entity.UserLog;
import com.mycrm.crm.service.OperatorService;
import com.mycrm.crm.service.UserLogService;
import com.mycrm.crm.service.UserService;
import com.mycrm.crm.util.JwtUtil;
import com.mycrm.crm.util.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/user")
@Api(value = "活动控制器")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserLogService userLogService;
    @Autowired
    OperatorService operatorService;

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Operator login(@RequestBody Operator operator, HttpServletRequest request){
        QueryWrapper<Operator> queryWrapper=new QueryWrapper<>();
        String password= Md5Utils.code(operator.getPassword());
        System.err.println(password);
        queryWrapper.eq("oname",operator.getOname());
        queryWrapper.eq("password",password);
        Operator operator1=operatorService.getOne(queryWrapper);
        request.getSession().setAttribute("token",operator.getOname());
        if(operator1==null){
//            List<UserLog> userLog=userLogService.selectid();
//            String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"因为账号或者密码错误登录失败");
//            userLog.get(0).setBehavior(behavior);
//            userLogService.updateById(userLog.get(0));


           return operator1;
        }
//        List<UserLog> userLog=userLogService.selectid();
//        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"登录成功");
//        userLog.get(0).setBehavior(behavior);
//        userLogService.updateById(userLog.get(0));
        System.err.println(BaseContext.getCurrentId());
        log.info("id为{}",BaseContext.getCurrentId());
        operator1.setToken(JwtUtil.createToken());
        return operator1;
    }
    @GetMapping("用户更新")
    @ApiOperation(value = "添加活动")
    public  String update( String user,String password,String password1){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("oname",user);
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
