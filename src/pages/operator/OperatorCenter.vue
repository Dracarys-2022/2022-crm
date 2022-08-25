<template>
  <div>
    <a-card style="margin-top: 24px" :bordered="false" title="修改手机号">
      <a-form-model ref="operator" :model="operator" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal"
        :rules="rules">
        <div class="content" style="background-color: white;">
        <a-form-model-item class="a" label="手机号:" prop="phone">
          <a-input name="phone" v-model="operator.phone" placeholder="请输入联系人电话">
          </a-input>
        </a-form-model-item>
        </div>
        <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
          <a-button type="primary" html-type="submit" @click="updatePhone('operator')">修改</a-button>
        </a-form-model-item>
      </a-form-model>
    </a-card>

    <a-card style="margin-top: 24px" :bordered="false" title="修改密码">
      <a-form-model ref="operator1" :model="operator1" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal"
        :rules="rules">
        <div class="content" style="background-color: white">
        <a-form-model-item class="a" label="旧密码" prop="oldPassword">
          <a-input-password name="againPassword" v-model="operator1.oldPassword" placeholder="请再次输入密码" />
          <a-icon type="ios-checkmark-circle" v-if="changeAgainFlag == 1" color="#35B449" />
          <a-icon type="md-close-circle" v-else-if="changeAgainFlag == 2" color="#f00" />
        </a-form-model-item>
        <a-form-model-item class="a" label="新密码" prop="password">
          <a-input-password name="password" v-model="operator1.password" placeholder="请新密码输入密码" />
          <span>6-16位字符，同时包括数字、大小写字母和特殊字符四种组合； 特殊字符需在“~ @ # $ % * _ - + = : , . ?”。</span>
          <a-icon type="ios-checkmark-circle" v-if="changeFlag == 1" color="#35B449" />
          <a-icon type="md-close-circle" v-else-if="changeFlag == 2" color="#f00" />
        </a-form-model-item>
        <a-form-model-item class="a" label="重复密码" prop="againPassword">
          <a-input-password name="againPassword" v-model="operator1.againPassword" placeholder="请再次输入密码" />
          <a-icon type="ios-checkmark-circle" v-if="changeAgainFlag == 1" color="#35B449" />
          <a-icon type="md-close-circle" v-else-if="changeAgainFlag == 2" color="#f00" />
        </a-form-model-item>
        </div>
        <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
          <a-button type="primary" html-type="submit" @click="updatePwd('operator1')">修改</a-button>
        </a-form-model-item>
      </a-form-model>
      
    </a-card>
  </div>

</template>
<script>
import { update } from "@/services/operatorUpdate";
import { queryById } from "@/services/operatorById";
export default {
  data() {
    const oldpwdCheck = async (rule, value, callback) => {
      if (value.length == 0) {
        this.changeFlag = 2;
        return callback(new Error('旧密码不能为空'));
      } else if (this.operator.old != value) {
        this.changeFlag = 2;
        return callback(new Error('旧密码不正确'));
      }
      else {
        this.changeFlag = 1;
        callback()
      }
    };
    const pwdCheck = async (rule, value, callback) => {
      let reg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*--+/\\.])[\da-zA-Z~!@#$%^&*--+/\\.]{6,16}$/
      if (value.length < 6) {
        this.changeFlag = 2;
        return callback(new Error('密码不能少于6位！'));
      } else if (value.length > 16) {
        this.changeFlag = 2;
        return callback(new Error('密码最长不能超过16位！'));
      } else if (!reg.test(value)) {
        this.changeFlag = 2;
        return callback(new Error('密码输入有误，请检查格式是否正确！'));
      } else if (this.operator.old == value) {
        this.changeFlag = 2;
        return callback(new Error('新密码不能和旧密码一样'));
      }
      else {
        this.changeFlag = 1;
        callback()
      }
    };
    // 重复密码验证
    const pwdAgainCheck = async (rule, value, callback) => {
      if (value.length < 1) {
        this.changeAgainFlag = 2;
        return callback(new Error('重复密码不能为空！'));
      } else if (this.operator.password != this.operator.againPassword) {
        this.changeAgainFlag = 2;
        return callback(new Error('两次输入密码不一致！'));
      } else {
        this.changeAgainFlag = 1;
        callback()
      }
    };
    return {
      flag: false,
      numbers: 100,
      changeAgainFlag: "",
      changeFlag: "",
      list: [],
      operator: {
        phone: "",
        oid: "1"
      },
      operator1:{
        old: "",
        oldPassword: "",
        password: "",
        againPassword: "",
        oid: "1"
      },
      labelCol: {
        style: { width: "150px", float: "left", position: "relative" },
      },
      wrapperCol: {
        span: 14,
        style: { width: "300px", position: "relative", float: "left" },
      },
      rules: {
        oldPassword: [
          { required: true, validator: oldpwdCheck, trigger: 'blur' }
        ],
        password: [
          { required: true, validator: pwdCheck, trigger: 'blur' }
        ],
        againPassword: [
          { required: true, validator: pwdAgainCheck, trigger: 'blur', }
        ],
        phone: [
          {
            required: true,
            pattern: /^1(3[0-9]|4[01456879]|5[0-3,5-9]|6[2567]|7[0-8]|8[0-9]|9[0-3,5-9])\d{8}$/,
            message: '请输入正确的手机号'
          },
        ],
      }
    };
  },
  methods: {
    updatePhone(operator) {
      this.$refs[operator].validate((valid) => {
        if (valid) {   // 如果校验通过，请求接口，允许提交表单
          update(this.operator).then(res => {
            this.$message.success(
              res.data.msg,
              10,
            );
          });
          this.$router.replace({
            path: '/login',
          });
        } else {   //校验不通过
          this.$message.success(
            '输入信息错误,请更正',
            10,
          );
          return false;
        }
      })
    },
    updatePwd(operator1) {
      this.$refs[operator1].validate((valid) => {
        if (valid) {   // 如果校验通过，请求接口，允许提交表单
          update(this.operator1).then(res => {
            this.$message.success(
              res.data.msg,
              10,
            );
          });
          this.$router.replace({
            path: '/login',
          });
        } else {   //校验不通过
          this.$message.success(
            '输入信息错误,请更正',
            10,
          );
          return false;
        }
      })
    },
  },
  mounted:
    function () {
      queryById(this.operator.oid).then((res) => {
        this.operator.phone = res.data.phone;
        //this.operator.old = res.data.password;
        this.operator.oid = res.data.oid;
        this.operator1.old = res.data.password;
        this.operator1.oid = res.data.oid;
      });
    },
  watch: {
    $route() {
      queryById(this.operator.oid).then((res) => {
        this.operator.phone = res.data.phone;
        //this.operator.old = res.data.password;
        this.operator.oid = res.data.oid;
        this.operator1.old = res.data.password;
        this.operator1.oid = res.data.oid;
      });
    },
  },

};
</script>
<style>
.content{
  margin-left: 25%;
}
</style>
