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
        }
        if("/roles/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("13".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //添加客户公司
        if("/client/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("72".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //获取所有客户公司信息
        if("/client/queryList".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("85".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //查询指定客户公司信息
        if("/client/queryById".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("73".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //修改客户公司信息
        if("/client/update".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("74".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //查看客户公司所有信息
        if("/client/page".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("75".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //客户添加
        if("/contact/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("66".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //查看客户信息
        if("/contact/page".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("71".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //查询指定客户信息
        if("/contact/queryById".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("69".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //修改客户信息
        if("/contact/update".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("70".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //用户添加
        if("/operator/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("49".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //查看所有用户信息
        if("/operator/page".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("86".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //查询指定用户信息
        if("/operator/queryById".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("50".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //修改用户信息
        if("/operator/update".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("54".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //查询所有销售
        if("/operator/queryContact".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("83".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //待办
        if("/operator/queryItems".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("87".equals(fun)){
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