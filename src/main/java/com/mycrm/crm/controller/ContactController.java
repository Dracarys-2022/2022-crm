package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.*;
import com.mycrm.crm.service.ClientService;
import com.mycrm.crm.service.ContactService;
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
    ContactService contactService;
    @Autowired
    ClientService clientService;
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
    public  Object testSelectPage(@RequestBody Paging pagacontact) {
        QueryWrapper<ContactVo> wrapper = new QueryWrapper<ContactVo>();
        Page<ContactVo> page = new Page<>(pagacontact.getCurrent(), pagacontact.getPagesize());
        IPage<ContactVo> iPage= null;
        if (pagacontact.getQuery()==null||pagacontact.getQuery().length()==0)
        {
            wrapper.isNotNull("coname");
            iPage = contactService.selectPage(page,wrapper);
        }else {
            if (pagacontact.getQuery().indexOf("常")!=-1||pagacontact.getQuery().indexOf("正")!=-1)
            {
                wrapper.like("co.coid", pagacontact.getQuery()).or().like("coname" ,pagacontact.getQuery()).or().like("oname" ,pagacontact.getQuery()).or().like("co.address",pagacontact.getQuery()).or().like("co.phone",pagacontact.getQuery()).or().like("cname",pagacontact.getQuery()).or().like("co.remark",pagacontact.getQuery()).or().like("co.status",1);
            }else if (pagacontact.getQuery().indexOf("流")!=-1||pagacontact.getQuery().indexOf("失")!=-1)
            {
                wrapper.like("co.coid", pagacontact.getQuery()).or().like("coname" ,pagacontact.getQuery()).or().like("oname" ,pagacontact.getQuery()).or().like("co.address",pagacontact.getQuery()).or().like("co.phone",pagacontact.getQuery()).or().like("cname",pagacontact.getQuery()).or().like("co.remark",pagacontact.getQuery()).or().like("co.status",0);

            }
            else {
                wrapper.like("co.coid", pagacontact.getQuery()).or().like("coname" ,pagacontact.getQuery()).or().like("oname" ,pagacontact.getQuery()).or().like("co.address",pagacontact.getQuery()).or().like("co.phone",pagacontact.getQuery()).or().like("cname",pagacontact.getQuery()).or().like("co.remark",pagacontact.getQuery());
            }
            iPage = contactService.selectPage(page, wrapper);
        }
        pagacontact.setRowcount((int) iPage.getTotal());
        pagacontact.setCurrent((int) iPage.getCurrent());
        List<ContactVo> contactVos = iPage.getRecords();
        pagacontact.setList(contactVos);
        pagacontact.setPagesize((int) iPage.getSize());
        return pagacontact;
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
