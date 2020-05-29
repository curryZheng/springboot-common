<template>
<div>
    <ToolBar :title="'日志管理'">
          <el-row>
              <el-col :span="3">
                <span>权限KEY：</span>
                <el-input
                  placeholder="请输入功能编号"
                  size="small"
                  style="width: 140px"
                  @clear="searchOpLog"
                  v-model="params.fnCode"
                  clearable>
                </el-input>
              </el-col>   
              <el-col :span="3">
                <span>功能url：</span>
                  <el-input
                    placeholder="功能url："
                  size="small"
                  style="width: 140px"
                  @clear="searchOpLog"
                  v-model="params.fnUrlKeyword"            
                  clearable>
                </el-input>
              </el-col>   
               <el-col :span="3">
                <span>用户ID：</span>
                  <el-input
                  placeholder="用户ID！"
                  size="small"
                  style="width: 140px"
                  v-model="params.opUserId"      
                  clearable>
                </el-input>
              </el-col>  
              <el-col :span="3">
                <el-button  type="success" icon="el-icon-search" @click="searchOpLog()" size="small"></el-button>
              </el-col>  
              <el-col :span="2" :offset="10">
                  <el-button type="primary" icon="el-icon-plus" :loading="loading" size="small"  @click="excelExport()">导出excel</el-button>
              </el-col>
            </el-row>
      </ToolBar>
   
  <div class='content'> 
    <el-table
        :data="opLogData.data"
        border
        ref="table"
        style="width: 100%">
      <el-table-column
          prop="fnName"
          label="功能名称">
      </el-table-column>
      <el-table-column
          prop="fnUrl"
          label="功能url">
      </el-table-column>
       <el-table-column
          prop="opTime"
          label="操作时间">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="创建时间">
      </el-table-column>
      <el-table-column
          prop="opUserName"
          label="操作人">
      </el-table-column>
      <el-table-column
            prop="req"
            label="请求内容">
       </el-table-column>
      <el-table-column
          prop="result"
          label="操作结果"
          width="180"
          :formatter="resultFormate">
      </el-table-column>
      <!-- <el-table-column
          prop="rsp"
          label="响应内容"
          width="180">
      </el-table-column> -->
    </el-table>
    <el-pagination
        background
        :page-size.sync="opLogData.pageSize"
        :current-page.sync="opLogData.current"
        layout="total,sizes,prev, pager, next, jumper"
        :page-sizes="[10, 20, 30, 40]"
        :total="opLogData.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
  </div> 
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
          fnCode: '',
          fnUrlKeyword:'',
          opUserId:''
        },
        opLogData:{
           total:0,
           pageSize:10,
           current:1,
           data:[]
        }
      }
    },
    created()	{
        this.searchOpLog()
    },
    methods: {
      handleSizeChange(){
        this.searchOpLog()
      },
      handleCurrentChange(){
        this.searchOpLog()
      },
      resultFormate(row,column){
         return this.$enumData.getEnumValue(this.$enumData.result,row.result);
      },
      //查询用户
      searchOpLog(){        
        let APP = this;
        axios({
              method:'get',
              url:"/oplog/queryOpLogList",
              params:{
                pageSize:APP.opLogData.pageSize,
                current:APP.opLogData.current,
                fnCode:APP.params.fnCode,
                fnUrlKeyword:APP.params.fnUrlKeyword,
                opUserId:APP.params.opUserId,
              }
            }).then(res=>{
                APP.opLogData = res.data
            }).catch(err=>{
              console.log(err)
            })   
      },
      //导出
      excelExport(){
           let APP = this;
           APP.loading=true;
          axios({
            method:'get',
            url:"/oplog/exportOpLog",
            params:{},
            responseType:'blob'//服务器返回的数据类型
          }).then(res=>{
            APP.loading=false
            let disposition = res.headers['content-disposition'];
            const content = res.data
            const blob = new Blob([content])//构造一个blob对象来处理数据
            let fileName = decodeURI(disposition.replace("attachment;filename=",""));
            if ('download' in document.createElement('a')) { //支持a标签download的浏览器
              const link = document.createElement('a')//创建a标签
              link.download = fileName//a标签添加属性
              link.style.display = 'none'
              link.href = URL.createObjectURL(blob)
              document.body.appendChild(link)
              link.click()//执行下载
              URL.revokeObjectURL(link.href) //释放url
              document.body.removeChild(link)//释放标签
            } else { //其他浏览器
              navigator.msSaveBlob(blob, fileName)
            }
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
