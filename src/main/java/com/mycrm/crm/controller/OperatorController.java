package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.common.BaseContext;
import com.mycrm.crm.entity.*;
import com.mycrm.crm.service.*;
import com.mycrm.crm.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@RestController
@Api(tags = "用户模块控制器")
@RequestMapping("/operator")
public class OperatorController {
    @Autowired
    OperatorService operatorService;
    @Autowired
    FunctionsService functionsService;
    @Autowired
    RolesService rolesService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    ContactService contactService;
    @Autowired
    ActiveService activeService;
    @Autowired
    UserLogService userLogService;

    @PostMapping("/add")
    @ApiOperation(value = "添加用户")
    public ResponseData addOperator(@RequestBody Operator operator){
        String password= MD5Util.getMd5(operator.getPassword());
        operator.setPassword(password);
        QueryWrapper<Operator> queryWrapper = new QueryWrapper<>();
        if(operatorService.getOne(queryWrapper.eq("phone",operator.getPhone()))==null)
        {
            boolean save = operatorService.save(operator);
            if(save == true){
                return new ResponseData().ok("添加成功",operator);
            }else {
                return new ResponseData().error("添加失败",operator);
            }
        }else {
            return new ResponseData().error("手机号重复",operator);
        }
    }
    @GetMapping("/queryById")
    @ApiOperation(value = "查询指定用户")
    public Object getById(@RequestParam("oid") Integer oid){
        QueryWrapper<OperatorVo> wrapper = new QueryWrapper<OperatorVo>();
        wrapper.eq("oid",oid);
        Operator operator = operatorService.selectById(wrapper);

        return operator;
    }
    @GetMapping("/queryContact")
    @ApiOperation(value = "查询客户信息")
    public Object listRoles() {
        QueryWrapper<Functions> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("fname","客户管理");
        Functions functions =functionsService.getOne(queryWrapper);
        QueryWrapper<OperatorVo> wrapper= new QueryWrapper<>();
        wrapper.eq("status",1).like("permissions",functions.getFid());
        List<OperatorVo> list =operatorService.queryList(wrapper);
        return list;
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户信息")
    public  ResponseData updateOperator(@RequestBody Operator operator){
        QueryWrapper<Operator> queryWrapper = new QueryWrapper<>();
        if(operatorService.getOne(queryWrapper.eq("phone", operator.getPhone()).ne("oid",operator.getOid()))==null)
        {
            boolean update = operatorService.updateById(operator);
            if(update == true){
                return new ResponseData().ok("修改成功",operator);
            }else {
                return new ResponseData().error("修改失败",operator);
            }
        }else {
            return new ResponseData().error("手机号重复",operator);
        }
    }

    @PostMapping("/page")
    @ApiOperation(value = "查询用户信息")
    public  Object testSelectPage(@RequestBody Paging pagaoperator) {
        QueryWrapper<OperatorVo> wrapper = new QueryWrapper<OperatorVo>();
        Page<OperatorVo> page = new Page<>(pagaoperator.getCurrent(), pagaoperator.getPagesize());
        IPage<OperatorVo> iPage= null;
        QueryWrapper<Functions> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("fname","系统管理员");
        Functions functions =functionsService.getOne(queryWrapper);
        QueryWrapper<Roles> wrapper1= new QueryWrapper<>();
        wrapper1.like("permissions",functions.getFid());
        Roles roles =rolesService.getOne(wrapper1);
        if (pagaoperator.getQuery()==null||pagaoperator.getQuery().length()==0)
        {
            wrapper.ne("r.roid",roles.getRoid()).orderByDesc("status");
            iPage = operatorService.selectPage(page,wrapper);
        }else {
            if (pagaoperator.getQuery().indexOf("常")!=-1||pagaoperator.getQuery().indexOf("正")!=-1)
            {
                wrapper.ne("r.roid",roles.getRoid()).like("o.oid", pagaoperator.getQuery()).or().like("oname" ,pagaoperator.getQuery()).or().like("phone" ,pagaoperator.getQuery()).or().like("r.roname" ,pagaoperator.getQuery()).or().like("status" ,1);
            }else if (pagaoperator.getQuery().indexOf("离")!=-1||pagaoperator.getQuery().indexOf("职")!=-1)
            {
                wrapper.ne("r.roid",roles.getRoid()).like("o.oid", pagaoperator.getQuery()).or().like("oname" ,pagaoperator.getQuery()).or().like("phone" ,pagaoperator.getQuery()).or().like("r.roname" ,pagaoperator.getQuery()).or().like("status" ,0);

            }
            else {
                wrapper.ne("r.roid",roles.getRoid()).like("o.oid", pagaoperator.getQuery()).or().like("oname" ,pagaoperator.getQuery()).or().like("phone" ,pagaoperator.getQuery()).or().like("r.roname" ,pagaoperator.getQuery());
            }
            iPage = operatorService.selectPage(page, wrapper);
        }
        pagaoperator.setRowcount((int) iPage.getTotal());
        pagaoperator.setCurrent((int) iPage.getCurrent());
        List<OperatorVo> operatorVos = iPage.getRecords();
        pagaoperator.setList(operatorVos);
        pagaoperator.setPagesize((int) iPage.getSize());
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" "+ LocalDateTime.now()+"查看了用户管理相关操作");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return pagaoperator;
    }

    @GetMapping("/queryItems")
    @ApiOperation(value = "待办信息")
    public  Object queryItems(@RequestParam("oid") Integer oid) {

        QueryWrapper queryWrapper= new QueryWrapper();
        Map map=new HashMap();
        queryWrapper.eq("oid",oid);
        Operator operator=operatorService.getOne(queryWrapper);

        QueryWrapper Wrapper= new QueryWrapper();
        Wrapper.eq("roid",operator.getRoid());
        Roles roles=rolesService.getOne(Wrapper);

        QueryWrapper WrapperContact = new QueryWrapper();
        QueryWrapper WrapperInventory = new QueryWrapper();
        QueryWrapper WrapperOperator = new QueryWrapper();
        WrapperContact.eq("fname","客户管理");
        WrapperInventory.eq("fname","库存管理");
        WrapperOperator.eq("fname","用户管理");
        Functions functionsContact =functionsService.getOne(WrapperContact);
        Functions functionsInventory =functionsService.getOne(WrapperInventory);
        Functions functionsOperator =functionsService.getOne(WrapperOperator);
        if(functionsContact!=null & roles.getPermissions().contains(String.valueOf(functionsContact.getFid())))
        {
            QueryWrapper<Orders> queryList= new QueryWrapper();
            map.put("state","客户管理");
            queryList.eq("type",0).eq("operid",oid);
            map.put("data1",ordersService.list(queryList));
            QueryWrapper<Orders> queryList1= new QueryWrapper();
            queryList1.eq("type",3).eq("operid",oid);
            map.put("data2",ordersService.list(queryList1));
        }
        else if(functionsInventory!=null & roles.getPermissions().contains(String.valueOf(functionsInventory.getFid())))
        {
            QueryWrapper<Orders> queryList3= new QueryWrapper();
            map.put("state","库存管理");
            queryList3.eq("type",1);
            map.put("data1",ordersService.list(queryList3));
            QueryWrapper<Orders> queryList4= new QueryWrapper();
            queryList4.eq("type",4);
            map.put("data2",ordersService.list(queryList4));
        }
        else if ( functionsOperator!=null & roles.getPermissions().contains(String.valueOf(functionsOperator.getFid()))){
            map.put("state","用户管理");
            QueryWrapper<Operator> queryList= new QueryWrapper();
            List list=new ArrayList();
            queryList.eq("status",0);
            List<Operator> operatorList = operatorService.list(queryList);
            for (Operator operator1:operatorList)
            {
                QueryWrapper<Contact> queryList2= new QueryWrapper();
                queryList2.eq("operid",operator1.getOid()).eq("status",1);
                //System.out.println(contactService.count());
                int count = contactService.count(queryList2);
                System.out.println(count);
                if (count!=0){
                    list.add("员工"+operator1.getOname()+"已离职,请及时处理其管理的客户");
                }
            }
            map.put("data1",list);
        }
       return map;
    }
}
