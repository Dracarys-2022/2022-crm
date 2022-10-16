<template>
  <a-card>
    <a-form-model
      ref="petition"
      :model="petition"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
      layout="horizontal"
      :rules="rules"
    >
      <a-form-model-item class="a" label="投诉id" prop="peid" v-show="false">
        <a-input v-model="petition.peid" />
      </a-form-model-item>
      <a-form-model-item class="a" label="投诉结果" prop="outcome">
        <a-textarea
          v-model="petition.outcome"
          placeholder="请输入投诉结果"
          :rows="4"
        />
      </a-form-model-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }" class="btn">
        <a-button
          type="primary"
          html-type="submit"
          @click="Insubmit('petition')"
          >录入</a-button
        >
        <a-button style="margin-left: 20px" @click="resetForm('petition')"
          >重置</a-button
        >
      </a-form-item>
    </a-form-model>
  </a-card>
</template>
<script>
import { message } from "ant-design-vue";
import { addoutcome } from "@/services/petition";
export default {
  data() {
    return {
      petition: {
        outcome: "",
        peid: "",
      },
      rules: {
        outcome: {
          required: true,
          message: "投诉内容不能为空",
          trigger: "change",
        },
      },
      labelCol: {
        style: { width: "150px", float: "left", position: "relative" },
      },
      wrapperCol: {
        span: 14,
        style: { width: "300px", position: "relative", float: "left" },
      },
    };
  },
  created() {
    var aaa=localStorage.getItem('permissions');
    var aa=aaa.replace("\"","").replace("\"","");
    if(localStorage.getItem('access-admin')==""||localStorage.getItem('access-admin')==null){
      this.$message.success("请重新登录！")
      this.$router.push({
          path: "/login"
        });
        return;
    }
   if (!aa.split(",").includes("36")) {
      this.$message.success("您没有权限")
      this.$router.push({
        path: "/403"
        });
        return;
   }
    this.petition.peid = this.$route.query.peid;
  },
  methods: {
    Insubmit(petition) {
      this.$refs[petition].validate((valid) => {
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          addoutcome(this.petition).then(this.afterSubmit);
        } else {
          //校验不通过
          message.warning("输入内容不规范，请重新输入");
          return false;
        }
      });
    },
    afterSubmit: function (res) {
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
      if (res.data.code == "0001") {
        this.$closePage(this.$route, "/petition/listpetition");
      }
    },
    resetForm(petition) {
      this.$refs[petition].resetFields();
    },
  },
};
</script>


<style>
.a {
  left: 25%;
}
.btn {
  left: 75px;
}
</style>