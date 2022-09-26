<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>登录日志</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="根据用户搜索" name="first"></el-tab-pane>
    <el-tab-pane label="根据日期搜索" name="second"></el-tab-pane>
    <el-tab-pane label="根据状态搜索" name="third"></el-tab-pane>
  </el-tabs>
            <el-row >     
                <el-col :span="5">
                    <el-input v-model="queryInfo.name" :placeholder="abc" clearable></el-input>
                </el-col>
                <el-col :span="1">
                    <el-button icon="el-icon-search" circle @click="getuserlog"></el-button>
                </el-col>
            </el-row>
            <el-table :data="form" border>
                <el-table-column label="#" type="index"></el-table-column>
                <el-table-column label="用户行为" type="expand" width="120px">
                    <template slot-scope="scope">
                        <el-row :gutter="20">
                            <el-col :span="6" v-for="(item,index) in scope.row.behavior" :key="index">
                                <el-tag>{{index+1}}::{{item}}</el-tag>
                            </el-col>

                        </el-row>
                    </template>
                </el-table-column>
                <el-table-column label="用户" prop="user"></el-table-column>
                <el-table-column label="用户状态" prop="status" width="120px">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.status == 1">合法用户</el-tag>
                        <el-tag type="danger" v-if="scope.row.status == 0">不合法用户</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="登录时间" prop="createTime" width="200px">
                </el-table-column>
                <el-table-column label="登录系统" prop="useragent" width="120px">
                </el-table-column>
            </el-table>
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="queryInfo.page" :page-sizes="[2, 10, 15, 20]" :page-size="queryInfo.pagesize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            form: [],
            queryInfo: {
                pagesize: 2,
                page: 1,
                name: ''

            },
            activeName: 'first',
            total: 0,
            value:'',

        }
    },
    created() {
        this.getuserlog();
    }, methods: {
        async getuserlog() {
            const { data: res } = await axios.get(`user-log/select`, { params: this.queryInfo })

            this.form = res.records
            console.log(this.queryInfo);
            this.total = res.total
            this.form.forEach(item => {
                item.behavior = item.behavior.split(' ')
            });
            console.log(this.form);


        }, handleSizeChange(newSize) {
            this.queryInfo.pagesize = newSize;
            this.getuserlog()
        }, handleCurrentChange(newPage) {
            this.queryInfo.page = newPage;
            this.getuserlog()
        },handleClick() {
        console.log(this.activeName);
       this.value=''
      }
    },
    computed:{
       abc(){
        if(this.activeName=='first'){
            return '输入用户名'
        }
        if(this.activeName=='second'){
            return '日期格式xxxx-MM-dd例如2022-09-xx'
        }else{
            return '输入合法用户或不合法用户'
        }
       }
        }
    
}


</script>

<style lang="less" scoped>
.el-header {
    background-color: antiquewhite;
    display: flex;
    justify-content: space-between;
    padding-left: 0%;
    align-items: center;
    color: black;
    font-size: 20px;

    >div {
        display: flex;
        align-items: center;

        span {
            margin-left: 15px;
        }
    }
}

.el-menu {
    background-color: aquamarine;
}

.el-card {
    margin-top: 20px;
}

.el-table {
    margin-top: 20px;
}
</style>