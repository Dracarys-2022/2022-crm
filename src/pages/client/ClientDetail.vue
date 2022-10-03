  <template >
    <div class="new-page" style="background-color: white">
        <a-form-model ref="client" :model="client" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal"
            :rules="rules">
            <a-form-model-item class="a" label="编号" prop="cid">
                <a-input name="cid" v-model="client.cid" readOnly />
            </a-form-model-item>
            <a-form-model-item class="a" label="客户公司名称" prop="cname">
                <a-input name="cname" v-model="client.cname" readOnly placeholder="请输入客户公司名称" />
            </a-form-model-item>
            <a-form-model-item class="a" label="法人" prop="legalperson">
                <a-input name="legalperson" v-model="client.legalperson" placeholder="请输入公司法人" />
            </a-form-model-item>
            <a-form-model-item class="a" label="注册资金" prop="registered">
                <a-input name="registered" v-model="client.registered" placeholder="请输入公司注册资金" />
            </a-form-model-item>
            <a-form-model-item class="a" label="联系方式:" prop="mobile">
                <a-input name="mobile" v-model="client.mobile" placeholder="请输入公司联系方式">
                    <a-select slot="addonBefore" v-model="client.select" style="width: 70px">
                        <a-select-option value="+86">
                            +86
                        </a-select-option>
                        <a-select-option value="+886">
                            +886
                        </a-select-option>
                    </a-select>
                </a-input>
            </a-form-model-item>
            <a-form-model-item class="a" label="公司地址" prop="address">
                <a-input name="address" v-model="client.address" placeholder="请输入公司地址" />
            </a-form-model-item>
            <a-form-model-item class="a" label="公司类型">
                <a-radio-group v-model="client.category">
                    <a-radio value="股份有限公司">
                        股份有限公司
                    </a-radio>
                    <a-radio value="有限责任公司">
                        有限责任公司
                    </a-radio>
                </a-radio-group>
            </a-form-model-item>
            <a-form-model-item class="a" label="银行账号" prop="account">
                <a-input name="account" v-model="client.account" placeholder="请输入客户公司银行账号" />
            </a-form-model-item>
            <a-form-model-item class="a" label="公司所属行业" prop="industry">
                <a-input name="industry" v-model="client.industry" placeholder="请输入客户公司所属行业" />
            </a-form-model-item>
            <a-form-model-item class="a" label="公司性质">
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
            </a-form-model-item>
            <a-form-model-item class="a" label="录入人" prop="oname">
                <a-input v-model="client.oname" readOnly  />
            </a-form-model-item>
            <a-form-model-item class="a" label="备注" prop="remark">
                <a-input v-model="client.remark" type="textarea" />
            </a-form-model-item>
            <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
                <a-button type="primary" html-type="submit" @click="update('client')">修改</a-button>
            </a-form-model-item>
        </a-form-model>
    </div>
</template>

<script>
import { update } from "@/services/clientUpdate"
import { queryById } from "@/services/clientById"
export default {
    data() {
        return {
            flag: false,
            numbers: 100,
            client: {
                cid: "",
                cname: "",
                select: "",
                legalperson: "",
                registered: "",
                phone: "",
                mobile: "",
                address: "",
                category: "",
                account: "",
                industry: "",
                quality: "",
                oname:"",
                remark: "",
                status: ""
            },
            labelCol: {
                style: { width: "150px", float: "left", position: "relative" },
            },
            wrapperCol: {
                span: 14,
                style: { width: "300px", position: "relative", float: "left" },
            },
            rules: {
                cname:
                    { required: true, message: '客户公司名称不能为空', trigger: 'blur' },
                legalperson: {
                    required: true, message: '客户公司法人格式不正确', trigger: 'blur', min: 2
                },
                mobile: [
                    {
                        required: true,
                        pattern: /^1(3[0-9]|4[01456879]|5[0-3,5-9]|6[2567]|7[0-8]|8[0-9]|9[0-3,5-9])\d{8}$/,
                        message: '请输入正确的手机号'
                    },
                ],
                account: { required: true, trigger: 'blur', min: 15, max: 19, message: '请输入正确的银行卡号' },
                address: { required: true, trigger: 'blur', message: '公司地址不能为空' },
                industry: { required: true, trigger: 'blur', message: '公司所属行业不能为空' }
            }
        };
    },

    methods: {
        update(client) {
            this.$refs[client].validate((valid) => {
                if (valid) {   // 如果校验通过，请求接口，允许提交表单
                    this.client.phone = this.client.select + this.client.mobile
                    update(this.client).then(res => {
                        if (res.data.code == "0000") {
                            this.$message.success(
                                res.data.msg,
                                10,
                            );
                            this.$router.replace({
                                path: '/client/clientList',
                                //query:{list:this.list}
                            });
                        } else {
                            this.$message.success(
                                res.data.msg,
                                10,
                            );
                        }

                    });
                } else {   //校验不通过
                    alert("请重新输入")
                    return false;
                }
            })
        },
        //}
    },
    watch: {
    $route() {
        this.client.cid = this.$route.query.cid
        //clientById(this.client)
        queryById(this.client.cid).then(res => {
            this.client.cname = res.data.cname
            this.client.legalperson = res.data.legalperson
            this.client.registered = res.data.registered
            this.client.address = res.data.address
            this.client.category = res.data.category
            this.client.account = res.data.account
            this.client.industry = res.data.industry
            this.client.oname=res.data.oname
            this.client.quality = res.data.quality
            this.client.remark = res.data.remark
            this.client.status = res.data.status
            this.client.phone = res.data.phone
            this.client.select = res.data.phone.substring(0, res.data.phone.indexOf("1"))
            this.client.mobile = res.data.phone.substring(res.data.phone.indexOf("1"), res.data.phone.length)
            if(res.data.msg!=""){
                this.$message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }else{
                this.$message.success(
                '查询成功',
            10,
        );
            }
        });
    },
  },
    created () {
        this.client.cid = this.$route.query.cid
        //clientById(this.client)
        queryById(this.client.cid).then(res => {
            this.client.cname = res.data.cname
            this.client.legalperson = res.data.legalperson
            this.client.registered = res.data.registered
            this.client.address = res.data.address
            this.client.category = res.data.category
            this.client.account = res.data.account
            this.client.industry = res.data.industry
            this.client.oname=res.data.oname
            this.client.quality = res.data.quality
            this.client.remark = res.data.remark
            this.client.status = res.data.status
            this.client.phone = res.data.phone
            this.client.select = res.data.phone.substring(0, res.data.phone.indexOf("1"))
            this.client.mobile = res.data.phone.substring(res.data.phone.indexOf("1"), res.data.phone.length)
            if(res.data.msg!=""){
                this.$message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }else{
                this.$message.success(
                '查询成功',
            10,
        );
            }
        });
    },
};

</script>
<style>
.a {
    left: 30%;
}
</style>
