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
@RequestMapping("/client")
@Api(tags = "客户公司模块控制器")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ContactService contactService;
    @PostMapping("/add")
    @ApiOperation(value = "添加客户公司")
    public ResponseData addClient(@RequestBody Client client){
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
       if(clientService.getOne(queryWrapper.eq("cname", client.getCname()))==null)
       {
           client.setCreatetime(FromDateUtil.UtilToSql());
           client.setUpdatetime(FromDateUtil.UtilToSql());
           client.setOperid(1);
           boolean save = clientService.save(client);
           if(save == true){
               return new ResponseData().ok("添加成功",client);
           }else {
               return new ResponseData().error("添加失败",client);
           }
       }else {
           return new ResponseData().error("该公司已添加",client);
       }


    }
    @GetMapping("/queryList")
    @ApiOperation(value = "查询未流失客户公司")
    public Object listClient()
    {
        QueryWrapper<Client> wrapper = new QueryWrapper<Client>();
        wrapper.eq("status",1);
        List<Client> list =clientService.list(wrapper);
        return list;
    }

    @GetMapping("/queryById")
    @ApiOperation(value = "查询指定客户公司")
    public Object getById(@RequestParam ("cid") Integer cid){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("cid",cid);
        Client client = clientService.selectById(queryWrapper);
        return client;
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新客户公司信息")
    public  ResponseData updateClient(@RequestBody Client client){
        client.setUpdatetime(FromDateUtil.UtilToSql());
        boolean update = clientService.updateById(client);
        if(update == true){
            if (client.getStatus()==0)
            {
                Contact contact=new Contact();
                client.setUpdatetime(FromDateUtil.UtilToSql());
                QueryWrapper<Contact> wrapper = new QueryWrapper<Contact>();
                wrapper.eq("cid",client.getCid());
                contact.setCid(client.getCid());
                contact.setStatus(0);
                contactService.update(contact,wrapper);
            }
            return new ResponseData().ok("修改成功",client);
        }else {
            return new ResponseData().error("修改失败",client);
        }
    }
    @PostMapping("/page")
    @ApiOperation(value = "查询客户公司")
    public  Object testSelectPage(@RequestBody Paging pageclient) {
        System.out.println(pageclient);
        QueryWrapper<ClientVo> wrapper = new QueryWrapper<ClientVo>();
        Page<ClientVo> page = new Page<>(pageclient.getCurrent(), pageclient.getPagesize());
        IPage<ClientVo> iPage= null;
        if (pageclient.getQuery()==null||pageclient.getQuery().length()==0)
        {
            wrapper.isNotNull("cname");
            iPage = clientService.selectPage(page, wrapper);
        }else {
            if (pageclient.getQuery().indexOf("常")!=-1||pageclient.getQuery().indexOf("正")!=-1)
            {
                wrapper.like("cid", pageclient.getQuery()).or().like("cname" ,pageclient.getQuery()).or().like("oname" ,pageclient.getQuery()).or().like("legalperson",pageclient.getQuery()).or().like("registered",pageclient.getQuery()).or().like("address",pageclient.getQuery()).or().like("phone",pageclient.getQuery()).or().like("account",pageclient.getQuery()).or().like("quality",pageclient.getQuery()).or().like("remark",pageclient.getQuery()).or().like("industry",pageclient.getQuery()).or().like("category",pageclient.getQuery()).or().like("status",1);
            }else if (pageclient.getQuery().indexOf("流")!=-1||pageclient.getQuery().indexOf("失")!=-1)
            {
                wrapper.like("cid", pageclient.getQuery()).or().like("cname" ,pageclient.getQuery()).or().like("oname" ,pageclient.getQuery()).or().like("legalperson",pageclient.getQuery()).or().like("registered",pageclient.getQuery()).or().like("address",pageclient.getQuery()).or().like("phone",pageclient.getQuery()).or().like("account",pageclient.getQuery()).or().like("quality",pageclient.getQuery()).or().like("remark",pageclient.getQuery()).or().like("industry",pageclient.getQuery()).or().like("category",pageclient.getQuery()).or().like("status",0);
            }
            else {
                wrapper.like("cid", pageclient.getQuery()).or().like("cname" ,pageclient.getQuery()).or().like("oname" ,pageclient.getQuery()).or().like("legalperson",pageclient.getQuery()).or().like("registered",pageclient.getQuery()).or().like("address",pageclient.getQuery()).or().like("phone",pageclient.getQuery()).or().like("account",pageclient.getQuery()).or().like("quality",pageclient.getQuery()).or().like("remark",pageclient.getQuery()).or().like("industry",pageclient.getQuery()).or().like("category",pageclient.getQuery());
            }
            iPage = clientService.selectPage(page, wrapper);
        }
        pageclient.setRowcount((int) iPage.getTotal());
        pageclient.setCurrent((int) iPage.getCurrent());
        List<ClientVo> clients = iPage.getRecords();
        pageclient.setList(clients);
        pageclient.setPagesize((int) iPage.getSize());
        return pageclient;
    }
}
