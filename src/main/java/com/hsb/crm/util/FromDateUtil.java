package com.hsb.crm.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FromDateUtil {
    public  static LocalDateTime UtilToSql(){
        //获取当前时间
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        java.util.Date utilDate = new Date();
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        /*java.sql.Time sTime = new java.sql.Time(utilDate.getTime());
        java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());*/
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime stp = instant.atZone(zoneId).toLocalDateTime();
        return stp;
    }
}
