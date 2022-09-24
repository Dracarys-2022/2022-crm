package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.common.R;
import com.mycrm.crm.entity.Active;
import com.mycrm.crm.entity.UserLog;
import com.mycrm.crm.service.ActiveService;
import com.mycrm.crm.service.UserLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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
@RequestMapping("/active")
@Api(value = "活动控制器")
public class ActiveController {
    @Autowired
    ActiveService activeService;
    @Autowired
    UserLogService userLogService;

    @PostMapping("/activesave")
    @ApiOperation(value = "添加活动")
    public String activesave(@RequestBody Active active, HttpServletRequest request) {

        activeService.save(active);
        Integer id= BaseContext.getCurrentId().intValue();
        UserLog userLog=userLogService.getById(id);
        String behavior=userLog.getBehavior().concat(" 添加了"+active.getTime()+"日程表");
        userLog.setBehavior(behavior);
        userLogService.updateById(userLog);

        return "添加日程成功";
    }
    @ApiOperation(value = "查看日志")
    @PutMapping("/select/{day}")
    public R<List<Active>> activeselect(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day, HttpServletRequest request) {

        QueryWrapper<Active> queryWrapper = new QueryWrapper();
        queryWrapper.eq("time", day);
        queryWrapper.orderByAsc("starttime");
        List<Active> active1 = activeService.list(queryWrapper);
//        List<UserLog> userLog=userLogService.selectid();
//        String behavior=userLog.get(0).getBehavior().concat(" 查看了"+day+"日程表");
//        userLog.get(0).setBehavior(behavior);
//        userLogService.updateById(userLog.get(0));
        Integer id= BaseContext.getCurrentId().intValue();
        UserLog userLog=userLogService.getById(id);
        String behavior=userLog.getBehavior().concat(" 查看了"+day+"日程表");
        userLog.setBehavior(behavior);
        userLogService.updateById(userLog);

        return R.success(active1);

    }
    @ApiOperation(value = "查看详细日程表")
    @PutMapping("/supdate/{id}")
    public R<Active> update(@PathVariable Integer id, HttpServletRequest request){
        Active active=activeService.getById(id);
//        List<UserLog> userLog=userLogService.selectid();
//        String behavior=userLog.get(0).getBehavior().concat(" 查看了详细日程表");
//        userLog.get(0).setBehavior(behavior);
//        userLogService.updateById(userLog.get(0));
        Integer id1= BaseContext.getCurrentId().intValue();
        UserLog userLog=userLogService.getById(id1);
        String behavior=userLog.getBehavior().concat(" 查看了详细日程表");
        userLog.setBehavior(behavior);
        userLogService.updateById(userLog);
        return R.success(active);
    }
    @PostMapping("update")
    @ApiOperation(value = "更新活动")
    public R<String> update1(@RequestBody Active active, HttpServletRequest request){
        activeService.updateById(active);
//        List<UserLog> userLog=userLogService.selectid();
//        String behavior=userLog.get(0).getBehavior().concat(" 修改了"+active.getTime()+"日程表");
//        userLog.get(0).setBehavior(behavior);
//        userLogService.updateById(userLog.get(0));
        Integer id1= BaseContext.getCurrentId().intValue();
        UserLog userLog=userLogService.getById(id1);
        String behavior=userLog.getBehavior().concat(" 修改了"+active.getTime()+"日程表");
        userLog.setBehavior(behavior);
        userLogService.updateById(userLog);
        return R.success("修改日程成功");
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除活动")
    public R<String> delete(@PathVariable Integer id, HttpServletRequest request){
        activeService.removeById(id);
//        List<UserLog> userLog=userLogService.selectid();
//        String behavior=userLog.get(0).getBehavior().concat(" 删除了编号为:"+id+"日程表");
//        userLog.get(0).setBehavior(behavior);
//        userLogService.updateById(userLog.get(0));
        Integer id1= BaseContext.getCurrentId().intValue();
        UserLog userLog=userLogService.getById(id1);
        String behavior=userLog.getBehavior().concat(" 删除了编号为:"+id+"日程表");
        userLog.setBehavior(behavior);
        userLogService.updateById(userLog);
        return R.success("成功");
    }
    @PostMapping()
    @ApiOperation(value = "活动列表")
    public R<List<Active>> list(HttpServletRequest request){
        List<Active> activeList=activeService.list();
//        List<UserLog> userLog=userLogService.selectid();
//        String behavior=userLog.get(0).getBehavior().concat(" "+LocalDateTime.now()+"查看了用户日程表");
//        userLog.get(0).setBehavior(behavior);
//        userLogService.updateById(userLog.get(0));
        Integer id1= BaseContext.getCurrentId().intValue();
        UserLog userLog=userLogService.getById(id1);
        String behavior=userLog.getBehavior().concat(" "+LocalDateTime.now()+"查看了用户日程表");
        userLog.setBehavior(behavior);
        userLogService.updateById(userLog);

        return R.success(activeList);

    }
}