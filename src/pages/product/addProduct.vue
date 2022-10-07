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
        <a-form-model-item label="选择类型" class="a" prop="type">
          <a-radio-group v-model="source.type">
            <a-radio value="1" @click="choosepid()">产品</a-radio>
            <a-radio value="2" @click="choosesid()">服务</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item class="a" label="服务/产品名称" prop="pname">
          <a-input v-model="source.pname" prop="pname" />
        </a-form-model-item>
        <a-form-model-item class="a" label="服务/产品类型" prop="category">
          <a-input v-model="source.category" prop="category" />
        </a-form-model-item>
        <a-form-model-item class="a" label="成本价" prop="price">
          <a-input v-model="source.price" prop="price" />
        </a-form-model-item>
        <a-form-model-item v-if="flag" class="a" label="产品单位" prop="units">
          <a-input v-model="source.units" prop="units" />
        </a-form-model-item>
        <a-form-model-item
          v-if="flag"
          class="a"
          label="产品规格"
          prop="specification"
        >
          <a-input v-model="source.specification" prop="specification" />
        </a-form-model-item>
        <a-form-model-item
          v-if="flag"
          class="a"
          label="产品保质期"
          prop="shelflife"
        >
          <a-input v-model="source.shelflife" prop="shelflife" />
        </a-form-model-item>
        <a-form-model-item class="a" label="市场价" prop="marketprice">
          <a-input v-model="source.marketprice" prop="marketprice" />
        </a-form-model-item>
        <a-form-item :wrapper-col="{ offset: 8, span: 16 }" class="btn">
          <a-button
            type="primary"
            html-type="submit"
            @click="Insubmit('source')"
            >确定录入</a-button
          >
          <a-button @click="resetForm('source')">重置</a-button>
        </a-form-item>
      </a-form-model>
    </div>
  </a-card>
</template>

<script>
import { addproduct } from "@/services/product";
import { message } from "ant-design-vue";
export default {
  data() {
    return {
      source: {
        status: "",
        pname: "",
        price: "",
        marketprice: "",
        category: "",
        units: "",
        specification: "",
        shelflife: "",
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
          message: "类型不能为空",
          trigger: "change",
        },
        pname: {
          required: true,
          message: "名称不能为空",
          trigger: "change",
        },
        category: {
          required: true,
          message: "产品或服务类型不能为空",
          trigger: "change",
        },
        units: {
          required: true,
          message: "单位不能为空",
          trigger: "change",
        },
        price: [{
          required: true,
          message: "成交金额不能为空",
          trigger: "change",
        },{
          pattern: /^(([1-9]{1}\d{0,9})|(0{1}))(\.\d{1,2})?$/,
          message: "成交金额格式错误",
          trigger: "change",
        }],
        marketprice: [{
          required: true,
          message: "市场价不能为空",
          trigger: "change",
        },{
          pattern: /^(([1-9]{1}\d{0,9})|(0{1}))(\.\d{1,2})?$/,
          message: "市场价格式错误",
          trigger: "change",
        }],
        specification: [{
          required: true,
          message: "规格不能为空",
          trigger: "change",
        },{
          message: "规格只能为汉字",
          pattern: /^[\u4E00-\u9FA5]+$/,
          trigger: "change",
        }],
        shelflife: [{
          required: true,
          message: "保质期不能为空",
          trigger: "change",
        },{
          message: "保质期只能为数字",
          pattern: /^[0-9]*$/,
          trigger: "change",
        }],
      },
    };
  },
  methods: {
    choosepid() {
      this.flag = true;
      this.source.type = 1;
    },
    choosesid() {
      this.flag = false;
      this.source.type = 2;
    },
    Insubmit(source) {
      this.$refs[source].validate((valid) => {
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          addproduct(this.source).then(res=>{
            if(res.data.msg!=""){
                this.$message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }else{
                this.$message.success(
                '添加成功'
        );
            }
          });
          this.$refs[source].resetFields();
        } else {
          //校验不通过
          message.warning("输入内容不规范，请重新输入");
          return false;
        }
      });
    },
    // afterSubmit: function (res) {
    //   if (res.data.code == "0001") {
    //     message.success(res.data.msg);
    //   }
    // },
    resetForm(source) {
      this.$refs[source].resetFields();
    },
  },
  watch: {},
  created() {},
};
</script>

<style>
.a {
  left: 25%;
}
.btn {
  left: 400px;
}
</style>