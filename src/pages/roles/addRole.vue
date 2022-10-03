<template>
  <a-form @submit="onSubmit" :form="form" ref="loginFormRef">
    <a-form-item label="输入角色名称">
      <a-input v-model="roname" placeholder="输入角色名称"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">创建</a-button>
      <a-button @click="resetLoginForm" style="margin-left: 10px">取消</a-button>
    </a-form-item>
  </a-form>
</template>
<script>
import {addRole} from '@/services/roles'
export default ({
  name:'roles',
   data () {
    return {
      roname:"",
      form: this.$form.createForm(this)
    }
  }, methods: {
    onSubmit () {
      addRole(this.roname).then(res=>{
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
        this.$ref.loginFormRef.resetFields()
      })
    },resetLoginForm() {
      // console.log(this);
      this.$refs.loginFormRef.resetFields()
    }
  },
});
</script>

