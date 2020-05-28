<template>
  <div class="user-list">
    <ToolBar>
      <el-button type="primary" icon="el-icon-plus" size="small" @click="dialogVisible=true">添加</el-button>
      <div style="float: right">
        <el-input
            placeholder="请输入用户名！"
            size="small"
            style="width: 140px"
            v-model="params.username"
            @clear="searchUser"
            clearable>
        </el-input>
        <el-input
            placeholder="请输入姓名！"
            size="small"
            style="width: 140px"
            v-model="params.name"
            @clear="searchUser"
            clearable>
        </el-input>
        <el-button @click="searchUser" type="success" icon="el-icon-search" size="small"></el-button>
      </div>
    </ToolBar>
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
          prop="createTime"
          label="创建时间">
      </el-table-column>
      <el-table-column
          prop="roleId"
          label="角色编号">
      </el-table-column>
      <el-table-column
            prop="opUserName"
            label="操作人">
       </el-table-column>
      <el-table-column
          label="操作"
          :render-header="tableAction"
          width="180">
        <template slot-scope="scope">
            <el-button @click="resetting(scope.row.id)" type="warning" style="transition: .4s;"  :ref="scope.row.id"  icon="el-icon-refresh" size="small" circle></el-button>
            <el-button @click="editUser(scope.row)" type="primary" icon="el-icon-edit" size="small" circle></el-button>
            <el-button @click="deleteUser(scope.row.id)" v-if="scope.row.active != '0'" type="danger" icon="el-icon-delete" circle size="small"></el-button>
            <el-button @click="deleteUser(scope.row.id)" v-else icon="el-icon-check" circle size="small"></el-button>
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
              url:"/user/",
              params:APP.params
            }).then(res=>{
                APP.usersData = res.data.data
            }).catch(err=>{
              console.log(err)
            })   
      },
      //创建用户
      addUser(){
        console.log("aaaa")
         this.loading=true;
          axios({
              method:'post',
              url:"/user/createUser",
              data:this.addUserData
            }).then(res=>{
               this.loading=false;
               this.dialogVisible=false;
               this.searchUser();
            }).catch(err=>{
              console.log(err)
               this.loading=false;
            })
      },
      tableAction(){
        return this.$createElement('HelpHint',{
            props:{
              content:'重置密码为123456 / 编辑用户 / 删除或恢复用户'
            }
          },'操作');
      },
      editUser(data) {

      },
      UploadUser(data) {

      },
      deleteUser(id) {

          this.$message({
            message: '这里请求api删除或者恢复用户之后刷新分页组件，列表自动更新',
            type: 'success'
          });

      },
      resetting(id) {

      let dom = this.$refs[id].$el;
      dom.style.transform = 'rotate(180deg)';
      setTimeout(()=>{dom.style.transform = 'rotate(0deg)'},600)
      this.$message({
        message: '已经成功重置密码',
        type: 'success'
      });

      },


    },
    components: {
      ToolBar,HelpHint
    }
  }
</script>
<style lang="less">

</style>
