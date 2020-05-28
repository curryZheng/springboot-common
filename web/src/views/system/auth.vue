<template>
  <div class="user-list">
    <ToolBar>
      <el-button type="primary" icon="el-icon-plus" size="small" @click="editUser(false)">添加</el-button>
      <div style="float: right">
        <el-input
            placeholder="请输入用户昵称！"
            size="small"
            style="width: 140px"
            v-model="params.name"
            @clear="searchAuthDta"
            clearable>
        </el-input>
        <el-button @click="searchAuthDta" type="success" icon="el-icon-search" size="small"></el-button>
      </div>
    </ToolBar>
     <el-table
      :data="authData"
      style="width: 100%;margin-bottom: 20px;"
      row-key="id"
      border
      default-expand-all
      :tree-props="{children: 'childList'}">
        <el-table-column
          prop="code"
          label="权限编号"
        >
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
         >
        </el-table-column>
        <el-table-column
          prop="name"
          label="名称">
        </el-table-column>
        <el-table-column
          prop="opUserName"
          label="操作人">
        </el-table-column>
  
    </el-table>
  </div>
</template>

<script>
  import ToolBar from '~/components/ToolBar/ToolBar.vue';
  import HelpHint from '~/components/HelpHint/HelpHint.vue';
  import axios from 'axios'
  export default {
    data() {
      return {
        params: {
          name: '',
        },
        authData: [
        ]
      }
    },
    created()	{
        this.searchAuthDta()
    },
    methods: {
      searchAuthDta(){
          let APP = this;
          axios({
                method:'get',
                url:"/auth/queryAuthTreeList",
              }).then(res=>{
                  APP.authData = res.data.data
              }).catch(err=>{
                console.log(err)
              })   
      },
     
    },
    components: {
      ToolBar,HelpHint
    }
  }
</script>
<style lang="less">

</style>
