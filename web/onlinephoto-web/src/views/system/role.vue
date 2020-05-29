<template>
 <div>
   <ToolBar :title="'角色管理'">
       <el-row>
          <el-col :span="3">
             <span>角色名：</span>
             <el-input
              placeholder="请输入角色名！"
              size="small"
              style="width: 140px"
              v-model="params.roleName"
              @clear="searchRole"
              clearable>
             </el-input>
          </el-col>   
          <el-col :span="3">
            <el-button @click="searchRole" type="success" icon="el-icon-search" size="small"></el-button>
          </el-col>  
          <el-col :span="2" :offset="16">
              <el-button type="primary" icon="el-icon-plus" size="small" @click="dialogVisible=true">添加</el-button>
          </el-col>
        </el-row>
    </ToolBar>
 <div class='content'> 
    <el-table
        :data="roleData.data"
        border
        ref="table"
        >
      <el-table-column
          prop="name"
          label="角色名">
      </el-table-column>
      <el-table-column
          prop="opUserName"
          label="操作人">
      </el-table-column>
       <el-table-column
          prop="createTime"
          label="创建时间"
          :formatter="timeConvString">
      </el-table-column>
       <el-table-column
          prop="updateTime"
          label="修改时间">
      </el-table-column>
      <el-table-column
              label="操作"
              width="180">
            <template slot-scope="scope">
                <el-button @click="updateRole(scope.row.id)" type="primary" icon="el-icon-edit" size="small" circle></el-button>
                <el-popconfirm @onConfirm="deleteRole(scope.row.id)"
                  confirmButtonText='确定'
                  cancelButtonText='取消'
                  icon="el-icon-info"
                  iconColor="red"
                  title="确定删除吗"
                >
                <el-button slot="reference" type="danger" icon="el-icon-delete" circle size="small"></el-button>        
                </el-popconfirm>
            </template>
          </el-table-column>
    </el-table>
     <el-pagination
            background
            :page-size.sync="roleData.pageSize"
            :current-page.sync="roleData.current"
            layout="total,sizes,prev, pager, next, jumper"
            :page-sizes="[10, 20, 30, 40]"
            :total="roleData.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          ></el-pagination>
     </div>
       <el-dialog
        title="新增角色"
        :visible.sync="dialogVisible"
        width="20%">
            <el-form label-width="70px" :model="addRoleData">
                <el-form-item label="角色名:">
                    <el-input v-model="addRoleData.name"></el-input>
                </el-form-item>
            </el-form>
            <el-row>
              <el-col :span="5"> <el-button type="primary" :loading="loading" @click="addRole()">确定</el-button></el-col>
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
        roleData:{
           total:0,
           pageSize:10,
           current:1,
           data:[]
        },
        params: {
          roleName: '',
        },
        addRoleData:{
          name:'',
        },
        opLogData: []
      }
    },
    created()	{
        this.searchRole()
    },
    methods: {
      handleSizeChange(){
        this.searchRole()
      },
      handleCurrentChange(){
        this.searchRole()
      },
      //查询角色
      searchRole(){        
        let APP = this;
        axios({
              method:'post',
              url:"/role/queryRoleList",
              data:{
                pageSize:APP.roleData.pageSize,
                current:APP.roleData.current,
                name:APP.params.roleName
              }
            }).then(res=>{
                APP.roleData = res.data
            }).catch(err=>{
              console.log(err)
            })   
         },
           //时间格式化
       timeConvString(row, column){
          return this.$utils.timeConvString(row.createTime) 
       },
      //修改角色
      updateRole(id){
         
      },
      //删除角色
      deleteRole(id){
          axios({
            method:'post',
            url:"/role/deleteRole",
            data:{
              id:id
            }
          }).then(res=>{
                this.$notify({
                      duration:500,
                      title: '成功',
                      message: '操作成功',
                      type: 'success'
                    });
                  this.searchRole()
          }).catch(err=>{
            console.log(err)
          })   
      },
      //新增角色
      addRole(){
            let APP = this;
            APP.loading=true;
            axios({
            method:'post',
            url:"/role/createRole",
            data:{
               name:APP.addRoleData.name
            }
          }).then(res=>{
              APP.dialogVisible=false
              APP.loading=false;
              APP.searchRole()
              this.$notify({
                  duration:500,
                  title: '成功',
                  message: '新增角色成功',
                  type: 'success'
                });
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
