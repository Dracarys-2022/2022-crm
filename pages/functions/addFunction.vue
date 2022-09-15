<template>
<div>
  <a-form @submit="onSubmit" :form="form" ref="loginFormRef" v-show="isone">
    <a-form-item label="输入父功能名称">
      <a-input v-model="fname" placeholder="输入父功能名称"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">创建</a-button>
      <a-button type="primary" @click="onSon">添加子功能</a-button>
      <a-button @click="resetLoginForm" style="margin-left: 10px">取消</a-button>
    </a-form-item>
  </a-form>
  <div v-show="istwo">
    <a-form @submit="onSubmit1" :form="form" ref="loginFormRef" >
  <select v-model="selected">
       <option v-for ="(func,index) in functions" :key="index" :value="func.fid">{{func.fname}}</option>
  </select>
    <a-form-item label="输入子功能名称">
      <a-input v-model="fname1" placeholder="输入子功能名称"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit1">创建</a-button>
      <a-button @click="resetLoginForm" style="margin-left: 10px">取消</a-button>
    </a-form-item>
  </a-form>
  </div>
</div>
</template>
<script>
import {addFunction,addFunction1,firstFunction} from '@/services/function'
export default ({
  name:'roles',
   data () {
    return {
      isone:true,
      istwo:false,
      selected:'',
      fname:"",
      fname1:"",
      functions:[],
      form: this.$form.createForm(this)
    }
  }, methods: {
    onSubmit () {
      addFunction(this.fname).then(alert("添加成功！"))
    },onSubmit1 () {
      alert(this.selected)
      addFunction1(this.fname1,this.selected).then(alert("添加成功！"))
    }
    ,resetLoginForm() {
      // console.log(this);
      this.$refs.loginFormRef.resetFields()
    },onSon(){
      this.isone=false,
      this.istwo=true,
      firstFunction().then(res=>this.afterFirst(res))
    },afterFirst(res){
        this.functions=res.data
        // alert(functions)
    }
  },
});
</script>