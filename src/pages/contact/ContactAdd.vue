<template >
    <div class="new-page" style="background-color: white">
        <a-form-model ref="contact" :model="contact" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal"
            :rules="rules">
            <a-form-model-item class="a" label="客户联系人名称" prop="coname">
                <a-input name="coname" v-model="contact.coname" placeholder="请输入客户联系人名称" />
            </a-form-model-item>
            <a-form-model-item class="a" label="客户公司名称:" prop="cid">
                <a-select v-model="contact.cid" placeholder="请选择客户公司">
                    <a-select-option :key="i" v-for="(it, i) in list">
                        {{ it.cname }}
                    </a-select-option>
                </a-select>
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
            <a-form-model-item class="a" label="备注" prop="remark">
                <a-input v-model="contact.remark" type="textarea" />
            </a-form-model-item>
            <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
                <a-button type="primary" html-type="submit" @click="Insubmit('contact')">注册</a-button>
                <a-button style="margin-left: 20px" @click="resetForm('contact')">重置</a-button>
            </a-form-model-item>
        </a-form-model>
    </div>
</template>

<script>
import { add } from "@/services/contactAdd"
import { queryList } from "@/services/clientList";
export default {
    data() {
        return {
            flag: false,
            numbers: 100,
            list: [],
            contact: {
                coname: "",
                select: "+86",
                phone: "",
                mobile: "",
                operid: "",
                address: "",
                remark: "",
                cid: "",
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
                    [{ required: true, message: '客户联系人不能为空', trigger: 'blur' },
                    {
                        message: "联系人只能为汉字",
                        pattern: /^[\u4E00-\u9FA5]+$/,
                        trigger: "change",
                        min: 2
                    }],
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
    mounted: function () {
        queryList().then(res => {
            this.list = res.data
            //业务逻辑
            // queryList().then(res => {
            //   this.list = res.data.list
            //   this.paging.rowcount=res.data.rowcount
            //   this.paging.currpage=res.data.currpage
            // console.log(this.paging.rowcount)
            // console.log(this.paging.currpage)
            //console.log(this.list[0].createtime)
        })
    },
    methods: {
        Insubmit(contact) {
            this.contact.operid = localStorage.getItem('localOperator');
            this.contact.cid = this.list[this.contact.cid].cid
            this.$refs[contact].validate((valid) => {
                if (valid) {   // 如果校验通过，请求接口，允许提交表单
                    this.contact.phone = this.contact.select + this.contact.mobile
                    add(this.contact).then(res => {
                        this.$message.success(
                            res.data.msg,
                            10,
                        );

                        this.$refs[contact].resetFields();
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
        resetForm(contact) {
            this.$refs[contact].resetFields();
        }
        //}
    },
    watch: {},
    created(){
        var aaa=localStorage.getItem('permissions');
    var aa=aaa.replace("\"","").replace("\"","");
    if(localStorage.getItem('access-admin')==""||localStorage.getItem('access-admin')==null){
      this.$message.success("请重新登录！")
      this.$router.push({
          path: "/login"
        });
        return;
    }
   if (!aa.split(",").includes("66")) {
      this.$message.success("您没有权限")
      this.$router.push({
        path: "/403"
        });
        return;
   }
        queryList().then(res => {
            this.list = res.data
            if(res.data.msg=="您没有权限进行此操作!"){
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
        })
    }
};

</script>
<style>
.a {
    left: 30%;
}
</style>
