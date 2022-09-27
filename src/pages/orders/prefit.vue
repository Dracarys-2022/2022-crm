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
            this.product=res.data.product;
        })
        prefit1().then(res=>{
            this.service=res.data.service;
        })
    }
}
</script>
