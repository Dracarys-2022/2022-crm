<template>
  <div class="new-page" :style="`min-height: ${pageMinHeight}px`">
    <a-card>
      <a-form-model ref="source" :model="source" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal"
        :rules="rules">
        <a-form-model-item class="a" label="产品" prop="pid">
          <a-select v-model="source.pid" placeholder="选择产品">
            <a-select-option v-for="(it,i) in prouct" :key="i" :value="it.pid">{{it.pname}}</a-select-option>
            <!-- <a-select-option  value="1">产品A</a-select-option>
            <a-select-option value="2">产品B</a-select-option> -->
          </a-select>
        </a-form-model-item>
        <a-form-model-item class="a" label="进货量" prop="numbers">
          <a-input v-model="source.numbers" prop="numbers" />
        </a-form-model-item>
        <a-form-model-item class="a" prop="price" label="进货单位成本">
          <a-input v-model="source.price" />
        </a-form-model-item>
        <a-form-model-item class="a" label="仓库位置" prop="location">
          <a-select v-model="source.location" placeholder="选择产品存放仓库位置">
            <a-select-option value="A">A仓库</a-select-option>
            <a-select-option value="B">B仓库</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item class="a" name="date-time-picker" label="进货时间" prop="createtime">
          <a-date-picker v-model="source.createtime" show-time format="YYYY-MM-DD HH:mm"
            value-format="YYYY-MM-DD HH:mm" />
        </a-form-model-item>
        <a-form-model-item class="a" name="range-picker" label="保质期" prop="producetime">
          <a-range-picker v-model="source.producetime" value-format="YYYY-MM-DD" />
        </a-form-model-item>
        <a-form-model-item label="进货类型" class="a" prop="type" v-if="flase">
          <a-radio-group v-model="source.type">
            <a-radio value="0" @click="changeFlase()">新进</a-radio>
            <a-radio value="1" @click="changetrue()">退换</a-radio>
          </a-radio-group>
        </a-form-model-item>

        <a-form-model-item v-if="flag" class="a" label="产品质量" prop="state">
          <a-select v-model="source.state" placeholder="选择产品质量">
            <a-select-option value="0">正常</a-select-option>
            <a-select-option value="1">异常</a-select-option>
          </a-select>
        </a-form-model-item>

        <a-form-item :wrapper-col="{ offset: 8, span: 16 }" class="btn">
          <a-button type="primary" html-type="submit" @click="Insubmit('source')">入库</a-button>
          <a-button style="margin-left: 20px" @click="resetForm('source')">重置</a-button>
        </a-form-item>
      </a-form-model>
    </a-card>
  </div>
</template>

<script>
import { add, getproduct } from "@/services/stockIn";
import { message } from "ant-design-vue";
message.config({});
export default {
  data() {
    return {
      flag: false,
      prouct: [],
      formState: {
        "date-picker": "",
        "date-time-picker": "",
        "month-picker": "",
        "range-picker": "",
      },

      source: {
        operid: "",
        numbers: "",
        pid: "",
        price: "",
        location: "",
        createtime: '',
        type: "",
        state: "",
        producetime: '',
        endtime: '[]',
      },
      labelCol: {
        style: { width: "150px", float: "left", position: "relative" },
      },
      wrapperCol: {
        span: 14,
        style: { width: "300px", position: "relative", float: "left" },
      },
      rules: {
        numbers: [{
          required: true,
          message: "进货量不能为空",
          trigger: "change",
        },{
          message: "进货量只能为数字",
          pattern: /^[0-9]*$/,
          trigger: "change",
        }],
        pid: {
          required: true,
          message: "进货产品不能为空",
          trigger: "change",
        },
        location: {
          required: true,
          message: "仓库位置不能为空",
          trigger: "change",
        },
        price: [{
          required: true,
          message: "产品进价不能为空",
          trigger: "change",
        }, {
          pattern: /^(([1-9]{1}\d{0,9})|(0{1}))(\.\d{1,2})?$/,
          message: "产品进价格式错误",
          trigger: "change",
        }
        ],
        state: {
          required: true,
          message: "产品状态不能为空",
          trigger: "change",
        },
        type: {
          required: true,
          message: "产品类型不能为空",
          trigger: "change",
        },
        producetime: {
          required: true,
          message: "产品保质期不能为空",
          trigger: "change",
        },
        createtime: {
          required: true,
          message: "产品入库时间不能为空",
          trigger: "change",
        },
      },
    };
  },
  methods: {
    Insubmit(source) {
      this.$refs[source].validate((valid) => {
        if (valid) {
          // 如果校验通过，请求接口，允许提交表单
          let time = this.source.producetime.toString().split(",");
          this.source.producetime = time[0];
          this.source.endtime = time[1];
          this.source.operid = localStorage.getItem('localOperator');
          add(this.source).then(this.afterSubmit);
          this.$refs[source].resetFields();
        } else {
          //校验不通过
          message.warning("输入内容不规范，请重新输入")
          return false;
        }
      });
    },
    afterSubmit: function (res) {
      if (res.data.msg != "") {
        this.$message.success(
          // '查询成功',
          res.data.msg,
          10,
        )
      } else {
        this.$message.success(
          '查询成功'
        );
      }
      // if (res.data.code == "0001") {
      //   message.success("入库成功");
      // }
    },
    resetForm(source) {
      this.$refs[source].resetFields();
    },
    changeFlase: function () {
      this.flag = false;
      console.log(this.flag);
    },
    changetrue: function () {
      this.flag = true;
      console.log(this.flag);
    },
  },
  created() {
    getproduct().then(res => {
      if (res.data.msg == "您没有权限进行此操作!") {
        this.$message.success(
          // '查询成功',
          res.data.msg,
          10,
        )
      } else {
        this.$message.success(
          '查询成功'
        );
      }
      this.prouct = res.data.data
    })
  },
  watch: {},
};
</script>
<style>
.a {
  left: 25%;
}

.btn {
  left: 100px;
  ;
}
</style>