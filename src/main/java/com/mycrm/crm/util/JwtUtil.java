package com.mycrm.crm.util;

import com.mycrm.crm.entity.Operator;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static  long time=1000*60*60*24;
    public static final String signature="admin";
    public  static  String createToken(Operator operator,String args){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder.setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .claim("username",operator.getOname())
                .claim("permissions",args)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return jwtToken;

    }
}
