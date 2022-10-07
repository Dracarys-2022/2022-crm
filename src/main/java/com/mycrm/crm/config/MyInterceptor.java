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
        System.out.println("权限："+permissions);
        String functions []=permissions.split(",");
        //功能查询的拦截放行
        if ("/functions/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("90".equals(fun)){
                    System.out.println("查看功能");
                    return true;
                }
            }
        }
        if("/roles/add".equals(request.getRequestURI())){
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
        }if("/functions/listFunction".equals(request.getRequestURI())||"/functions/listFunction1".equals(request.getRequestURI())||"/functions/beSureFunctions".equals(request.getRequestURI())||"/roles/roleOne".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("89".equals(fun)){
                    System.out.println("修改权限");
                    return true;
                }
            }
        }
//        if("/roles/roleOne".equals(request.getRequestURI())){
//            for(String fun:functions){
//                if ("89".equals(fun)){
//                    System.out.println("修改权限");
//                    return true;
//                }
//            }
//        }
        //投诉模块
        if("/petition/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("36".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/petition/getOper".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("91".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/petition/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("39".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/petition/listNull".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("40".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/petition/addoutcome".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("37".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/petition/delete".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("41".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //库存模块
        if("/inventory/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("82".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/query".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("56".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/sub".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("62".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("64".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/listNull".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("65".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/queryById".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("58".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/update".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("60".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/getproduct".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("59".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/inventory/lack".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("63".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        //产品模块
        if("/product/list".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("31".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/product/listOut".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("32".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/product/changeStatus".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("88".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/product/queryByPid".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("30".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/product/update".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("33".equals(fun)){
                    System.out.println("我通过了");
                    return true;
                }
            }
        }
        if("/product/add".equals(request.getRequestURI())){
            for(String fun:functions){
                if ("29".equals(fun)){
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