package com.mycrm.crm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Paging {
    //总条数
    private int rowcount;
    //当前页
    private int current;
    //数据
    private List list;
    //条件
    private String query;
    //每页数据
    private int pagesize;
    //用户id
    private  int oid;
}
