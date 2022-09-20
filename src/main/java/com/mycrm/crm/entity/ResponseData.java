package com.mycrm.crm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResponseData<T> implements Serializable {
    private String code;
    private String msg;
    private T data;
    public ResponseData()
    {}
    public ResponseData(String code,String msg,T data)
    {
        this.code=code;
        this.msg =msg;
        this.data=data;
    }
    public ResponseData ok(String msg,T data){
        ResponseData responseData= new ResponseData("0000",msg,data);
        return responseData;
    }
    public ResponseData error(String msg,T data){
        ResponseData responseData= new ResponseData("1111",msg,data);
        return responseData;
    }
}
