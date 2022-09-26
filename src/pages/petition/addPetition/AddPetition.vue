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
      <a-form-model-item class="a" label="投诉内容" prop="content">
        <a-textarea
          v-model="petition.content"
          placeholder="请输入主要投诉内容"
          :rows="4"
        />
      </a-form-model-item>
      <a-form-model-item class="a" label="处理人" prop="executor">
        <a-select v-model="petition.executor" placeholder="选择处理人">
           <a-select-option v-for="(it,i) in oper" :key="i" :value="it.oid">{{it.opername}}</a-select-option>
          <!-- <a-select-option value="1">韩总</a-select-option>
          <a-select-option value="2">索总</a-select-option> -->
        </a-select>
      </a-form-model-item>
      <a-form-model-item
        class="a"
        name="date-time-picker"
        label="记录时间"
        prop="createtime"
      >
        <a-date-picker
          v-model="petition.createtime"
          show-time
          format="YYYY-MM-DD HH:mm"
          value-format="YYYY-MM-DD HH:mm"
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
import { add ,getOper} from "@/services/petition";
import { message } from "ant-design-vue";
export default {
  data() {
    return {
      oper:[],
      petition: {
        content: "",
        executor: "",
        createtime: "",
        operid: "1",
      },
      rules: {
        content: {
          required: true,
          message: "投诉内容不能为空",
          trigger: "change",
        },
        executor: {
          required: true,
          message: "处理人不能为空",
          trigger: "change",
        },
        createtime: {
          required: true,
          message: "投诉时间不能为空",
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
     getOper().then(res=>{
      this.oper=res.data.data
    })
  },
  methods: {
    Insubmit(petition) {
      this.$refs[petition].validate((valid) => {
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          add(this.petition).then(this.afterSubmit);
          this.$refs[petition].resetFields();
        } else {
          //校验不通过
          message.warning("输入内容不规范，请重新输入");
          return false;
        }
      });
    },
    afterSubmit: function (res) {
      if (res.data.code == "0001") {
        message.success(res.data.msg);
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
.btn{
  left: 75px;
}
</style>