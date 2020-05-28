<template>
 
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item style="font-weight:bold;color:#409EFF" label="姓名：" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item style="font-weight:bold;color:#409EFF" label="驾校：" prop="school">
    <el-select v-model="ruleForm.school" placeholder="请选择活动区域">
      <el-option label="区域一" value="shanghai"></el-option>
      <el-option label="区域二" value="beijing"></el-option>
    </el-select>
  </el-form-item>
   <el-form-item style="font-weight:bold" label="网点：" prop="branches">
    <el-select v-model="ruleForm.branches" placeholder="请选择活动区域">
      <el-option label="区域一" value="shanghai"></el-option>
      <el-option label="区域二" value="beijing"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item style="font-weight:bold" label="身份证：" prop="card">
    <el-input v-model="ruleForm.card"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
  
  </el-form-item>
</el-form>

</template>

<script>
import axios from 'axios'
  export default {
    data() {
      return {
        ruleForm:{
          name:"",
          school:"",
          branches:"",
          card:""
        },
          rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 2, max: 15, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          school: [
            { required: true, message: '请选择驾校', trigger: 'change' }
          ],
          branches: [
            { required: true, message: '请选择网点', trigger: 'change' }
          ],
          card: [
              { required: true, message: '请输入身份证号', trigger: 'blur' },
          ],
        },
   
        options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        value: '',
        options1: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
     
      }
      
    },
    methods: {
     submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios({
                method:'get',
                url:"dasdasdasdas/dasdasdas",
                params:{

                }
              }).then(res=>{
                this.activityList=res.data.data.slice(0,3)
                this.havcUtil.getImgUrl(res.data.data,this.enumData.imgDataAttriName.activityUrl)
              }).catch(err=>{
                console.log(err)
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
    },
    mounted: function () {
         
    }
  }
</script>
<style lang="less">
   .box-card{
       

  }  .el-col {
    border-radius: 4px;
  }
 
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
    line-height: 36px;
  }
  .grid-content{
    text-align: right;
    color: #409EFF;
  }
  
</style>