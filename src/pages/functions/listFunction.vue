<template>
    <div>
      <div v-show="!modalShow">
        <a-card :bordered="false">
      </a-card>
      <a-card style="margin-top: 24px" :bordered="false" title="">
        <div slot="extra">
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
        </a-table>
      </a-card>
      </div>
    </div>
  </template>
  
  <script>
import {listFunctions} from '@/services/function'
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
            title: "功能编号",
            dataIndex: "fid",
            key: "fid",
            scopedSlots: {
              filterDropdown: "filterDropdown",
              filterIcon: "filterIcon",
              customRender: "customRender",
            },
            onFilter: (value, record) =>
              record.fid.toString().toLowerCase().includes(value.toLowerCase()),
            onFilterDropdownVisibleChange: (visible) => {
              if (visible) {
                setTimeout(() => {
                  this.searchInput.focus();
                }, 0);
              }
            },
          },
          {
            title: "功能名称",
            dataIndex: "fname",
            key: "fname",
            scopedSlots: {
              filterDropdown: "filterDropdown",
              filterIcon: "filterIcon",
              customRender: "customRender",
            },
            onFilter: (value, record) =>
              record.fname.toString().toLowerCase().includes(value.toLowerCase()),
            onFilterDropdownVisibleChange: (visible) => {
              if (visible) {
                setTimeout(() => {
                  this.searchInput.focus();
                }, 0);
              }
            },
          },
          {
            title: "权限级别",
            dataIndex: "functionName",
            key: "functionName",
            scopedSlots: {
              filterDropdown: "filterDropdown",
              filterIcon: "filterIcon",
              customRender: "customRender",
            },
            onFilter: (value, record) =>
              record.functionName.toString().toLowerCase().includes(value.toLowerCase()),
            onFilterDropdownVisibleChange: (visible) => {
              if (visible) {
                setTimeout(() => {
                  this.searchInput.focus();
                }, 0);
              }
            },
          },
        ],
      };
    },
    methods: {
      handleTableChange(pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
        listFunctions(this.pagination.current, this.pagination.pageSize).then((res) =>
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
      }
    },
    created() {
        listFunctions(this.pagination.current, this.pagination.pageSize).then((res) =>
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