<template>
  <div>
    <a-card :bordered="false">
      <div style="display: flex; flex-wrap: wrap">
        <head-info title="累计投诉" :content="this.num" :bordered="true" />
        <head-info title="已处理的投诉" :content="this.overtotal" />
      </div>
    </a-card>
    <a-card style="margin-top: 24px" :bordered="false" title="">
      <div slot="extra">
        <a-radio-group>
          <a-radio-button @click="listNull">未处理</a-radio-button>
          <a-radio-button @click="listAll">已处理</a-radio-button>
        </a-radio-group>
        <!-- <a-input-search style="margin-left: 16px; width: 272px" /> -->
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
          <a v-if="record.outcome == null" @click="addOutcome(record.peid)">处理</a>
          <a-divider v-if="record.outcome == null" type="vertical" /> 
          <a v-if="record.outcome == null" @click="deletePe(record.peid)">撤销</a>
          <span v-if="record.outcome != null">处理完成</span>
          <!-- <a-divider type="vertical" />
          <a>Delete</a> -->
        </span>
        <span slot="outcome" slot-scope="text, record">
          <span v-if="record.outcome == null">暂未处理</span>
          <span v-if="record.outcome != null">{{ record.outcome }}</span>
        </span>
         <span slot="oname" slot-scope="text, record">
          <span >{{record.oname}}</span>
        </span>
      </a-table>
    </a-card>
  </div>
</template>

<script>
import HeadInfo from "../../../components/tool/HeadInfo.vue";
import { list, listNull ,deletePetion} from "@/services/petition";
//import { message } from "ant-design-vue";

export default {
  name: "StandardList",
  components: { HeadInfo },
  data() {
    return {
      searchText: "",
      searchInput: null,
      searchedColumn: "",
      num: "",
      overtotal: "",
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
          title: "投诉内容",
          dataIndex: "content",
          key: "content",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.content
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
          title: "记录人",
          dataIndex: "oname",
          key: "oname",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "oname",
          },
          onFilter: (value, record) =>
            record.oname
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
          title: "投诉时间",
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
          title: "处理人",
          dataIndex: "exname",
          key: "exname",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "customRender",
          },
          onFilter: (value, record) =>
            record.exname
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
          title: "处理结果",
          dataIndex: "outcome",
          key: "outcome",
          scopedSlots: {
            filterDropdown: "filterDropdown",
            filterIcon: "filterIcon",
            customRender: "outcome",
          },
          onFilter: (value, record) =>
            record.outcome
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
      listNull(this.pagination.current, this.pagination.pageSize).then((res) => {
        this.querylist(res)
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
      //message.success(res.data.msg);
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
      this.num = res.data.num;
      this.overtotal = res.data.overtotal;
    },
    deletePe(peid){
      deletePetion(peid,this.pagination.current, this.pagination.pageSize).then((res)=>{
         this.querylist(res)
      })
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
    addOutcome: function (peid) {
      this.$router.push({
        path: "/petition/addoutcome",
        query: { peid: peid },
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
    var aaa=localStorage.getItem('permissions');
    var aa=aaa.replace("\"","").replace("\"","");
    if(localStorage.getItem('access-admin')==""||localStorage.getItem('access-admin')==null){
      this.$message.success("请重新登录！")
      this.$router.push({
          path: "/login"
        });
        return;
    }
   if (!aa.split(",").includes("39")) {
      this.$message.success("您没有权限")
      this.$router.push({
        path: "/403"
        });
        return;
   }
    listNull(this.pagination.current, this.pagination.pageSize).then((res) =>
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
    width: 125px;
  }
  .time {
    width: 150px;
  }
  .content {
    width: 200px;
  }
  .outcome {
    width: 200px;
  }
}
</style>
