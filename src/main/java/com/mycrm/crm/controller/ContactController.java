package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.*;
import com.mycrm.crm.service.ClientService;
import com.mycrm.crm.service.ContactService;
import com.mycrm.crm.service.OperatorService;
import com.mycrm.crm.service.RolesService;
import com.mycrm.crm.util.FromDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hsb
 * @since 2022-07-18
 */
@RestController
@RequestMapping("/contact")
@Api(tags = "客户模块控制器")
public class ContactController {
    @Autowired
    OperatorService operatorService;
    @Autowired
    ContactService contactService;
    @Autowired
    ClientService clientService;
    @Autowired
    RolesService rolesService;
    @PostMapping("/add")
    @ApiOperation(value = "添加客户联系人")
    public ResponseData addContact(@RequestBody Contact contact) {
        QueryWrapper<Contact> queryWrapper = new QueryWrapper<>();
        if (contactService.getOne(queryWrapper.eq("phone", contact.getPhone())) == null) {
            contact.setCreatetime(FromDateUtil.UtilToSql());
            contact.setUpdatetime(FromDateUtil.UtilToSql());
            //contact.setOperid(1);
            boolean save = contactService.save(contact);
            if (save == true) {
                return new ResponseData().ok("添加成功", contact);
            } else {
                return new ResponseData().error("添加失败", contact);
            }
        } else {
            return new ResponseData().error("手机号重复", contact);
        }
    }
    @PostMapping("/page")
    @ApiOperation(value = "查询所有客户信息")
    public  Object testSelectPage(@RequestBody Paging pagecontact) {
        System.out.println("--------------------------");
        System.out.println(pagecontact);
        QueryWrapper<ContactVo> wrapper = new QueryWrapper<ContactVo>();
        Page<ContactVo> page = new Page<>(pagecontact.getCurrent(), pagecontact.getPagesize());
        QueryWrapper<Roles> rolesQueryWrapper=new QueryWrapper<>();
        rolesQueryWrapper.eq("roname","销售员");
        Roles roles=rolesService.getOne(rolesQueryWrapper);
        QueryWrapper<Operator> operatorQueryWrapper=new QueryWrapper<>();
        operatorQueryWrapper.eq("oid",pagecontact.getOid());
        Operator operator=operatorService.getOne(operatorQueryWrapper);
        IPage<ContactVo> iPage= null;
        if (roles.getRoid()==operator.getRoid())
        {
            if (pagecontact.getQuery()==null||pagecontact.getQuery().length()==0)
            {
                wrapper.eq("co.operid",pagecontact.getOid());
                iPage = contactService.selectPage(page,wrapper);
            }else {
                if (pagecontact.getQuery().indexOf("常")!=-1||pagecontact.getQuery().indexOf("正")!=-1)
                {
                    wrapper.eq("co.operid",pagecontact.getOid()).and(w1->w1.like("co.coid", pagecontact.getQuery())
                            .or().like("coname" ,pagecontact.getQuery())
                            .or().like("oname" ,pagecontact.getQuery())
                            .or().like("co.address",pagecontact.getQuery())
                            .or().like("co.phone",pagecontact.getQuery())
                            .or().like("cname",pagecontact.getQuery())
                            .or().like("co.remark",pagecontact.getQuery())
                            .or().like("co.status",1));
                }else if (pagecontact.getQuery().indexOf("流")!=-1||pagecontact.getQuery().indexOf("失")!=-1)
                {
                    wrapper.eq("co.operid",pagecontact.getOid()).and(w1->w1.like("co.coid", pagecontact.getQuery())
                            .or().like("coname" ,pagecontact.getQuery())
                            .or().like("oname" ,pagecontact.getQuery())
                            .or().like("co.address",pagecontact.getQuery())
                            .or().like("co.phone",pagecontact.getQuery())
                            .or().like("cname",pagecontact.getQuery())
                            .or().like("co.remark",pagecontact.getQuery())
                            .or().like("co.status",0));
                }
                else {

                    wrapper.eq("co.operid",pagecontact.getOid()).and(w1->w1.like("co.coid", pagecontact.getQuery())
                            .or().like("coname" ,pagecontact.getQuery())
                            .or().like("oname" ,pagecontact.getQuery())
                            .or().like("co.address",pagecontact.getQuery())
                            .or().like("co.phone",pagecontact.getQuery())
                            .or().like("cname",pagecontact.getQuery())
                            .or().like("co.remark",pagecontact.getQuery()));
                }
                iPage = contactService.selectPage(page, wrapper);
            }
        }
        else {
            if (pagecontact.getQuery()==null||pagecontact.getQuery().length()==0)
            {
                wrapper.isNotNull("coname");
                iPage = contactService.selectPage(page,wrapper);
            }else {
                if (pagecontact.getQuery().indexOf("常")!=-1||pagecontact.getQuery().indexOf("正")!=-1)
                {
                    wrapper.like("co.coid", pagecontact.getQuery()).or().like("coname" ,pagecontact.getQuery()).or().like("oname" ,pagecontact.getQuery()).or().like("co.address",pagecontact.getQuery()).or().like("co.phone",pagecontact.getQuery()).or().like("cname",pagecontact.getQuery()).or().like("co.remark",pagecontact.getQuery()).or().like("co.status",1);
                }else if (pagecontact.getQuery().indexOf("流")!=-1||pagecontact.getQuery().indexOf("失")!=-1)
                {
                    wrapper.like("co.coid", pagecontact.getQuery()).or().like("coname" ,pagecontact.getQuery()).or().like("oname" ,pagecontact.getQuery()).or().like("co.address",pagecontact.getQuery()).or().like("co.phone",pagecontact.getQuery()).or().like("cname",pagecontact.getQuery()).or().like("co.remark",pagecontact.getQuery()).or().like("co.status",0);

                }
                else {
                    wrapper.like("co.coid", pagecontact.getQuery()).or().like("coname" ,pagecontact.getQuery()).or().like("oname" ,pagecontact.getQuery()).or().like("co.address",pagecontact.getQuery()).or().like("co.phone",pagecontact.getQuery()).or().like("cname",pagecontact.getQuery()).or().like("co.remark",pagecontact.getQuery());
                }
                iPage = contactService.selectPage(page, wrapper);
            }
        }

        pagecontact.setRowcount((int) iPage.getTotal());
        pagecontact.setCurrent((int) iPage.getCurrent());
        List<ContactVo> contactVos = iPage.getRecords();
        pagecontact.setList(contactVos);
        pagecontact.setPagesize((int) iPage.getSize());
        return pagecontact;
    }
    @GetMapping("/queryById")
    @ApiOperation(value = "查询指定客户")
    public Object getById(@RequestParam("coid") Integer coid){
        QueryWrapper<ContactVo> wrapper = new QueryWrapper<ContactVo>();
        wrapper.eq("coid",coid);
        ContactVo contactVo = contactService.selectById(wrapper);
        return contactVo;
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新客户信息")
    public  ResponseData updateContact(@RequestBody Contact contact){
        QueryWrapper<Contact> queryWrapper = new QueryWrapper<>();
        contact.setUpdatetime(FromDateUtil.UtilToSql());
        QueryWrapper<Client> wrapper=new QueryWrapper<>();
        wrapper.eq("cid",contact.getCid());
        Client client= clientService.getOne(wrapper);
        if ( client.getStatus()==1 || contact.getStatus()==null )
        {
            if (contactService.getOne(queryWrapper.eq("phone",contact.getPhone()).ne("coid",contact.getCoid()))== null)
            {
                boolean update = contactService.updateById(contact);
                if(update == true){
                    return new ResponseData().ok("修改成功",contact);
                }else {
                    return new ResponseData().error("修改失败",contact);
                }
            }
            else {
                return new ResponseData().error("手机号重复",contact);
            }
        }
        else {
            return new ResponseData().error("该联系人所在公司流失,不能修改状态",contact);
        }

    }
}
