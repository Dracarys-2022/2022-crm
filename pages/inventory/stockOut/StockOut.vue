<template>
  <div>
    <a-card :bordered="false">
      <div style="display: flex; flex-wrap: wrap">
        <head-info
          title="待处理出库订单"
          :content="this.pagination.total"
          :bordered="true"
        />
        <!-- <head-info title="本周任务平均处理时间" content="32分钟" :bordered="true"/> -->
        <head-info title="本周已出库订单" content="4个" />
      </div>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="">
      <div slot="extra">
        <a-radio-group>
          <a-radio-button @click="queryall">待出库</a-radio-button>
          <a-radio-button @click="queryOut">已出库</a-radio-button>
          <a-radio-button @click="querylack">库存不足</a-radio-button>
        </a-radio-group>
        <a-input-search style="margin-left: 16px; width: 272px" />
      </div>
      <a-table
        size="large"
        :pagination="pagination"
        @change="handleTableChange"
        :columns="columns"
        :dataSource="list"
      >
        <span slot="numbers" slot-scope="text, record">
          <span>{{ record.numbers }}/{{ record.units }}</span>
        </span>
        <span slot="action" slot-scope="text, record">
          <a v-if="record.type!=2 " @click="gosub(record.oid)">出库</a>
          <a-divider v-if="record.type==1  || record.type==3" type="vertical"/>
          <a v-if="record.type==1  || record.type==3" @click="lack(record.oid)">库存不足</a>
          <span v-if="record.type==2">已完成</span>
        </span>
      </a-table>
    </a-card>
  </div>
</template>

<script>
import HeadInfo from "../../../components/tool/HeadInfo.vue";
import { query, sub, lack, queryOut, queryLack } from "@/services/stockIn";
import { message } from "ant-design-vue";

export default {
  name: "StandardList",
  components: { HeadInfo },
  data() {
    return {
      list: [],
      pagination: {
        showSizeChanger: true,
        showQuickJumper: true,
        pageSize: 5,
        total: 0,
        current: 1,
      },
      columns: [
        {
          title: "订单编号",
          dataIndex: "oid",
          key: "oid",
        },
        {
          title: "产品",
          dataIndex: "pname",
          key: "pname",
        },
        {
          title: "订单数量",
          dataIndex: "numbers",
          key: "numbers",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "numbers",
          },
        },
        {
          title: "业务员",
          dataIndex: "opername",
          key: "opername",
        },
        {
          title: "业务员电话",
          dataIndex: "phone",
          key: "phone",
        },
        {
          title: "交货日期",
          dataIndex: "deadline",
          key: "deadline",
        },
        {
          title: "操作",
          key: "action",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "action",
          },
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
    handleTableChange(pagination) {
      this.pagination.current = pagination.current;
      this.pagination.pageSize = pagination.pageSize;
      query(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    querylist: function (res) {
      this.list = res.data.data.records;
      this.pagination.total = res.data.total;
      //message.success(res.data.msg);
    },
    queryOut() {
      queryOut(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    querylack() {
      queryLack(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    queryall() {
      query(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    gosub: function (oid) {
      sub(oid).then((doc) => {
        if (doc.data.code == "0001") {
          this.$closePage(this.$route, "/inventory/resultout");
        } else {
          message.warning(doc.data.msg);
        }
      });
    },
    lack(oid) {
      //this.$closePage(this.$route, "/inventory/resultout")
      lack(oid).then((res) => {
        query(this.pagination.current, this.pagination.pageSize).then((res) =>
          this.querylist(res)
        );
        message.info(res.data.msg);
      });
      //message.info("已通知业务员");
    },
  },
  created() {
    query(this.pagination.current, this.pagination.pageSize).then((res) =>
      this.querylist(res)
    );
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
    width: 125px;
  }
  .time {
    width: 150px;
  }
}
</style>
