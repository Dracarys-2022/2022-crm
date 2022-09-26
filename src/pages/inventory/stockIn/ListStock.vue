<template>
  <div>
    <a-card style="margin-top: 24px" :bordered="false" title="">
      <div slot="extra">
        <a-radio-group>
          <a-radio-button @click="listAll">有库存</a-radio-button>
          <a-radio-button @click="listNull">无库存</a-radio-button>
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
          <a v-if="record.exist !=0" @click="update(record.id)">修改</a>
          <span  v-if="record.exist ==0">已完成</span>
        </span>
        <span slot="location" slot-scope="text, record">
          <!-- <span v-if='record.location =="A"'>流失</span>
          <span v-if='record.location =="B"'>正常</span> -->
          <span>{{ record.location }}仓库</span>
        </span>
        <span slot="price" slot-scope="text, record">
          <span>{{ record.price }}/元</span>
        </span>
        <span slot="numbers" slot-scope="text, record">
          <span>{{ record.numbers }}/{{ record.units }}</span>
        </span>
        <span slot="exist" slot-scope="text, record">
          <span>{{ record.exist }}/{{ record.units }}</span>
        </span>
      </a-table>
    </a-card>
  </div>
</template>

<script>
import { list, queryById, listNull } from "@/services/stockIn";
import { message } from "ant-design-vue";
export default {
  name: "StandardList",
  components: {},
  data() {
    return {
      searchText: "",
      searchInput: null,
      searchedColumn: "",
      list: [],
      pagination: {
        showSizeChanger: true,
        showQuickJumper: true,
        pageSize: 10,
        total: 0,
        current: 1,
      },
      columns: [
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
          title: "入库数量",
          dataIndex: "numbers",
          key: "numbers",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "numbers",
          },
          onFilter: (value, record) =>
            record.numbers
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
          title: "现存数量",
          dataIndex: "exist",
          key: "exist",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "exist",
          },
          onFilter: (value, record) =>
            record.exist.toString().toLowerCase().includes(value.toLowerCase()),
          onFilterDropdownVisibleChange: (visible) => {
            if (visible) {
              setTimeout(() => {
                this.searchInput.focus();
              }, 0);
            }
          },
        },
        {
          title: "产品进价",
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
          title: "仓库位置",
          dataIndex: "location",
          key: "location",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "location",
          },
          onFilter: (value, record) =>
            record.location
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
          title: "生产日期",
          dataIndex: "producetime",
          key: "producetime",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.producetime
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
          title: "过期日期",
          dataIndex: "endtime",
          key: "endtime",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.endtime
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
          title: "入库时间",
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
          title: "操作",
          key: "action",
          scopedSlots: { customRender: "action" },
        },
      ],
    };
  },
  watch: {
    $route(to, from) {
      console.log(to.path);
      console.log(from.path);
      list(this.pagination.current, this.pagination.pageSize).then((res) => {
        this.list = res.data.data.records;
      });
    },
  },
  methods: {
    //表格改变时触发
    handleTableChange(pagination) {
      this.pagination.current = pagination.current;
      this.pagination.pageSize = pagination.pageSize;
      list(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    querylist: function (res) {
      message.success(res.data.msg);
      this.list = res.data.data.records;
      this.pagination.total = res.data.total;
    },
    listAll() {
      list(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    listNull() {
      listNull(this.pagination.current, this.pagination.pageSize).then((res) =>
        this.querylist(res)
      );
    },
    update(id) {
      queryById(id).then((res) => {
        this.$router.push({
          path: "/inventory/updatestock",
          query: { data: res.data.data },
        });
      });
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
  },
  created() {
    list(this.pagination.current, this.pagination.pageSize).then((res) =>
      this.querylist(res)
    );
  },
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
