  <template >
    <div class="new-page" style="background-color: white">
        <a-form-model ref="operator" :model="operator" :label-col="labelCol" :wrapper-col="wrapperCol"
            layout="horizontal" :rules="rules">
            <a-form-model-item class="a" label="用户名" prop="oname">
                <a-input name="oname" v-model="operator.oname" placeholder="请输入用户" />
            </a-form-model-item>
            <a-form-model-item class="a" label="密码" prop="password">
                <a-input-password name="password" v-model="operator.password" placeholder="请输入密码" />
                <span>6-16位字符，同时包括数字、大小写字母和特殊字符四种组合； 特殊字符需在“~ @ # $ % * _ - + = : , . ?”。</span>
                <a-icon type="ios-checkmark-circle" v-if="changeFlag == 1" color="#35B449" />
                <a-icon type="md-close-circle" v-else-if="changeFlag == 2" color="#f00" />
            </a-form-model-item>

            <a-form-model-item class="a" label="重复密码" prop="againPassword">
                <a-input-password name="againPassword" v-model="operator.againPassword" placeholder="请再次输入密码" />
                <a-icon type="ios-checkmark-circle" v-if="changeAgainFlag == 1" color="#35B449" />
                <a-icon type="md-close-circle" v-else-if="changeAgainFlag == 2" color="#f00" />
            </a-form-model-item>
            <a-form-model-item class="a" label="用户角色:" prop="roid">
                <a-select v-model="operator.roid" placeholder="请选择用户角色">
                    <a-select-option :key="i" v-for="(it, i) in list">
                        {{ it.roname }}
                    </a-select-option>
                </a-select>
            </a-form-model-item>
            <a-form-model-item class="a" label="手机号:" prop="phone">
                <a-input name="phone" v-model="operator.phone" placeholder="请输入联系人电话">
                </a-input>
            </a-form-model-item>
            <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
                <a-button type="primary" html-type="submit" @click="Insubmit('operator')">注册</a-button>
                <a-button style="margin-left: 20px" @click="resetForm('operator')">重置</a-button>
            </a-form-model-item>
        </a-form-model>
    </div>
</template>

<script>
import { add } from "@/services/operatorAdd";
import { queryList } from "@/services/rolesList";
export default {
    data() {
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
            } else {
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
                oname: "",
                password: "",
                againPassword: "",
                phone: "",
                roid: "",
            },
            labelCol: {
                style: { width: "150px", float: "left", position: "relative" },
            },
            wrapperCol: {
                span: 14,
                style: { width: "300px", position: "relative", float: "left" },
            },
            rules: {
                roid: {
                    required: true, message: '请为用户选择定角色', trigger: 'blur'
                },
                password: [
                    { required: true, validator: pwdCheck, trigger: 'blur' }
                ],
                againPassword: [
                    { required: true, validator: pwdAgainCheck, trigger: 'blur', }
                ],
                oname:
                    { required: true, message: '用户名不能为空', trigger: 'blur' },
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
    mounted: function () {
        queryList().then(res => {
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
            this.list = res.data
        })
    },
    methods: {
        Insubmit(operator) {
            this.operator.roid = this.list[this.operator.roid].roid
            this.$refs[operator].validate((valid) => {
                if (valid) {   // 如果校验通过，请求接口，允许提交表单
                    add(this.operator).then(res => {
                        this.$message.success(
                            res.data.msg,
                            10,
                        );

                        this.$refs[operator].resetFields();
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
        resetForm(operator) {
            this.$refs[operator].resetFields();
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
