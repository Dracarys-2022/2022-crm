  <template >
    <div class="new-page" style="background-color: white">
        <a-form-model ref="operator" :model="operator" :label-col="labelCol" :wrapper-col="wrapperCol"
            layout="horizontal">
            <a-form-model-item class="a" label="用户名" prop="oname">
                <a-input name="oname" v-model="operator.oname" readOnly placeholder="请输入用户" />
            </a-form-model-item>
            <a-form-model-item class="a" label="用户角色:" prop="roid">
                <a-select v-model="operator.roname" placeholder="请选择用户角色">
                    <a-select-option :key="i" v-for="(it, i) in list">
                        {{ it.roname }}
                    </a-select-option>
                </a-select>
            </a-form-model-item>
            <a-form-model-item class="a" label="手机号:" prop="phone">
                <a-input name="phone" readOnly v-model="operator.phone" placeholder="请输入联系人电话">
                </a-input>
            </a-form-model-item>
            <a-form-model-item :wrapper-col="{ offset: 10, span: 16 }">
                <a-button type="primary" html-type="submit" @click="update()">修改</a-button>
            </a-form-model-item>
        </a-form-model>
    </div>
</template>

<script>
import { update } from "@/services/operatorUpdate";
import { queryById } from "@/services/operatorById";
import { queryList } from "@/services/rolesList";
export default {
    data() {
        return {
            flag: false,
            numbers: 100,
            list: [],
            operator: {
                oname: "",
                phone: "",
                roid: "",
                roname: "",
                oid: ""
            },
            labelCol: {
                style: { width: "150px", float: "left", position: "relative" },
            },
            wrapperCol: {
                span: 14,
                style: { width: "300px", position: "relative", float: "left" },
            }
        };
    },
    methods: {
        update() {
            this.operator.roid = this.list[this.operator.roname].roid
            update(this.operator).then(res => {
                this.$message.success(
                    res.data.msg,
                    10,
                );
            });
            this.$router.replace({
                path: '/operator/operatorList',
            });

        },
    },
    mounted:
        function () {
            this.operator.oid = this.$route.query.oid
            queryById(this.operator.oid).then((res) => {
                this.operator.oname = res.data.oname;
                this.operator.phone = res.data.phone;
                this.operator.roname = res.data.roname;
                this.operator.oid = res.data.oid;
            });
            queryList().then(res => {
                this.list = res.data
            })
        },


};

</script>
<style>
.a {
    left: 30%;
}
</style>

