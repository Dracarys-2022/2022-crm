package com.ly.crm.entity;

import lombok.Data;

@Data
public class OrdersVo extends Orders{
    private String pname;
    private String opername;
    private String phone;
    private String units;
}
