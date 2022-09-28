<template>
  <common-layout>
    <div class="top">
      <div class="header">
        <img alt="logo" class="logo" src="./logo1.png" />
        <span class="title">管理系统</span>
      </div>
      
    </div>
    <div class="login">
      <a-form @submit="onSubmit" :form="form">
        <a-tabs size="large" :tabBarStyle="{textAlign: 'center'}" style="padding: 0 2px;">
          <a-tab-pane tab="账户密码登录" key="1">
            <a-alert type="error" :closable="true" v-show="error" :message="error" showIcon style="margin-bottom: 24px;" />
            <a-form-item>
              <a-input
                autocomplete="autocomplete"
                size="large"
                placeholder="用户名"
                v-decorator="['oname', {rules: [{ required: true, message: '请输入账户名', whitespace: true}]}]"
              >
                <a-icon slot="prefix" type="user" />
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-input
                size="large"
                placeholder="密码"
                autocomplete="autocomplete"
                type="password"
                v-decorator="['password', {rules: [{ required: true, message: '请输入密码', whitespace: true}]}]"
              >
                <a-icon slot="prefix" type="lock" />
              </a-input>
            </a-form-item>
          </a-tab-pane>
        </a-tabs>
        <div>
           <el-link type="primary" class="abcde" href="#/update">修改密码</el-link>
        </div>
        <a-form-item>
          <a-button :loading="logging" style="width: 100%;margin-top: 24px" size="large" htmlType="submit" type="primary">登录</a-button>
        </a-form-item>
        <div>
      
        </div>
      </a-form>
    </div>
  </common-layout>
</template>

<script>
import CommonLayout from '@/layouts/CommonLayout'
import {login} from '@/services/user'
import axios from 'axios'
export default {
  name: 'Login',
  components: {CommonLayout},
  data () {
    return {
      logging: false,
      error: '',
      form: this.$form.createForm(this)
    }
  },
  computed: {
    systemName () {
      return this.$store.state.setting.systemName
    }
  },
  methods: {
    onSubmit (e) {
      e.preventDefault()
      this.form.validateFields(async (err) => {
         if (!err) {
          this.logging = true
          const oname = this.form.getFieldValue('oname')
          const password = this.form.getFieldValue('password')
        
          const {data:res}=await axios.post(`user-log/save`,{user:oname,password:password})
          console.log(res);
          if(res!=="成功"){
          return this.$message.error("登录异常")
          }
          else{
           this.$message.success("已将本次登录记录")
          
          login(oname, password).then(this.afterLogin)
          }
        }
      })
    },
    afterLogin(res) {
      this.logging = false
      const loginRes = res.data
      console.log("loginRes:"+loginRes.token);
      alert("loginRes:"+loginRes.token)
      if (!loginRes.token == '') {
        localStorage.setItem('access-admin',JSON.stringify(loginRes.token))
          this.$message.success('登录成功')
          this.$router.push('/dashboard/workplace')
      } else {
        this.$message.error('账号密码错误')
      }
    },
    
  }
}
</script>

<style lang="less" scoped>
  .common-layout{
    .top {
      text-align: center;
      .header {
        height: 44px;
        line-height: 44px;
        a {
          text-decoration: none;
        }
        .logo {
          height: 44px;
          vertical-align: top;
          margin-right: 16px;
        }
        .title {
          font-size: 33px;
          color: @title-color;
          font-family: 'Myriad Pro', 'Helvetica Neue', Arial, Helvetica, sans-serif;
          font-weight: 600;
          position: relative;
          top: 2px;
        }
      }
      .desc {
        font-size: 14px;
        color: @text-color-second;
        margin-top: 12px;
        margin-bottom: 40px;
      }
    }
    .login{
      width: 368px;
      margin: 0 auto;
      @media screen and (max-width: 576px) {
        width: 95%;
      }
      @media screen and (max-width: 320px) {
        .captcha-button{
          font-size: 14px;
        }
      }
      .icon {
        font-size: 24px;
        color: @text-color-second;
        margin-left: 16px;
        vertical-align: middle;
        cursor: pointer;
        transition: color 0.3s;

        &:hover {
          color: @primary-color;
        }
      }
    }
  }
</style>
