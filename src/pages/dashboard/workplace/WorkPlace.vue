<template>
  <page-layout :avatar="currUser.avatar">
    <div slot="headerContent">
      <div class="title">{{ welcome.timeFix[lang] }}，{{ currUser.name }}，{{ welcome.message[lang] }}</div>
      <div>{{ currUser.position[lang] }}</div>
    </div>
    <template slot="extra">
      <head-info class="split-right" :title="$t('project')" content="56" />
      <head-info class="split-right" :title="$t('ranking')" content="8/24" />
      <head-info class="split-right" :title="$t('visit')" content="2,223" />
    </template>
    <template>
      <a-row style="margin: 0 -12px">
        <a-col style="padding: 0 12px" :xl="16" :lg="24" :md="24" :sm="24" :xs="24">
          <a-card class="project-list" :loading="loading" style="margin-bottom: 24px;" :bordered="false" title="待办事项"
            :body-style="{ padding: 0 }">
            <div v-if="state == '用户管理'">
              <p style="margin-left: 50px;" :key="i" v-for="(it, i) in list">{{ it }}</p>
            </div>
            <div v-if="state == '客户管理'">
              <p style="margin-left: 50px;">待确认订单</p>
              <p style="margin-left: 80px;" :key="i" v-for="(it, i) in list">订单编号为:{{ it.oid }}</p>
              <p style="margin-left: 50px;">库存不足订单</p>
              <p style="margin-left: 80px;" :key="i" v-for="(it, i) in list1">订单编号为:{{ it.oid }}</p>
            </div>
            <div v-if="state == '库存管理'">
              <p style="margin-left: 50px;">待发货订单</p>
              <p style="margin-left: 80px;" :key="i" v-for="(it, i) in list">订单编号为:{{ it.oid }}</p>
              <p style="margin-left: 50px;">未处理退货订单</p>
              <p style="margin-left: 80px;" :key="i" v-for="(it, i) in list1">订单编号为:{{ it.oid }}</p>
            </div>
          </a-card>
          <a-card :loading="loading" :title="$t('dynamic')" :bordered="false">
            <a-list>
              <a-list-item :key="index" v-for="(item, index) in activities">
                <a-list-item-meta>
                  <a-avatar slot="avatar" :src="item.user.avatar" />
                  <div slot="title" v-html="item.template" />
                  <div slot="description">9小时前</div>
                </a-list-item-meta>
              </a-list-item>
            </a-list>
          </a-card>
        </a-col>
        <a-col style="padding: 0 12px" :xl="8" :lg="24" :md="24" :sm="24" :xs="24">
          <a-card :title="$t('access')" style="margin-bottom: 24px" :bordered="false" :body-style="{ padding: 0 }">
            <div class="item-group">
              <a>操作一</a>
              <a>操作二</a>
              <a>操作三</a>
              <a>操作四</a>
              <a>操作五</a>
              <a>操作六</a>
              <a-button size="small" type="primary" ghost icon="plus">{{ $t('add') }}</a-button>
            </div>
          </a-card>
          <a-card :loading="loading" :title="`XX ${$t('degree')}`" style="margin-bottom: 24px" :bordered="false"
            :body-style="{ padding: 0 }">
            <div style="min-height: 400px;">
              <radar />
            </div>
          </a-card>
          <a-card :loading="loading" :title="$t('team')" :bordered="false">
            <div class="members">
              <a-row>
                <a-col :span="12" v-for="(item, index) in teams" :key="index">
                  <a>
                    <a-avatar size="small" :src="item.avatar" />
                    <span class="member">{{ item.name }}</span>
                  </a>
                </a-col>
              </a-row>
            </div>
          </a-card>
        </a-col>
      </a-row>
    </template>
  </page-layout>
</template>

<script>
import PageLayout from '@/layouts/PageLayout'
import HeadInfo from '@/components/tool/HeadInfo'
import Radar from '@/components/chart/Radar'
import { mapState } from 'vuex'
import { request, METHOD } from '@/utils/request'
import { queryItem } from '@/services/operatorItem'
export default {
  name: 'WorkPlace',
  components: { Radar, HeadInfo, PageLayout },
  i18n: require('./i18n'),
  data() {
    return {
      
      projects: [],
      oid: "2",
      state: "",
      list: [],
      list1: [],
      loading: true,
      activities: [],
      teams: [],
      welcome: {
        timeFix: '',
        message: ''
      }
    }
  },
  computed: {
    ...mapState('account', { currUser: 'user' }),
    ...mapState('setting', ['lang'])
  },
  created() {
    request('/user/welcome', METHOD.GET).then(res => this.welcome = res.data)
    request('/work/activity', METHOD.GET).then(res => this.activities = res.data)
    request('/work/team', METHOD.GET).then(res => this.teams = res.data)
    request('/project', METHOD.GET).then(res => {
      this.projects = res.data
      this.loading = false
    })
  },
   watch: {
    $route() {
      queryItem(this.oid).then((res) => {
        this.state = res.data.state
        this.list = res.data.data1
        this.list1 = res.data.data2
      });
    },
  },
  mounted:
    function () {
      queryItem(this.oid).then((res) => {
        this.state = res.data.state
        this.list = res.data.data1
        this.list1 = res.data.data2
      });
    }
}
</script>

<style lang="less">
@import "index";
</style>
