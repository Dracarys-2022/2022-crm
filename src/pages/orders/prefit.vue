<template>
    <h2>
        产品的利润为：{{product}}元<br>
        服务的利润为：{{service}}元
    </h2>
</template>
<script>
import { prefit,prefit1 } from "@/services/orders";
import { message } from "ant-design-vue";
export default {
  data() {
    return {
        product:'',
        service:''
    }}
    ,methods:{

    },created(){
        var aaa=localStorage.getItem('permissions');
    var aa=aaa.replace("\"","").replace("\"","");
    if(localStorage.getItem('access-admin')==""||localStorage.getItem('access-admin')==null){
      this.$message.success("请重新登录！")
      this.$router.push({
          path: "/login"
        });
        return;
    }
   if (!aa.split(",").includes("47")) {
      this.$message.success("您没有权限")
      this.$router.push({
        path: "/403"
        });
        return;
   }
        prefit().then(res=>{
            if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }else{
                message.success(
                '查询成功'
        );
            }
            this.product=res.data.product;
        })
        prefit1().then(res=>{
            if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }else{
                message.success(
                '查询成功'
        );
            }
            this.service=res.data.service;
        })
    }
}
</script>
