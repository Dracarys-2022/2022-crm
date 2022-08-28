<template>
  <a-card>
    <div>
      <a-form-model
        ref="source"
        :model="source"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        layout="horizontal"
        :rules="rules"
      >
        <a-form-model-item label="订单类型" class="a" prop="type">
          <a-radio-group v-model="source.type">
            <a-radio value="1" @click="choosepid()">产品</a-radio>
            <a-radio value="2" @click="choosesid()">服务</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item class="a" label="产品或服务" prop="pid">
          <a-select v-model="source.pid" placeholder="选择产品">
            <a-select-option
              v-for="(it, i) in prouct"
              :key="i"
              :value="it.pid"
              >{{ it.pname }}</a-select-option
            >
          </a-select>
        </a-form-model-item>
        <a-form-model-item
          v-if="flag"
          class="a"
          label="订单数量"
          prop="numbers"
        >
          <a-input v-model="source.numbers" prop="numbers" />
        </a-form-model-item>
        <a-form-model-item class="a" label="成交金额" prop="price">
          <a-input v-model="source.price" prop="price" />
        </a-form-model-item>
        <a-form-model-item class="a" label="客户" prop="cid">
          <a-select v-model="source.cid" placeholder="选择客户">
            <a-select-option v-for="(it, i) in c" :key="i" :value="it.pid">{{
              it.pname
            }}</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item class="a" label="客户联系人" prop="coid">
          <a-select v-model="source.coid" placeholder="选择客户联系人">
            <a-select-option v-for="(it, i) in co" :key="i" :value="it.pid">{{
              it.pname
            }}</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item
          class="a"
          name="date-time-picker"
          label="开始时间"
          prop="createtime"
        >
          <a-date-picker
            v-model="source.createtime"
            show-time
            format="YYYY-MM-DD HH:mm"
            value-format="YYYY-MM-DD HH:mm"
          />
        </a-form-model-item>

        <a-form-model-item
          name="date-picker"
          class="a"
          label="截止时间"
          prop="deadline"
        >
          <a-date-picker v-model="source.deadline" value-format="YYYY-MM-DD" />
        </a-form-model-item>

        <a-form-item :wrapper-col="{ offset: 8, span: 16 }" class="btn">
          <a-button
            type="primary"
            html-type="submit"
            @click="Insubmit('source')"
            >添加订单</a-button
          >
          <a-button style="margin-left: 20px" @click="resetForm('source')"
            >重置</a-button
          >
        </a-form-item>
      </a-form-model>
    </div>
  </a-card>
</template>

<script>
import { add, getproduct } from "@/services/orders";
import { message } from "ant-design-vue";
export default {
  data() {
    return {
      prouct: [],
      co: [],
      c: [],
      source: {
        state: "",
        oid: "",
        price: "",
        numbers: "",
        coid: "",
        cid: "",
        pid: "",
        createtime: "",
        deadline: "",
        operid: "",
        type: "",
      },
      flag: true,
      labelCol: {
        style: { width: "150px", float: "left", position: "relative" },
      },
      wrapperCol: {
        span: 14,
        style: { width: "300px", position: "relative", float: "left" },
      },
      rules: {
        type: {
          required: true,
          message: "订单类型不能为空",
          trigger: "change",
        },
        numbers: {
          required: true,
          message: "订单数量不能为空",
          trigger: "change",
        },
        pid: {
          required: true,
          message: "产品或服务不能为空",
          trigger: "change",
        },
        cid: {
          required: true,
          message: "客户不能为空",
          trigger: "change",
        },
        price: {
          required: true,
          message: "成交金额不能为空",
          trigger: "change",
        },
        coid: {
          required: true,
          message: "客户联系人不能为空",
          trigger: "change",
        },
        deadline: {
          required: true,
          message: "截止时间不能为空",
          trigger: "change",
        },
        createtime: {
          required: true,
          message: "下单时间不能为空",
          trigger: "change",
        },
      },
    };
  },
  methods: {
    choosepid() {
      this.flag = true;
    },
    choosesid() {
      this.flag = false;
    },
    Insubmit(source) {
      this.$refs[source].validate((valid) => {
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          add(this.source).then(this.afterSubmit);
          this.$refs[source].resetFields();
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
    resetForm(source) {
      this.$refs[source].resetFields();
    },
  },
  watch: {},
  created() {
    getproduct().then((res) => {
      this.co = res.data.data;
      this.c = res.data.data;
      this.prouct = res.data.data;
    });
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