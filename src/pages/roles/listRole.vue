<template>
<div>
<table class="table" v-show="islist">
        <thead>
            <tr>
      <th>序号</th>
      <th>角色名</th>
      <th>权限</th>
      <th>操作</th>
            </tr>
        </thead>
    <tbody>
     <tr v-for ="(role,index) in roles" :key="index">
      <td>{{index+1}}</td>
      <td>{{role.roname}}</td>
      <td>{{role.permissions}}</td>
      <td><button @click="remove(role.roid)">删除</button><button @click="update(role.roid)">修改</button><button @click="assignment(role.roid)">分配权限</button></td>
     </tr>
    </tbody>
   </table>
    <a-form @submit="onSubmit" :form="form" ref="loginFormRef" v-show="isform">
    <a-form-item label="输入角色名称">
      <a-input v-model="roname" placeholder="输入角色名称"/>
    </a-form-item>
      <a-input v-model="roid" v-show='false'/>
    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">修改</a-button>
    </a-form-item>
  </a-form>
  <div v-show="ischeck">
        <div>请为角色分配权限</div>
        <div v-for ="(f,index) in functionone" :key="index">
            <button @click="sonfun(f.fid)">+</button>{{f.fname}}
        </div> 
        <br>
        <div v-for ="(f,index) in functiontwo" :key="index">
            <input type="checkbox" v-model="permissions" :value=f.fid>{{f.fname}}
        </div>
            <button @click="beSure(rooid,permissions)">确定</button>
		<!-- <div>你已选中：{{permissions}}</div> -->
    </div>
</div>   
</template>
<script>
import {listRoles,deleteRole,updateRole,updateone,RoleOne} from '@/services/roles'
import {beSureFunction,firstFunction,firstFunction1} from '@/services/function'
import { message } from "ant-design-vue";
    export default ({
        name:'listRole',
        data(){
            return{
            isHidden:true,
            islist:true,
            isform:false,
            ischeck:false,
            roname:'',
            rooid:'',
            hobby2:[],
            permissions:[],
            functionone:[],
            functiontwo:[],
            roles: []
            }
        },methods: {
 insert: function () {
  this.users.push(this.user)
 },
 remove: function (roid) {
  deleteRole(roid).then(res=>this.afterDelete(res))
 },listRole(){
                listRoles().then(res=>this.afterList(res));
                // listRoles().then(res=>this.roles=res);
            },
            afterList(res){
                if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }
                this.roles=res.data;
            },afterDelete(res){
                this.roles=res.data;
                if(res.data.msg!=""){
                message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }
            },update(roid){
                updateRole(roid).then(res=>this.lookUpdate(res))
            },lookUpdate(res){
                if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }
                this.isform=true,
                this.islist=false,
                this.roname=res.data.roname,
                this.roid=res.data.roid
            },onSubmit(){
                updateone(this.roid,this.roname).then(res=>{
                    if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }
                })
            },assignment(roid){
                // alert(roid)
                RoleOne(roid).then(res=>this.afterAss(res));
            },afterAss(res){
                // alert(res.data.permissions==null)
                // alert(res.data.permissions)
                if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }
                if(res.data.permissions!=null&&res.data.permissions!=""){
                    // alert(res.data.permissions)
                    // alert(res.data.permissions=="")
                    var sss=res.data.permissions.split(",");
                    this.permissions=sss;
                }
                // alert(res.data.permissions)
                this.rooid=res.data.roid;
                // alert(this.rooid)
                this.afterassi();
            },afterassi(){
                // alert("000")
                firstFunction().then(res=>this.afterassimen(res))
            },afterassimen(res){
                // alert("111")
                if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }
                this.isform=false;
                this.islist=false;
                this.ischeck=true;
                this.functionone=res.data;
            },beSure(rooid,permissions){
                var Onepermission="";
                for(var i = 0; i < permissions.length; i++){
                    // var a=permissions[i+1]+","; 
                    if(i==permissions.length-1){
                        Onepermission+=permissions[i];
                    }else{
                        Onepermission+=permissions[i]+=",";
                    }
                }
                // permissions.forEach((item) => {
                //     var a=permissions[item]+',';
                //     Onepermission+=a;
                // });
                beSureFunction(rooid,Onepermission).then(res=>this.afterBesure(res));
            },afterBesure(res){
                if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }
                this.roles=res.data;
                this.ischeck=false;
                this.islist=true;
            },sonfun(fid){
                firstFunction1(fid).then(res=>this.aftersonfun(res))
            },aftersonfun(res){
                if(res.data.msg=="您没有权限进行此操作!"){
                message.success(
                    "您没有权限进行此操作!"
        )
            }
                this.functiontwo=res.data;
            }
 },created(){
            this.listRole();
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