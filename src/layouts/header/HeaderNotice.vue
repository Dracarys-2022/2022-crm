<template>
  <a-dropdown :trigger="['click']" v-model="show">
    <div slot="overlay">
      <a-spin :spinning="loading">
        <a-tabs class="dropdown-tabs" :tabBarStyle="{textAlign: 'center'}" :style="{width: '297px'}">
          <a-tab-pane tab="通知" key="1">
            <a-list class="tab-pane">
              <a-list-item>
                <a-list-item-meta title="你收到了 14 份新周报" description="一年前">
                  <a-avatar style="background-color: white" slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"/>
                </a-list-item-meta>
              </a-list-item>
              <a-list-item>
                <a-list-item-meta title="你推荐的 曲妮妮 已通过第三轮面试" description="一年前">
                  <a-avatar style="background-color: white" slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png"/>
                </a-list-item-meta>
              </a-list-item>
              <a-list-item>
                <a-list-item-meta title="这种模板可以区分多种通知类型" description="一年前">
                  <a-avatar style="background-color: white" slot="avatar" src="https://gw.alipayobjects.com/zos/rmsportal/kISTdvpyTAhtGxpovNWd.png"/>
                </a-list-item-meta>
              </a-list-item>
            </a-list>
          </a-tab-pane>
          <a-tab-pane tab="消息" key="2">
            <a-list class="tab-pane"></a-list>
          </a-tab-pane>
          <a-tab-pane tab="待办" key="3">
            <a-list class="tab-pane">
              <div v-if="state == '查看所有用户信息'">
              <p style="margin-left: 5px;" :key="i" v-for="(it, i) in list">{{ it }}</p>
            </div>
            <div v-if="state == '添加订单'">
              <p style="margin-left: 5px;">待确认订单</p>
              <p style="margin-left: 20px;" :key="i" v-for="(it, i) in list">订单编号为:{{ it.oid }}</p>
              <p style="margin-left: 5px;">库存不足订单</p>
              <p style="margin-left: 20px;" :key="i" v-for="(it, i) in list1">订单编号为:{{ it.oid }}</p>
            </div>
            <div v-if="state == '查看有库存的'">
              <p style="margin-left: 5px;">待发货订单</p>
              <p style="margin-left: 20px;" :key="i" v-for="(it, i) in list">订单编号为:{{ it.oid }}</p>
              <p style="margin-left: 5px;">未处理退货订单</p>
              <p style="margin-left: 20px;" :key="i" v-for="(it, i) in list1">订单编号为:{{ it.oid }}</p>
            </div>
            </a-list>
          </a-tab-pane>
        </a-tabs>
      </a-spin>
    </div>
    <span @click="fetchNotice" class="header-notice">
      <a-badge class="notice-badge" count="12">
        <a-icon :class="['header-notice-icon']" type="bell" />
      </a-badge>
    </span>
  </a-dropdown>
</template>

<script>
import { queryItem } from '@/services/operatorItem'
export default {
  name: 'HeaderNotice',
  data () {
    return {
      loading: false,
      show: false,
      oid: "2",
      state: "",
      list: [],
      list1: [],
    }
  },
  computed: {
  },
  methods: {
    fetchNotice () {
      if (this.loading) {
        this.loading = false
        return
      }
			if (this.show) return
      this.loading = true
      setTimeout(() => {
        this.loading = false
      }, 100)
      queryItem(this.oid).then((res) => {
        this.state = res.data.state
        this.list = res.data.data1
        this.list1 = res.data.data2
      });
    }

  }
}
</script>

<style lang="less">
  .header-notice{
    display: inline-block;
    transition: all 0.3s;
    span {
      vertical-align: initial;
    }
    .notice-badge{
      color: inherit;
      .header-notice-icon{
        font-size: 16px;
        padding: 4px;
      }
    }
  }
  .dropdown-tabs{
    background-color: @base-bg-color;
    box-shadow: 0 2px 8px @shadow-color;
    border-radius: 4px;
    .tab-pane{
      padding: 0 24px 12px;
      min-height: 250px;
    }
  }
</style>
