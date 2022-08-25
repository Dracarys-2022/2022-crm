<template >
    <div class="new-page" style="background-color: white">
        <a-form-model ref="contact" :model="contact" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal"
            :rules="rules">
            <a-form-model-item class="a" label="客户联系人名称" prop="coname">
                <a-input name="coname" v-model="contact.coname" readOnly placeholder="请输入客户联系人名称" />
            </a-form-model-item>
            <a-form-model-item class="a" label="客户公司名称:" prop="cname">
                <a-input name="cname" v-model="contact.cname" readOnly placeholder="请输入客户联系人名称" />
            </a-form-model-item>
            <a-form-model-item class="a" label="手机号:" prop="mobile">
                <a-input name="mobile" v-model="contact.mobile" placeholder="请输入联系人电话">
                    <a-select slot="addonBefore" v-model="contact.select" style="width: 70px">
                        <a-select-option value="+86">
                            +86
                        </a-select-option>
                        <a-select-option value="+886">
                            +886
                        </a-select-option>
                    </a-select>
                </a-input>
            </a-form-model-item>
            <a-form-model-item class="a" label="联系人地址" prop="address">
                <a-input name="address" v-model="contact.address" placeholder="请输入联系人地址" />
            </a-form-model-item>
            <a-form-model-item class="a" label="负责人:" prop="oname">
                <a-select v-model="contact.oname" placeholder="请选择用户角色">
                    <a-select-option :key="i" v-for="(it, i) in list">
                        {{ it.oname }}
                    </a-select-option>
                </a-select>
            </a-form-model-item>
            <a-form-model-item class="a" label="备注" prop="remark">
                <a-input v-model="contact.remark" type="textarea" />
            </a-form-model-item>
            <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
                <a-button type="primary" html-type="submit" @click="update('contact')">修改</a-button>
            </a-form-model-item>
        </a-form-model>
    </div>
</template>

<script>
import { update } from "@/services/contactUpdate"
import { queryById } from "@/services/contactById"
import { queryList } from "@/services/operatorList"
export default {
    data() {
        return {
            flag: false,
            numbers: 100,
            list: [],
            contact: {
                coname: "",
                cname: "",
                select: "+86",
                phone: "",
                mobile: "",
                address: "",
                remark: "",
                old: "",
                cid: "",
                operid: "",
                oname: "",
                coid: "",
                oid: "",
            },
            labelCol: {
                style: { width: "150px", float: "left", position: "relative" },
            },
            wrapperCol: {
                span: 14,
                style: { width: "300px", position: "relative", float: "left" },
            },
            rules: {
                coname:
                    { required: true, message: '客户联系人不能为空', trigger: 'blur' },
                cid:
                    { required: true, message: '客户公司不能为空', trigger: 'blur' },
                mobile: [
                    {
                        required: true,
                        pattern: /^1(3[0-9]|4[01456879]|5[0-3,5-9]|6[2567]|7[0-8]|8[0-9]|9[0-3,5-9])\d{8}$/,
                        message: '请输入正确的手机号'
                    },
                ],
                address: { required: true, trigger: 'blur', message: '地址不能为空' },
            }
        };
    },

    methods: {
        update(contact) {
            if (this.contact.old != this.contact.oname) {
                this.contact.operid = this.list[this.contact.oname].oid
            }
            console.log(this.contact.operid)
            this.$refs[contact].validate((valid) => {
                if (valid) {   // 如果校验通过，请求接口，允许提交表单
                    this.contact.phone = this.contact.select + this.contact.mobile
                    //console.log(this.contact)
                    update(this.contact).then(res => {
                        if (res.data.code == "0000") {
                            this.$message.success(
                                res.data.msg,
                                10,
                            );
                            this.$router.replace({
                                path: '/contact/contactList',

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
    mounted: function () {
        this.contact.coid = this.$route.query.coid
        //console.log(this.$route.query.coid)
        queryById(this.contact.coid).then(res => {
            this.contact.cname = res.data.cname
            this.contact.coname = res.data.coname
            this.contact.address = res.data.address
            this.contact.cid = res.data.cid
            this.contact.coid = res.data.coid
            this.contact.old = res.data.oname
            this.contact.remark = res.data.remark
            this.contact.oid = res.data.oid
            this.contact.phone = res.data.phone
            this.contact.oname = res.data.oname
            this.contact.select = res.data.phone.substring(0, res.data.phone.indexOf("1"))
            this.contact.mobile = res.data.phone.substring(res.data.phone.indexOf("1"), res.data.phone.length)
        });
        queryList().then(res => {
            this.list = res.data
        })
        this.$message.success(
            '查询成功',
            10,
        );
    },
};

</script>
<style>
.a {
    left: 30%;
}
</style>
