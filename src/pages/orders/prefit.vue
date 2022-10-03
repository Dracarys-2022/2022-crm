<template>
    <h2>
        产品的利润为：{{product}}元<br>
        服务的利润为：{{service}}元
    </h2>
</template>
<script>
import { prefit,prefit1 } from "@/services/orders";
export default {
  data() {
    return {
        product:'',
        service:''
    }}
    ,methods:{

    },created(){
        prefit().then(res=>{
            if(res.data.msg!=""){
                this.$message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }else{
                this.$message.success(
                '查询成功'
        );
            }
            this.product=res.data.product;
        })
        prefit1().then(res=>{
            if(res.data.msg!=""){
                this.$message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }else{
                this.$message.success(
                '查询成功'
        );
            }
            this.service=res.data.service;
        })
    }
}
</script>
