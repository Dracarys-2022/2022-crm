  <template >
  <div class="new-page" style="background-color: white">
    <a-form-model ref="visit" :model="visit" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal"
      :rules="rules">
      <a-form-model-item class="a" label="所属公司名称" prop="company">
        <a-input name="company" v-model="visit.company" placeholder="请输入所属公司名称" />
      </a-form-model-item>
      <a-form-model-item class="a" label="拜访客户" prop="client">
        <a-input name="client" v-model="visit.client" placeholder="请输入拜访客户" />
      </a-form-model-item>
      <a-form-model-item class="a" label="陪同人员" prop="accompany">
        <a-input name="accompany" v-model="visit.accompany" placeholder="请输入陪同人员" />
      </a-form-model-item>
      <a-form-model-item class="a" label="联系方式:" prop="mobile">
        <a-input name="phone" v-model="visit.mobile" placeholder="请输入联系方式">
          <a-select slot="addonBefore" v-model="visit.select" style="width: 70px">
            <a-select-option value="86">
              +86
            </a-select-option>
            <a-select-option value="87">
              +87
            </a-select-option>
          </a-select>
        </a-input>
      </a-form-model-item>
      <a-form-model-item class="a" label="拜访主题" prop="theme">
        <a-input name="theme" v-model="visit.theme" placeholder="请输入拜访主题" />
      </a-form-model-item>
      <!-- <a-form-model-item class="a" label="公司类型">
        <a-radio-group v-model="client.category">
          <a-radio value="股份有限公司">
            股份有限公司
          </a-radio>
          <a-radio value="有限责任公司">
            有限责任公司
          </a-radio>
        </a-radio-group>
      </a-form-model-item> -->
      <a-form-model-item class="a" label="拜访目的" prop="purpose">
        <a-input name="purpose" v-model="visit.purpose" placeholder="请输入拜访目的" />
      </a-form-model-item>
      <a-form-model-item class="a" label="拜访前准备" prop="ready">
        <a-input name="ready" v-model="visit.ready" placeholder="请输入拜访前准备" />
      </a-form-model-item>
      <!-- <a-form-model-item class="a" label="公司性质">
        <a-radio-group v-model="client.quality">
          <a-radio value="国有企业">
            国有企业
          </a-radio>
          <a-radio value="集体企业">
            集体企业
          </a-radio>
          <a-radio value="私营企业">
            私营企业
          </a-radio>
          <a-radio value="联营企业">
            联营企业
          </a-radio>
          <a-radio value="合伙企业">
            合伙企业
          </a-radio>
          <a-radio value="个体工商户">
            个体工商户
          </a-radio>
          <a-radio value="股份合作制企业">
            股份合作制企业
          </a-radio>
        </a-radio-group>
      </a-form-model-item> -->
      <a-form-model-item class="a" label="拜访结果" prop="outcome">
        <a-input v-model="visit.outcome" placeholder="请输入拜访结果" />
      </a-form-model-item>
      <a-form-model-item class="a" label="客户态度" prop="attitude">
        <a-input v-model="visit.attitude" placeholder="请输入客户态度" />
      </a-form-model-item>
      <a-form-model-item class="a" label="活动经费" prop="fund">
        <a-input v-model="visit.fund" placeholder="请输入活动经费" />
      </a-form-model-item>
     <a-form-model-item
          class="a"
          name="date-time-picker"
          label="拜访日期"
          prop="createtime"
        >
          <a-date-picker
            v-model="visit.createtime"
            show-time
            format="YYYY-MM-DD HH:mm"
            value-format="YYYY-MM-DD HH:mm"
          />
        </a-form-model-item>
      <a-form-model-item class="a" label="备注" prop="remark">
        <a-input v-model="visit.remark" type="textarea" />
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
        <a-button type="primary" html-type="submit" @click="Insubmit()">填写</a-button>
        <a-button style="margin-left: 20px" @click="resetForm()">重置</a-button>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>

<script>
import {addvisit} from "@/services/visit"
export default {
  data() {
    return {
      flag: false,
      numbers: 100,
      visit: {
        company: "",
        client:"",
        select: "+86",
        accompany: "",
        theme: "",
        phone: "",
        mobile:"",
        purpose: "",
        ready: "",
        outcome: "",
        attitude: "",
        fund: "",
        remark: "",
        createtime:''
      },
      labelCol: {
        style: { width: "150px", float: "left", position: "relative" },
      },
      wrapperCol: {
        span: 14,
        style: { width: "300px", position: "relative", float: "left" },
      },
      rules: {
        company:
          { required: true, message: '客户公司名称不能为空', trigger: 'blur' },
        client: {
          required: true, message: '拜访客户不能为空', trigger: 'blur',
        },
        mobile: [
          {
            required: true,
            pattern: /^1(3[0-9]|4[01456879]|5[0-3,5-9]|6[2567]|7[0-8]|8[0-9]|9[0-3,5-9])\d{8}$/,
            message: '请输入正确的手机号'
          },
        ],
        theme: { required: true, trigger: 'blur', message: '拜访主题不能为空' },
        ready: { required: true, trigger: 'blur', message: '拜访前准备不能为空' },
        outcome: { required: true, trigger: 'blur', message: '拜访结果不能为空' },
        attitude: { required: true, trigger: 'blur', message: '客户态度不能为空' },
        fund: { required: true, trigger: 'blur', message: '活动经费不能为空' },
        createtime: { required: true, trigger: 'blur', message: '拜访时间不能为空' },
        purpose: { required: true, trigger: 'blur', message: '拜访目的不能为空' }
      }
    };
  },

  methods: {
    Insubmit() {
      this.$refs.visit.validate((valid) => {
        if (valid) {   // 如果校验通过，请求接口，允许提交表单
          this.visit.phone = this.visit.select + this.visit.mobile
          addvisit(this.visit).then(res=>{
            if(res.data.msg!=""){
                this.$message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }else{
                this.$message.success(
                '填写成功！'
        );
            }
          })
        } else {   //校验不通过
          alert("请重新输入")
          return false;
        }
      })
    },
    resetForm() {
      this.$refs.visit.resetFields();
    }



    //}
  },
  watch: {},

};

</script>
<style>
.a {
  left: 30%;
}
</style>
