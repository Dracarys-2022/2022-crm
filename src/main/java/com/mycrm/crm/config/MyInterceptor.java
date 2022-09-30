package com.mycrm.crm.config;
import com.mycrm.crm.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        System.out.println("MyInterceptor1>>>preHandle");
        //把token解析出来
        String token = request.getHeader("Authorization");
        System.out.println("token的值为"+token);
        String aa=token.replace("\"", "");
        Jws<Claims> claimsJws= Jwts.parser().setSigningKey(JwtUtil.signature).parseClaimsJws(aa);
        Claims body=claimsJws.getBody();
        String permissions= (String) body.get("permissions");
        String functions []=permissions.split(",");
        //功能查询的拦截放行
        if ("/functions/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("13".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }if("/roles/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("13".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        System.out.println("我失败了");
        returnJson(response,"{\"code\":400,\"msg\":\"您没有权限进行此操作!\"}");
        return  false;
    }
    /*返回客户端数据*/
    private void returnJson(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptorl>>>postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptorl>>>afterCompletion");
    }
}