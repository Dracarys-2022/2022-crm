<template>
  <a-card>
    <a-result
      status="success"
      title="出库成功"
      sub-title="该订单出库成功，订单状态已经更新"
    >
      <template #extra>
        <a-button type="primary" @click="goout">返回列表</a-button>
        <a-button key="buy">Buy Again</a-button>
      </template>
    </a-result>
  </a-card>
</template>

<script>
export default {
  methods: {
    goout() {
      this.$closePage(this.$route, "/inventory/stockout");
    },
  },
};
</script>

<style>
</style>