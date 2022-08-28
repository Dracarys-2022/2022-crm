<template>
  <div>
    <a-card style="margin-top: 24px" :bordered="false" title="">
      <div slot="extra">
        <a-input-search v-model="pagination.query" style="margin-left: 16px; width: 272px" />
        <a-radio-button @click="query()">查询</a-radio-button>
      </div>
      <a-table size="large" :pagination="pagination" @change="handleTableChange"
        :rowKey="(record, index) => { return index }" :columns="columns" :dataSource="list">
        <div slot="filterDropdown" slot-scope="{
            setSelectedKeys,
            selectedKeys,
            confirm,
            clearFilters,
            column,
          }" style="padding: 8px">
          <a-input v-ant-ref="(c) => (searchInput = c)" :placeholder="`Search ${column.dataIndex}`"
            :value="selectedKeys[0]" style="width: 188px; margin-bottom: 8px; display: block" @change="
              (e) => setSelectedKeys(e.target.value ? [e.target.value] : [])
            " @pressEnter="
  () => handleSearch(selectedKeys, confirm, column.dataIndex)
" />
          <a-button type="primary" icon="search" size="small" style="width: 90px; margin-right: 8px"
            @click="() => handleSearch(selectedKeys, confirm, column.dataIndex)">
            搜索
          </a-button>
          <a-button size="small" style="width: 90px" @click="() => handleReset(clearFilters)">
            重置
          </a-button>
        </div>
        <a-icon slot="filterIcon" slot-scope="filtered" type="search"
          :style="{ color: filtered ? '#108ee9' : undefined }" />
        <template slot="customRender" slot-scope="text, record, index, column">
          <span v-if="searchText && searchedColumn === column.dataIndex">
            <template v-for="(fragment, i) in text
            .toString()
            .split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))">
              <mark v-if="fragment.toLowerCase() === searchText.toLowerCase()" :key="i" class="highlight">{{ fragment
              }}</mark>
              <template v-else>{{ fragment }}</template>
            </template>
          </span>
          <template v-else>
            {{ text }}
          </template>
        </template>
        <span slot="time" slot-scope="time">
          {{ time | filterTime(time) }}</span>
        <span slot="status" slot-scope="text, record">
          <span v-if='record.status == 0'>流失</span>
          <span v-if='record.status == 1'>正常</span>
        </span>
        <span slot="action" slot-scope="text, record">

          <a v-if='record.status == 1' @click="updatestatus(record.coid, record.cid, 0)">流失</a>
          <a v-if='record.status == 0' @click="updatestatus(record.coid, record.cid, 1)">正常</a>

          <a class="a" @click="skippage(record.coid)">详情</a>

        </span>
      </a-table>
    </a-card>
  </div>
</template>

<script>

import { update } from "@/services/contactUpdate";
import { pageList } from "@/services/contactPage";
import { message } from "ant-design-vue";
import moment from 'moment'
export default {
  name: "StandardList",
  components: {},
  data() {
    return {
      searchText: '',
      searchInput: null,
      searchedColumn: '',
      list: [],
      pagination: {
        showSizeChanger: true,
        showQuickJumper: true,
        pageSize: 10,
        total: 0,
        current: 1,
        query: "",
      },
      contact: {
        coid: "",
        cid: "",
        status: ""
      },
      columns: [
        {
          title: "编号",
          dataIndex: "coid",
          key: "coid",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
            record.coid
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "客户联系人",
          dataIndex: "coname",
          key: "coname",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
            record.coname
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "客户公司名称",
          dataIndex: "cname",
          key: "cname",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
            record.cname
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "地址",
          dataIndex: "address",
          key: "address",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
            record.address
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "联系电话",
          dataIndex: "phone",
          key: "phone",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
            record.phone
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "备注",
          dataIndex: "remark",
          key: "remark",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
            record.remark
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "我方负责人",
          dataIndex: "oname",
          key: "oname",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'customRender',
          },
          onFilter: (value, record) =>
            record.oname
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "录入时间",
          dataIndex: "createtime",
          key: "createtime",
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'time',
          },
          onFilter: (value, record) =>
            record.createtime
              .toString()
              .toLowerCase()
              .includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: visible => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "状态",
          dataIndex: "status",
          key: "status",
          scopedSlots: {
            customRender: 'status',
          },

        },
        {
          title: "操作",
          key: "action",
          scopedSlots: { customRender: "action" },
        },
      ],
    };
  },
  watch: {
    $route() {
      pageList(this.pagination).then((res) => {
        this.list = res.data.list;
      });
    },
  },
  // mounted: function () {
  //   this.list = this.$route.query.list
  // },
  methods: {
    skippage(e) {
      this.$router.replace({
        path: '/contact/contactDetail',
        query: { coid: e }
      });
    },
    //表格改变时触发
    handleTableChange(pagination) {
      this.pagination.current = pagination.current;
      this.pagination.pageSize = pagination.pageSize;
      pageList(this.pagination).then((res) =>
        this.querylist(res)
      );
    },
    querylist: function (res) {
      message.success('查询成功');
      this.list = res.data.list;
      this.pagination.total = res.data.rowcount;
      this.pagination.query = res.data.query;
    },
    query() {
      this.pagination.current = 1
      console.log(this.pagination.query)
      pageList(this.pagination).then(res => {
        this.querylist(res)
      })
    },
    handleSearch(selectedKeys, confirm, dataIndex) {
      confirm();
      this.searchText = selectedKeys[0];
      this.searchedColumn = dataIndex;
    },
    updatestatus(x, y, z) {
      this.contact.coid = x;
      this.contact.cid = y;
      this.contact.status = z;
      update(this.contact).then(res => {
        message.success(
          res.data.msg,
          10,
        );
        this.list = null
         pageList(this.pagination).then((res) => {
        this.list = res.data.list;
      });

      })
    },
    handleReset(clearFilters) {
      clearFilters();
      this.searchText = '';
    },
  },
  created() {
    pageList(this.pagination).then((res) =>
      this.querylist(res)
    );
  },
  filters: {
    filterTime(time) {
      return moment(time).format('YYYY-MM-DD HH:mm:ss')
    }
  }
};
</script>

<style lang="less" scoped>
.actions {
  position: relative;
  right: 30px;
}

.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}

.a {
  margin-left: 10px;
}

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
    width: 85px;
  }

  .time {
    width: 150px;
  }
}
</style>
