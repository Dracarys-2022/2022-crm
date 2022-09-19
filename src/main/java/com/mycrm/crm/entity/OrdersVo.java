package com.mycrm.crm.entity;

import lombok.Data;

@Data
public class OrdersVo extends Orders{
    private String pname;
    private String cname;
    private String coname;
    //类型1，产品名2.服务名
    private String staname;
    //订单状态名
    private String tyname;
    private String oname;
    private String phone;
    private String units;
}
