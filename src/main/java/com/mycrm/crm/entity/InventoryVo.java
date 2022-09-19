package com.mycrm.crm.entity;

import lombok.Data;

@Data
public class InventoryVo extends Inventory{
    private String pname;
    private String units;
}
