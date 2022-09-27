<template>
  <div>
    <div v-show="!modalShow">
      <a-card :bordered="false">
      <div style="display: flex; flex-wrap: wrap">
        <head-info title="累计投诉" content="66个" :bordered="true" />
        <head-info title="已处理的投诉" content="24个" />
      </div>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="">
      <div slot="extra">
        <a-radio-group>
          <a-radio-button>全部</a-radio-button>
          <a-radio-button>进行中</a-radio-button>
          <a-radio-button>等待中</a-radio-button>
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
        <div
          slot="filterDropdown"
          slot-scope="{
            setSelectedKeys,
            selectedKeys,
            confirm,
            clearFilters,
            column,
          }"
          style="padding: 8px"
        >
          <a-input
            v-ant-ref="(c) => (searchInput = c)"
            :placeholder="`Search ${column.dataIndex}`"
            :value="selectedKeys[0]"
            style="width: 188px; margin-bottom: 8px; display: block"
            @change="
              (e) => setSelectedKeys(e.target.value ? [e.target.value] : [])
            "
            @pressEnter="
              () => handleSearch(selectedKeys, confirm, column.dataIndex)
            "
          />
          <a-button
            type="primary"
            icon="search"
            size="small"
            style="width: 90px; margin-right: 8px"
            @click="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
          >
            搜索
          </a-button>
          <a-button
            size="small"
            style="width: 90px"
            @click="() => handleReset(clearFilters)"
          >
            重置
          </a-button>
        </div>
        <a-icon
          slot="filterIcon"
          slot-scope="filtered"
          type="search"
          :style="{ color: filtered ? '#108ee9' : undefined }"
        />
        <template slot="customRender" slot-scope="text, record, index, column">
          <span v-if="searchText && searchedColumn === column.dataIndex">
            <template
              v-for="(fragment, i) in text
                .toString()
                .split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))"
            >
              <mark
                v-if="fragment.toLowerCase() === searchText.toLowerCase()"
                :key="i"
                class="highlight"
                >{{ fragment }}</mark
              >
              <template v-else>{{ fragment }}</template>
            </template>
          </span>
          <template v-else>
            {{ text }}
          </template>
        </template>
        <span slot="action" slot-scope="text, record">
          <div v-if="record.state==1">
              <div v-if="record.type==0">
                  <a @click="requestout(record.oid)">请求出库</a>
              </div>
              <div v-else-if="record.type==1">
                  <a @click="out(record.oid)">出库</a>
              </div>
              <div v-else-if="record.type==3">
                  库存不足
              </div>
              <div v-else-if="record.type==2">
                  <a @click="returnstate(record.oid)">退换</a>
                  <a-divider type="vertical" />
                  <a @click="complate(record.oid)">完成</a>
              </div>
              <div v-else-if="record.type==4">
                  已退换
              </div>
          </div>
          
        </span>
      </a-table>
    </a-card>
    </div>
    <Modal v-show="modalShow" ref="source" title="商品退换">
      <div>
        <a-form-model-item  label="商品状况" >
          <a-select v-model="productstatue" placeholder="选择商品状况" style="width:200px">
            <a-select-option value="0">正常</a-select-option>
            <a-select-option value="1">异常</a-select-option>
          </a-select>
        </a-form-model-item>
      <a-form-model-item
          class="a"
          name="date-time-picker"
          label="生产日期"
          prop="procreatetime"
        >
          <a-date-picker
            v-model="procreatetime"
            show-time
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
          />
        </a-form-model-item>

        <a-form-model-item
          name="date-picker"
          class="a"
          label="截止时间"
          prop="prodeadline"
        >
          <a-date-picker v-model="prodeadline" 
          show-time
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
          />
        </a-form-model-item>
        <a-form-model-item class="a">
          <a-button @click="returnstatesure()">确定退换</a-button>
        </a-form-model-item>
      </div>
    </Modal>
  </div>
</template>

<script>
import { listorders,requestout,out,returnstate,complate } from "@/services/orders";
// import { message } from "ant-design-vue";
export default {
  data() {
    return {
      searchText: "",
      searchInput: null,
      searchedColumn: "",
      list: [],
      modalShow:false,
      productstatue:"",
      procreatetime:"",
      prodeadline:"",
      proid:"",
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
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.oid.toString().toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "产品",
          dataIndex: "pname",
          key: "pname",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.pid.toString().toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "成交数量",
          dataIndex: "numbers",
          key: "numbers",
        },
        {
          title: "成交金额",
          dataIndex: "price",
          key: "price",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.price.toString().toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "联系人",
          dataIndex: "coname",
          key: "coname",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.coid.toString().toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "客户",
          dataIndex: "cname",
          key: "cname",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.cid.toString().toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "下单日期",
          dataIndex: "createtime",
          key: "createtime",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.createtime
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "截止日期",
          dataIndex: "deadline",
          key: "deadline",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.deadline
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "销售员",
          dataIndex: "operid",
          key: "operid",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.operid
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "订单状态",
          dataIndex: "tyname",
          key: "tyname",
        },
        {
          title: "订单类型",
          dataIndex: "staname",
          key: "staname",
        },
        {
          title: "操作",
          key: "action",
          scopedSlots: { customRender: "action" },
        },
      ],
    };
  },
  methods: {
    handleTableChange(pagination) {
      this.pagination.current = pagination.current;
      this.pagination.pageSize = pagination.pageSize;
      listorders(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    querylist: function (res) {
      // message.success(res.data.msg);
      this.list = res.data.data.records;
      this.pagination.total = res.data.total;
    },
    handleSearch(selectedKeys, confirm, dataIndex) {
      confirm();
      this.searchText = selectedKeys[0];
      this.searchedColumn = dataIndex;
    },
    handleReset(clearFilters) {
      clearFilters();
      this.searchText = "";
    },
    requestout(oid){
      requestout(oid,1,5).then(res=>{
        alert("已请求出库！");
        this.list = res.data.data.records;
        this.pagination.total = res.data.total;
      })
    },out(oid){
      out(oid,1,5).then(res=>{
        alert("已出库！");
        this.list = res.data.data.records;
        this.pagination.total = res.data.total;
      })
    },returnstate(oid){
      this.proid=oid;
      this.modalShow=true;
    },returnstatesure(){
      this.modalShow=false;
      returnstate(this.productstatue,this.procreatetime,this.prodeadline,this.proid,1,5).then(res=>{
        // this.$router.push({
        //   path: "/inventory/updatestock",
        //   query: { data: res.data.data },
        // });
        alert("已退换！");
        this.$ref.source.resetFields();
        this.list = res.data.data.records;
        this.pagination.total = res.data.total;
      })
    },
    complate(oid){
      complate(oid,1,5).then(res=>{
        alert("已完成订单");
        this.list = res.data.data.records;
        this.pagination.total = res.data.total;
      })
    }
  },
  created() {
    listorders(this.pagination.current, this.pagination.pageSize).then((res) =>
      this.querylist(res)
    );
  },
};
</script>

<style>
.actions {
  position: relative;
  right: 30px;
}
.a {
  left: 25%;
}
.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}
</style>