<template>
<div>
<table class="table" v-show="islist">
        <thead>
            <tr>
      <th>序号</th>
      <th>功能</th>
      <td>级别</td>
      <!-- <th>操作</th> -->
            </tr>
        </thead>
    <tbody>
     <tr v-for ="(func,index) in functions" :key="index">
      <td>{{index+1}}</td>
      <td>{{func.fname}}</td>
      <td v-if="func.fatherid==0">父权限</td>
      <td v-if="func.fatherid!=0">子权限</td>
      <!-- <td><button @click="remove(func.fid)">删除</button><button @click="update(func.fid)">修改</button></td> -->
     </tr>
    </tbody>
   </table>
    <a-form @submit="onSubmit" :form="form" ref="loginFormRef" v-show="isform">
    <a-form-item label="输入功能名称">
      <a-input v-model="fname" placeholder="输入功能名称"/>
    </a-form-item>
      <a-input v-model="fid" v-show='false'/>
    <!-- <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">修改</a-button>
    </a-form-item> -->
  </a-form>
</div>   
</template>
<script>
import {listFunctions,deleteFunction,updateFunction,updateoneFunction} from '@/services/function'
    export default ({
        name:'listRole',
        data(){
            return{
            ischeck:false,
            isHidden:true,
            islist:true,
            isform:false,
            fname:'',
  functions: [
  {'fid': '1', 'fname': 'a'}
  ]
            }
        },methods: {
 insert: function () {
  this.users.push(this.user)
 },
 remove: function (fid) {
  deleteFunction(fid).then(res=>this.afterDelete(res))
 },listFunction(){
                listFunctions().then(res=>this.afterList(res));
                // listRoles().then(res=>this.roles=res);
            },
            afterList(res){
                this.functions=res.data;
            },afterDelete(res){
                this.functions=res.data;
                alert('删除成功！');
            },update(fid){
                updateFunction(fid).then(res=>this.lookUpdate(res))
            },lookUpdate(res){
                this.isform=true,
                this.islist=false,
                this.fname=res.data.fname,
                this.fid=res.data.fid
            },onSubmit(){
                updateoneFunction(this.fid,this.fname).then(alert("修改成功！"))
            }

 },created(){
            this.listFunction();
        }
    })
</script>
<style scoped>
h1, h2 {
 font-weight: normal;
}
ul {
 list-style-type: none;
 padding: 0;
}
li {
 display: inline-block;
 margin: 0 10px;
}
a {
 color: #42b983;
}
tr,th{
 text-align:center;
}
</style>