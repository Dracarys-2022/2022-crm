<template>
  <div>
    <a-card :bordered="false">
      <div style="display: flex; flex-wrap: wrap">
        <head-info title="所有拜访记录" :content="allVisit" :bordered="true" />
        <!-- <head-info title="本周任务平均处理时间" content="32分钟" :bordered="true"/> -->
        <!-- <head-info title="本周已出库订单" content="24个" /> -->
      </div>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="">
      <!-- <div slot="extra">
        <a-radio-group>
          <a-radio-button>全部</a-radio-button>
          <a-radio-button>进行中</a-radio-button>
          <a-radio-button>等待中</a-radio-button>
        </a-radio-group>
        <a-input-search style="margin-left: 16px; width: 272px" />
      </div> -->
      <!-- <a-button type="dashed" style="width: 100%" icon="plus">添加</a-button> -->
      <a-list
        size="large"
        :pagination="{
          showSizeChanger: true,
          showQuickJumper: true,
          pageSize: 5,
          total: allVisit,
        }"
      >
        <a-list-item :key="i" v-for="(it, i) in list">
          <!-- <a-list-item-meta description="那是一种内在的东西， 他们到达不了，也无法触及的" > -->
          <!-- <a-avatar slot="avatar" size="large" shape="square" src="https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"/> -->
          <!-- <a slot="title">AliPay</a>
          </a-list-item-meta> -->
          <!-- <div slot="actions">
            <a>出库</a>
          </div>
          <div slot="actions">
            <a-dropdown>
              <a>库存不足</a>
               <a-menu slot="overlay">
                <a-menu-item><a>编辑</a></a-menu-item>
                <a-menu-item><a>删除</a></a-menu-item>
              </a-menu>
              <a>更多<a-icon type="down"/></a> 
            </a-dropdown>
          </div> -->
          <div class="list-content">
            <div class="list-content-item">
              <span>所属公司</span>
              <p>{{ it.company }}</p>
            </div>
            <div class="list-content-item">
              <span>拜访客户</span>
              <p>{{ it.client }}</p>
            </div>
            <div class="list-content-item">
              <span>陪同人员</span>
              <p>{{ it.accompany }}</p>
            </div>
            <div class="list-content-item">
              <span>联系方式</span>
              <p>{{ it.phone }}</p>
            </div>
            <div class="list-content-item">
              <span>拜访主题</span>
              <p>{{ it.theme }}</p>
            </div>
            <div class="list-content-item">
              <span>拜访目的</span>
              <p>{{ it.purpose }}</p>
            </div>
            <div class="list-content-item">
              <span>拜访前准备</span>
              <p>{{ it.ready }}</p>
            </div>
            <div class="list-content-item">
              <span>拜访结果</span>
              <p>{{ it.outcome }}</p>
            </div>
            <div class="list-content-item">
              <span>客户态度</span>
              <p>{{ it.attitude }}</p>
            </div>
            <div class="list-content-item">
              <span>活动经费</span>
              <p>{{ it.fund }}</p>
            </div>
            <div class="list-content-item">
              <span>备注</span>
              <p>{{ it.remark }}</p>
            </div>
            <div class="list-content-item">
              <span>拜访时间</span>
              <p class="time">{{it.createtime.toLocaleString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')}}</p>
            </div>
            <div class="list-content-item">
              <span>操作</span>
              <p><button @click="deleteVisit(it.vid)">删除</button></p>
            </div>
            <!-- <div class="list-content-item">
              <a-progress :percent="80" style="width: 180px" />
            </div> -->
          </div>
        </a-list-item>
      </a-list>
    </a-card>
  </div>
</template>

<script>
// import HeadInfo from "../../../components/tool/HeadInfo.vue";
import { listvisit,deletevisit } from "@/services/visit";
export default {
  name: "StandardList",
//   components: { HeadInfo },
  data() {
    return {
        allVisit:"",
      list: [
        {
            vid:"",
        company: "",
        client:"",
        accompany: "",
        theme: "",
        phone: "",
        purpose: "",
        ready: "",
        outcome: "",
        attitude: "",
        fund: "",
        remark: "",
        createtime:''
        },
      ],
    };
  },
  watch: {
    // $route(to, from) {
    //   console.log("home watch to", to);
    //   console.log("home watch from", from);
    // },
  },
  methods: {
    querylist(res){
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
      this.list=res.data.data;
      this.allVisit=res.data.total
    },prolist(){
        listvisit(1,5).then(res=>this.querylist(res));
    },deleteVisit(vid){
        deletevisit(vid).then(res=>this.afterdelete(res))
    },afterdelete(res){
      if(res.data.msg!=""){
                this.$message.success(
            // '查询成功',
                res.data.msg,
            10,
        )
            }else{
                this.$message.success(
                '删除成功！'
        );
            }
        this.list=res.data.data;
      this.allVisit=res.data.alltotal
    }
  },
  created() {
    this.prolist();
    // query().then(res => this.querylist(res));
    // console.log("page about 创建");
    // console.log("page about获取传入值Query");
    // console.log("page about获取参数值params");
  },
};
</script>

<style lang="less" scoped>
.list-content-item {
  color: @text-color-second;
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  margin-left: 40px;
  span {
    line-height: 20px;
    text-align: center;
  }
  p {
    margin: 4px 0 0px;
    line-height: 20px;
    width: 80px;
    color: black;
  }
  .time{
    width: 150px;
  }
}
</style>
