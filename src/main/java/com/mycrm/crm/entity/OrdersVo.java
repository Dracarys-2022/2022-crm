package com.mycrm.crm.entity;

<<<<<<< HEAD
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "订单实体")
public class OrdersVo extends Orders{
    @ApiModelProperty(value = "产品名")
    private String pname;
    @ApiModelProperty(value = "客户公司名称")
    private String cname;
    @ApiModelProperty(value = "客户公司联系人名")
    private String coname;
    //类型1，产品名2.服务名
    @ApiModelProperty(value = "类型名")
    private String staname;
    //订单状态名
    @ApiModelProperty(value = "订单状态名")
    private String tyname;
    @ApiModelProperty(value = "录入人名")
    private String oname;
    @ApiModelProperty(value = "录入人电话")
    private String phone;
    @ApiModelProperty(value = "产品规格")
=======
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
>>>>>>> 83528cb18ece3a9aac31cad6df35b8f02b7476df
    private String units;
}
