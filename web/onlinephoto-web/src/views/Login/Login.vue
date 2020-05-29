<template>
  <div class="login">
    <div class="login-form">
      <div class="login-header">
        <img src="../../assets/images/logo.svg" width="100" height="100" alt="">
        <p>{{ $Config.siteName }}</p>
      </div>
      <el-input
          placeholder="请输入用户名"
          suffix-icon="fa fa-user"
          v-model="userNmae"
          style="margin-bottom: 18px"
      >
      </el-input>

      <el-input
          placeholder="请输入密码"
          suffix-icon="fa fa-keyboard-o"
          v-model="password"
          type="password"
          style="margin-bottom: 18px"
          @keyup.native.enter="login"
      >
      </el-input>

      <el-button
          type="primary" :loading="loginLoading"
          style="width: 100%;margin-bottom: 18px"
          @click.native="login"
      >登录
      </el-button>
      <div>
        <el-checkbox v-model="Remenber"> Remenber</el-checkbox>
        <a href="javascript:;" style="float: right;color: #3C8DBC;font-size: 14px">Register</a>
      </div>

    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        userNmae: '',
        password: '',
        Remenber: true,
        loginLoading: false
      }
    },
    methods: {
      login() {
          let APP = this;
           axios({
                method:'post',
                url:"/login",
                data:{
                  username:this.userNmae,
                  password:this.password
                }
              }).then(res=>{
                 if(res.data.code==0){
                     console.log("我来啦啦啦啦啦")
                     sessionStorage.setItem(APP.$Config.tokenKey, res.data.data.token);
                     APP.$Func.setLoginUser(res.data.data)
                     APP.$router.push({path: '/'});
                 }
              }).catch(err=>{
                console.log(err)
              })
           }
       }
  }
</script>

<style lang="less">
  @import "Login.less";
</style>
