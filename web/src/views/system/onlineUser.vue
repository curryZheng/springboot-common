<template>
  <div class="user-list">
    <el-table
        :data="usersData"
        border
        ref="table"
        style="width: 100%">
      <el-table-column
          prop="userNo"
          label="用户编号">
      </el-table-column>
      <el-table-column
          prop="username"
          label="昵称">
      </el-table-column>
       <el-table-column
          prop="name"
          label="姓名">
      </el-table-column>
      <el-table-column
          prop="roleId"
          label="角色编号">
      </el-table-column>
      <el-table-column
          label="操作"
        
          width="180">
        <template slot-scope="scope">
            <el-button @click="kickUser(scope.row.username)" v-if="scope.row.active != '0'" type="danger" icon="el-icon-delete" circle size="small"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
    title="新增用户"
    :visible.sync="dialogVisible"
    width="20%">
        <el-form label-width="70px" :model="addUserData">
            <el-form-item label="姓名">
                <el-input v-model="addUserData.name"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="addUserData.password"></el-input>
            </el-form-item>
            <el-form-item label="角色ID">
                <el-input v-model="addUserData.roleId"></el-input>
            </el-form-item>
             <el-form-item label="用户编号">
                <el-input v-model="addUserData.userNo"></el-input>
            </el-form-item>
             <el-form-item label="用户名称">
                <el-input v-model="addUserData.username"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" :loading="loading" @click="addUser()">确定</el-button>
   </el-dialog>
    </div>
  
</template>

<script>
  import ToolBar from '~/components/ToolBar/ToolBar.vue';
  import HelpHint from '~/components/HelpHint/HelpHint.vue';
  import axios from 'axios'
  export default {
    data() {
      return {
        loading:false,
        dialogVisible:false,
        addUserData:{
           name:'',
           password:'',
           roleId:'',
           userNo:'',
           username:''
        },
        params: {
          username: '',
        },
        usersData: []
      }
    },
    created()	{
        this.searchUser()
    },
    methods: {
      //查询用户
      searchUser(){        
        let APP = this;
        axios({
              method:'get',
              url:"/getOnlineUsers",
            }).then(res=>{
                APP.usersData = res.data.data
            }).catch(err=>{
              console.log(err)
            })   
      },
      //强制下线
      kickUser(username){
            let APP = this;
          axios({
              method:'post',
              url:"/kickUser",
              data:{
                username:username
              }
            }).then(res=>{
              APP.searchUser()
            }).catch(err=>{
              console.log(err)
            })   
      }
    },
    components: {
      ToolBar,HelpHint
    }
  }
</script>
<style lang="less">

</style>
