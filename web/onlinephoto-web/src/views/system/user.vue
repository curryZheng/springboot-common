<template>
  <div class="user-list">
    <ToolBar :title="'用户管理'">
       <el-row>
          <el-col :span="3">
             <span>用户名：</span>
             <el-input
              placeholder="请输入用户名！"
              size="small"
              style="width: 140px"
              v-model="params.username"
              @clear="searchUser"
              clearable>
             </el-input>
          </el-col>   
          <el-col :span="3">
             <span>姓名：</span>
              <el-input
              placeholder="请输入姓名！"
              size="small"
              style="width: 140px"
              v-model="params.name"
              @clear="searchUser"
              clearable>
            </el-input>
          </el-col>    
          <el-col :span="3">
            <el-button @click="searchUser" type="success" icon="el-icon-search" size="small"></el-button>
          </el-col>  
          <el-col :span="2" :offset="13">
              <el-button type="primary" icon="el-icon-plus" size="small" @click="dialogVisible=true">添加</el-button>
          </el-col>
        </el-row>

    </ToolBar>
    <div class='content'> 
          <el-table
              :data="usersData.data"
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
                label="创建时间"
                :formatter="timeConvString">
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
                width="180">
              <template slot-scope="scope">
                  <el-popconfirm @onConfirm="passwordReset(scope.row.id)"
                    confirmButtonText='确定'
                    cancelButtonText='取消'
                    icon="el-icon-info"
                    iconColor="red"
                    title="确定重置密码吗？"
                  >
                   <el-button slot="reference" type="warning" style="transition: .4s;"  :ref="scope.row.id"  icon="el-icon-refresh" size="small" circle></el-button>
                  </el-popconfirm>
                  <el-button @click="editUser(scope.row)" type="primary" icon="el-icon-edit" size="small" circle></el-button>
                  <el-popconfirm @onConfirm="deleteUser(scope.row.id)"
                    confirmButtonText='确定'
                    cancelButtonText='取消'
                    icon="el-icon-info"
                    iconColor="red"
                    title="确定删除内容吗"
                  >
                  <el-button slot="reference" type="danger" icon="el-icon-delete" circle size="small"></el-button>        
                  </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            background
            :page-size.sync="usersData.pageSize"
            :current-page.sync="usersData.current"
            layout="total,sizes,prev, pager, next, jumper"
            :page-sizes="[10, 20, 30, 40]"
            :total="usersData.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          ></el-pagination>
    </div>
    <el-dialog
    title="新增用户"
    :visible.sync="dialogVisible"
    width="20%">
        <el-form label-width="90px" :model="addUserData">
            <el-form-item label="姓名：">
                <el-col :span="18"> 
                   <el-input v-model="addUserData.name"></el-input>
                </el-col>  
            </el-form-item>
            <el-form-item label="密码：">
                <el-col :span="18"> 
                  <el-input v-model="addUserData.password"></el-input>
                </el-col> 
            </el-form-item>
            <el-form-item label="角色：">
              <el-select v-model="addUserData.roleId" placeholder="请选择">
                <el-option
                  v-for="item in roleData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
               </el-select>
            </el-form-item>
             <el-form-item label="用户编号：">
                <el-col :span="18"> 
                   <el-input v-model="addUserData.userNo"></el-input>
                </el-col>
            </el-form-item>
             <el-form-item label="用户名称：">
                <el-col :span="18"> 
                   <el-input v-model="addUserData.username"></el-input>
                </el-col>
            </el-form-item>
        </el-form>
        <el-row>
          <el-col :span="5"> <el-button type="primary" :loading="loading" @click="addUser()">确定</el-button></el-col>
          <el-col :span="5" :offset="14"> <el-button type="success" @click="dialogVisible=false" >取消</el-button></el-col>      
        </el-row>      
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
        usersData: {
          total:0,
          pageSize:10,
          current:1,
          data:[]
        },
        roleData:[]
      }
    },
    created()	{
        this.searchUser()
        this.searchRole()
    },
    methods: {
      handleSizeChange(){
        this.searchUser()
      },
      handleCurrentChange(){
        this.searchUser()
      },
      //时间格式化
       timeConvString(row, column){
          return this.$utils.timeConvString(row.createTime) 
       },
      //查询角色信息
      searchRole(){
         let APP = this;   
          axios({
              method:'get',
              url:"/role/queryRoleAll",
              params:{}
            }).then(res=>{
                APP.roleData = res.data.data
            }).catch(err=>{
              console.log(err)
            })  
      },
      //查询用户
      searchUser(){        
        let APP = this;   
        axios({
              method:'get',
              url:"/user/",
              params:{
                 current:APP.usersData.current,
                 pageSize:APP.usersData.pageSize,
                 username:APP.params.username
              }
            }).then(res=>{
                APP.usersData = res.data
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
               this.$notify({
                      duration:1000,
                      title: '成功',
                      message: '操作成功',
                      type: 'success'
                    });
               this.loading=false;
               this.dialogVisible=false;
               this.searchUser();
            }).catch(err=>{
              console.log(err)
               this.loading=false;
            })
      },
      //删除用户
      deleteUser(id) {
          axios({
              method:'post',
              url:"/user/deleteUser",
              data:{
                id:id
              }
            }).then(res=>{
                  this.$notify({
                        duration:1000,
                        title: '成功',
                        message: '操作成功',
                        type: 'success'
                      });
                   this.searchUser()
            }).catch(err=>{
              console.log(err)
            })   
      },
      //重置密码
      passwordReset(id){
          axios({
                method:'post',
                url:"/user/passwordReset",
                data:{
                  id:id
                }
              }).then(res=>{
                  this.$notify({
                        title: '成功',
                        message: '密码重置成功',
                        type: 'success'
                      });
              }).catch(err=>{
                console.log(err)
              })   
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
