<template>
   <div class="box-main">
       <div class="login-header">
          <img src="../../assets/images/1587633229(1).jpg" width="100%" height="100" alt=""> 
          <p style="margin-top: 30%;"></p>
       </div>
    <div class="box-form">
        <ul>
          <li>
            <div class="lf">姓名：</div>
            <div class="rt">
              <input type="text" v-model="saveData.name" autocomplete="off" placeholder="请输入用户名">
            </div>
          </li>
          <li>
            <div class="lf">身份证：</div>
            <div class="rt"> <input type="text"  v-model="saveData.card"  autocomplete="off" placeholder="请输入用户名"></div>
          </li>
          <li>
            <div class="lf">驾校：</div>
            <div class="rt">
              <van-dropdown-menu>
                <van-dropdown-item
                  v-model="saveData.school"
                  :options="schoolList"  
                />
             </van-dropdown-menu>
            </div>
          </li>
          <li>
            <div class="lf">网点：</div>
            <div class="rt">
               <van-dropdown-menu>
                <van-dropdown-item
                  v-model="saveData.branch"
                  :options="branchList"
                   
                />
               </van-dropdown-menu>
            </div>
          </li>
        </ul>
    </div>
    <div class="box-bottom">
        <van-button type="button" :loading="loading" loading-text="加载中...." @click="save" class="el-button el-button--primary" style="width: 100%; margin-bottom: 18px;border-radius: 25px;">
          <span>确认提交</span>
        </van-button>
    </div>
    
     
   </div>
    
</template>

<script>
export default {
 
  data() {
    return {
      loading:false,
       saveData:{
          name:'',
          card:'',
          school:'',
          branch:'',

       },
       schoolList: [
        { text: 'A驾校', value: 0 },
        { text: 'B驾校', value: 1 },
        { text: 'C驾校', value: 2 }
      ],
        branchList: [
        { text: '罗湖网点', value: 0 },
        { text: '宝安网点', value: 1 },
        { text: '深圳网点', value: 2 }
      ],
    
      };
  },

  methods: {

      save(){
         
          if(this.$utils.isNullOrEmpty(this.saveData.name)){
              this.toast("请填写用户姓名")
              return
          }
          if(this.$utils.isNullOrEmpty(this.saveData.card)){
              this.toast("请填写身份证号")
              return
          }
          if(this.$utils.isNullOrEmpty(this.saveData.school)){
              this.toast("请选择驾校")
              return
          }
          if(this.$utils.isNullOrEmpty(this.saveData.branch)){
              this.toast("请选择网点")
              return
          }
          this.loading=true
          setTimeout(() => {
          
          this.loading=false
          this.toast("提交成功，请拍照")
        }, 3000);
      
      },
      toast(messg){
            this.$toast({
            message: messg,
         })
        },
     
  }
};
</script>
   
<style scoped>
 .rt>input:hover{
    border-bottom: 1px solid #3c8dbc;
}
 .rt>input{  
    -webkit-appearance: none;
    background-color: #fff;
    background-image: none;
    border: 1px solid #dcdfe6;
    box-sizing: border-box;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    height: 40px;
    line-height: 40px;
    outline: 0;
    padding-left:25%;
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    width: 100%;
    border: none;
    border-bottom: 1px solid #9e9e9e;
 }
 .box-main{
    width: 100%;
    height: 100%;
    /* padding: 30px; */
    background-color: white;
    text-align: left;
    
    position: relative;
    margin-left: 0;
    margin-right: 0;
    zoom: 1;
    display: block;
}
.box-main>.login-header {
    text-align: center;
    font-size: 25px;
    margin-bottom: 20%;
    font-weight: bold;
    
}
.box-form{
  margin-bottom: 20%;
}
.box-bottom{
  width: 70%;
  margin: 0 auto;
}
.box-form>ul>li{ 
    border-bottom: 1px solid #eeeff1;
    padding: 0 10px;
    height: 60px;
    line-height: 60px;

}
.lf{
  color: #3c8dbc;
  font-weight: bold;
  float: left;
  text-align: right;
  width: 30%;
}
.rt{
  width: 65%;
  float:left;
}
</style>
