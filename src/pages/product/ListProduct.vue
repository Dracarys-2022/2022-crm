<template>
  <div>
    <a-card style="margin-top: 24px" :bordered="false" title="">
      <div slot="extra">
        <a-radio-group>
          <a-radio-button @click="on">已上架</a-radio-button>
          <a-radio-button @click="out">已下架</a-radio-button>
        </a-radio-group>
        <a-input-search style="margin-left: 16px; width: 272px" />
      </div>
      <a-table
        size="large"
        :pagination="pagination"
        @change="handleTableChange"
        :columns="columns"
        :dataSource="this.list"
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
        <span slot="type" slot-scope="text, record">
          <span v-if="record.type == 1">产品</span>
          <span v-if="record.type == 2">服务</span>
        </span>
        <span slot="price" slot-scope="text, record">
          <span>{{ record.price }}/元</span>
        </span>
        <span slot="marketprice" slot-scope="text, record">
          <span>{{ record.marketprice }}/元</span>
        </span>
        <span slot="shelflife" slot-scope="text, record">
          <span>{{ record.shelflife }}天</span>
        </span>
        <span slot="specification" slot-scope="text, record">
          <span>{{ record.specification }}{{ record.units }}/件</span>
        </span>
        <span slot="action" slot-scope="text, record">
          <div>
            <div>
              <a @click="update(record.pid)">修改</a>
              <a-divider type="vertical" />
              <a @click="changestatus(record.pid)"
                ><span v-if="record.status == 0">上架</span
                ><span v-if="record.status == 1">下架</span></a
              >
            </div>
          </div>
        </span>
      </a-table>
    </a-card>
  </div>
</template>

<script>
import { listPro, listOut, changeStatus,queryByPid } from "@/services/product";
export default {
  data() {
    return {
      searchText: "",
      searchInput: null,
      searchedColumn: "",
      iskey: false,
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
          title: "产品编号",
          dataIndex: "pid",
          key: "pid",
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
          title: "产品名称",
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
          title: "种类",
          dataIndex: "category",
          key: "category",
        },
        {
          title: "成本价",
          dataIndex: "price",
          key: "price",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "price",
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
          title: "市场价",
          dataIndex: "marketprice",
          key: "marketprice",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "marketprice",
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
          title: "单位",
          dataIndex: "units",
          key: "units",
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
          title: "规格",
          dataIndex: "specification",
          key: "specification",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "specification",
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
          title: "保质期",
          dataIndex: "shelflife",
          key: "shelflife",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "shelflife",
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
          title: "类型",
          dataIndex: "type",
          key: "type",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "type",
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
  methods: {
    handleTableChange(pagination) {
      this.pagination.current = pagination.current;
      this.pagination.pageSize = pagination.pageSize;
      listPro(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    querylist: function (res) {
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
      this.list = res.data.data.records;
      this.pagination.total = res.data.total;
      console.log(res.data.data.records);
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
    on() {
      listPro(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    out() {
      listOut(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    changestatus(pid) {
      changeStatus(pid).then((res) => {
        if (res.data.code == '0001') {
          listPro(this.pagination.current, this.pagination.pageSize).then(
            (res) => this.querylist(res)
          );
        }
      });
    },
    update(pid){
        queryByPid(pid).then((res) => {
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
        this.$router.push({
          path: "/product/updateproduct",
          query: { data: res.data.data },
        });
      });
    }
  },
  created() {
    listPro(this.pagination.current, this.pagination.pageSize).then((res) =>
      this.querylist(res)
    );
  },
  watch: {
    $route(to, from) {
      console.log(to.path);
      console.log(from.path);
      listPro(this.pagination.current, this.pagination.pageSize).then((res) => {
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
        this.list = res.data.data.records;
      });
    },
  },
};
</script>

<style>
.actions {
  position: relative;
  right: 30px;
}

.highlight {
  background-color: rgb(255, 192, 105);
  padding: 0px;
}
</style>