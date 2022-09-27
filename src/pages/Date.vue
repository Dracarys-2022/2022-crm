<template>
    <div>
 
        <el-alert title="点击日历中深色颜色部分即可安排日程" type="success" center class="abcd" :closable="false">
        </el-alert>
        <el-calendar v-model="value">
            <template slot="dateCell" slot-scope="{date, data}">
                <div @click="chooseDay(data.day)" class="a">

                    <p :class="data.isSelected ? 'is-selected' : ''">
                        {{ data.day.split('-').slice(1).slice(1).join('-') }} 
                    </p>
                    <ul class="abc" v-if="comcba(data.day)">
                        <li v-for="item in activeList" :key="item.id" class="li">
                            <el-tag v-if="data.day == item.time" class="abcde">{{ item.name }}</el-tag>
                        </li>
                    </ul>
                </div>
            </template>
        </el-calendar>
        <el-drawer title="我是标题" :visible.sync="drawer" :with-header="false">
            <span>
                <p class="p">{{ this.day }}</p>
                <el-card class="box-card" shadow="hover">
                    <div slot="header" class="clearfix">
                        <span v-if="this.open == 1">{{ '日程' }}</span>
                        <span v-if="this.open == 2" class="span2">{{ '此日无日程' }}</span>
                    </div>
                    <div v-if="this.open == 1" class="div1">
                        <ul v-for="item in this.activeForm" :key="item.id" class="li123">
                            <el-button type="primary" icon="el-icon-edit" size="mini" style="float: right;"
                                @click="updateactive(item.id)"></el-button>
                            <el-button type="danger" icon="el-icon-delete" size="mini" style="float: right;"
                                @click="delete1(item.id)"></el-button>
                            <li class="li1">编号 :{{ item.id }}</li>
                            <li class="li1">活动名称 :{{ item.name }}</li>
                            <li class="li1">活动开始时间 :{{ item.starttime }}</li>
                            <li class="li1">活动结束时间 :{{ item.endtime }}</li>
                            <li class="li1">活动参与人员 :{{ item.people }}</li>
                        </ul>

                    </div>
                </el-card>
                <el-button type="primary" class="button" @click="ondlg">添加此日的日程</el-button>
            </span>
        </el-drawer>
        <el-dialog title="提示" :visible.sync="dialogVisible" width="50%" @close="addDialogClosed">
            <span>
                <el-form :model="addForm" label-width="80px" ref="addFromRef" :rules="addFormRules">
                    <el-form-item label="活动名称" prop="name">
                        <el-input v-model="addForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="起始时间">
                        <el-time-select placeholder="起始时间" v-model="addForm.starttime" :picker-options="{
                            start: '08:30',
                            step: '00:15',
                            end: '18:30'
                        }">
                        </el-time-select>
                    </el-form-item>
                    <el-form-item label="结束时间">
                        <el-time-select placeholder="结束时间" v-model="addForm.endtime" :picker-options="{
                            start: '08:30',
                            step: '00:15',
                            end: '18:30',
                            minTime: addForm.startTime
                        }">
                        </el-time-select>
                    </el-form-item>
                    <el-form-item label="参加人员" prop="people">
                        <el-input v-model="addForm.people"></el-input>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogVisible1" width="50%">
            <span>
                <el-form :model="addForm1" label-width="80px" :rules="addFormRules1" ref="addFromRef1">
                    <el-form-item label="活动名称" prop="name">
                        <el-input v-model="addForm1.name"></el-input>
                    </el-form-item>
                    <el-form-item label="起始时间">
                        <el-time-select placeholder="起始时间" v-model="addForm1.starttime" :picker-options="{
                            start: '08:30',
                            step: '00:15',
                            end: '18:30'
                        }">
                        </el-time-select>
                    </el-form-item>
                    <el-form-item label="结束时间">
                        <el-time-select placeholder="结束时间" v-model="addForm1.endtime" :picker-options="{
                            start: '08:30',
                            step: '00:15',
                            end: '18:30',
                            minTime: addForm1.startTime
                        }">
                        </el-time-select>
                    </el-form-item>
                    <el-form-item label="参加人员" prop="people">
                        <el-input v-model="addForm1.people"></el-input>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible1 = false">取 消</el-button>
                <el-button type="primary" @click="update">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import axios from 'axios';
import {MessageBox} from 'element-ui'
export default {
    data() {
        return {
            value: new Date(),
            drawer: false,
            day: '',
            dialogVisible: false,
            dialogVisible1: false,
            activeForm: [],
            activeList: [],
            activeListTime: [],
            addForm: {
                name: '',
                people: '',
                starttime: '',
                endtime: '',
                time: '',
            },
            addForm1: {
                name: '',
                people: '',
                starttime: '',
                endtime: '',
                time: '',
            },
            open: '',
            addFormRules: {
                name: [{ required: true, message: '请输入活动名称', trigger: 'blur' },],
                people: [{ required: true, message: '请输入活动人员', trigger: 'blur' }]
            }, addFormRules1: {
                name: [{ required: true, message: '请输入活动名称', trigger: 'blur' },],
                people: [{ required: true, message: '请输入活动人员', trigger: 'blur' }]
            }

        }
    },
    created() {
        this.getactiveList();

    }, mounted() {

    },
    methods: {
        async chooseDay(day) {
            this.day = day

            const { data: res } = await axios.put(`active/select/` + day)

            if (res.data.length == 0) {
                this.open = 2;
            } else {
                this.open = 1;
                this.activeForm = res.data
            }
            this.drawer = true;
        }, ondlg() {
            this.dialogVisible = true;
        }, save() {
            this.$refs.addFromRef.validate(async valid => {
                if (!valid) return
                const day = this.day
                this.addForm.time = day
                const { data: res } = await axios.post(`active/activesave`, this.addForm)
                this.dialogVisible = false;
                this.chooseDay(day)
                this.$message.success(res)
                this.getactiveList();

            })

        }, addDialogClosed() {
            this.$refs.addFromRef.resetFields();
        }, async updateactive(id) {
            this.dialogVisible1 = true
            console.log(id);
            const { data: res } = await axios.put(`active/supdate/` + id)
            this.addForm1 = res.data

        }, update() {
            this.$refs.addFromRef1.validate(async valid => {
                if (!valid) return
                const day = this.day
                this.addForm1.time = day
                const { data: res } = await axios.post(`active/update`, this.addForm1)
                this.dialogVisible1 = false;
                this.$message.success(res.data)
                this.chooseDay(day)
                this.getactiveList();
            })
        }, async delete1(id) {
            const confirmResult = await MessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err=>err)
            if (confirmResult !== 'confirm') {
                return this.$message.info('已取消删除')
            }
            await axios.delete('active/delete/' + id)
            this.$message.success('删除成功')
            this.chooseDay(this.day)
            this.getactiveList();
        }, async getactiveList() {
            const { data: res } = await axios.post(`active`)
            this.activeList = res.data;
            res.data.forEach(item => {
                this.activeListTime.push(item.time)
                console.log(item.time);

            })
            console.log("++++" + this.activeListTime);
            this.activeListTime.join(",")



        },
        comcba(day) {
            if (this.activeListTime.includes(day)) {
                return true
            } else {
                return false
            }
        },

    }, computed: {

    }
}
</script>

<style lang="less" scoped>
.button {
    position: fixed;
    bottom: 0;
    margin-left: 380px;

}

.span2 {
    font-size: large;

}

.a {
    position: relative;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-between;
    padding: 1vh;
    box-sizing: border-box;
    background-color: blanchedalmond;


}

.p {
    font-size: large;
    color: cornflowerblue;
    margin-left: 220px;
}

.li {
    list-style: none;

}

.clearfix {
    text-align: center;
    font-size: large;
}

.li1 {
    padding-top: 15px;
}

.li123 {
    list-style: none;
    border-style: inset;
    border-radius: 10px;
    border-color: beige;
}

.box-card {
    margin-top: 20px;
    background-color: antiquewhite;
}

.abc {
    width: 100%;
    height: 70%;
    overflow: hidden;
    overflow-y: scroll;
    text-align: left;
    margin-top: 23px;
}

.abcde {
    border-radius: 10px;
}

.abcd {
    margin-top: 20px;
}
</style>