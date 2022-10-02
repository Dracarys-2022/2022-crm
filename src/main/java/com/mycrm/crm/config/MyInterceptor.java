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
                if ("21".equals(fun)){
                    System.out.println("添加角色");
                    return true;
                }
            }
        }if("/roles/queryList".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("48".equals(fun)){
                    System.out.println("添加用户时查角色");
                    return true;
                }
            }
        }if("/roles/delete".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("22".equals(fun)){
                    System.out.println("删除角色");
                    return true;
                }
            }
        }if("/roles/update".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("23".equals(fun)){
                    System.out.println("查看单个角色详情");
                    return true;
                }
            }
        }if("/roles/updateone".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("24".equals(fun)){
                    System.out.println("修改单个角色详情");
                    return true;
                }
            }
        }if("/roles/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("25".equals(fun)){
                    System.out.println("查看所有角色");
                    return true;
                }
            }
        }if("/visit/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("26".equals(fun)){
                    System.out.println("查看拜访记录");
                    return true;
                }
            }
        }if("/visit/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("27".equals(fun)){
                    System.out.println("添加拜访记录");
                    return true;
                }
            }
        }if("/visit/delete".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("28".equals(fun)){
                    System.out.println("删除拜访记录");
                    return true;
                }
            }
        }if("/product/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("29".equals(fun)){
                    System.out.println("添加产品");
                    return true;
                }
            }
        }if("/orders/add".equals(request.getRequestURI())||"/orders/getlist".equals(request.getRequestURI())||"/orders/selectcid".equals(request.getRequestURI())||"/orders/allpro".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("42".equals(fun)){
                    System.out.println("添加订单");
                    return true;
                }
            }
        }if("/orders/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("43".equals(fun)){
                    System.out.println("查看订单");
                    return true;
                }
            }
        }if("/orders/request".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("44".equals(fun)){
                    System.out.println("订单请求出库");
                    return true;
                }
            }
        }if("/orders/return".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("45".equals(fun)){
                    System.out.println("订单退换");
                    return true;
                }
            }
        }if("/orders/complate".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("46".equals(fun)){
                    System.out.println("完成订单");
                    return true;
                }
            }
        }if("/orders/prefit1".equals(request.getRequestURI())||"/orders/prefit".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("47".equals(fun)){
                    System.out.println("查看利润");
                    return true;
                }
            }
        }if("/functions/listFunction".equals(request.getRequestURI())||"/functions/listFunction1".equals(request.getRequestURI())||"/functions/beSureFunctions".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("89".equals(fun)){
                    System.out.println("修改权限");
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