<template>
  <a-dropdown>
    <div class="header-avatar" style="cursor: pointer">
      <a-avatar class="avatar" size="small" shape="circle" :src="user.avatar"/>
      <span class="name">{{name}}</span>
    </div>
    <a-menu :class="['avatar-menu']" slot="overlay">
      <a-menu-item @click="center">
        <a-icon type="user" />
        <span>个人中心</span>
      </a-menu-item>
      <a-menu-item>
        <a-icon type="setting" />
        <span>设置</span>
      </a-menu-item>
      <a-menu-divider />
      <a-menu-item @click="logout">
        <a-icon style="margin-right: 8px;" type="poweroff" />
        <span>退出登录</span>
      </a-menu-item>
      
    </a-menu>
  </a-dropdown>
</template>

<script>
import {mapGetters} from 'vuex'
import {logout} from '@/services/user'

export default {
  name: 'HeaderAvatar',
  data(){
    return{
      name:"",
    }
  },
  computed: {
    ...mapGetters('account', ['user']),
  },
  watch: {
    $route() {
      this.name=localStorage.getItem('oname');
      this.name=this.name.substring(1, this.name.length-1)
      // queryItem(this.oid).then((res) => {
      //   this.state = res.data.state
      //   this.list = res.data.data1
      //   this.list1 = res.data.data2
      // });
    },
  },
  mounted:
    function () {
      this.name=localStorage.getItem('oname');
      this.name=this.name.substring(1, this.name.length-1)
      //console.log(this.name);
      // queryItem(this.oid).then((res) => {
      //   this.state = res.data.state
      //   this.list = res.data.data1
      //   this.list1 = res.data.data2
      // });
    },
  methods: {
    center() {
      this.$router.replace({
        path: '/operator/center',
        //query: { cid: e }
      });
    },
    logout() {
      logout()
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="less">
  .header-avatar{
    display: inline-flex;
    .avatar, .name{
      align-self: center;
    }
    .avatar{
      margin-right: 8px;
    }
    .name{
      font-weight: 500;
    }
  }
  .avatar-menu{
    width: 150px;
  }

</style>
