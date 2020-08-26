<template>
  <div>
      <el-form :rules="rules" :model="loginForm" ref="loginForm" class="loginContainer">
          <h3 class="loginTitle">系统登录</h3>
          <el-form-item prop="username">
              <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" auto-complete="off"
                    @keydown.enter.native="submitLogin"></el-input>
          </el-form-item>
          <el-checkbox class="loginRemember" v-model="checked">记住密码</el-checkbox>
          <el-button type="primary" style="width:100%" @click="submitLogin">登录</el-button>
      </el-form>
  </div>
</template> 

<script>
    export default {
        name : "Login",
        data(){
            return{
                loginForm:{
                    username:'admin',
                    password:'123'
                },
                checked:true,
                rules:{
                    username:[{required:true,message:'请输入用户名',trigger:'blur'}],
                    password:[{required:true,message:'请输入密码',trigger:'blur'}]
                }
            }
        },
        methods:{
            submitLogin(){
                this.$refs.loginForm.validate((valid)=>{
                    if(valid){
                        this.postKeyValueRequest('/doLogin',this.loginForm).then(resp=>{
                            if(resp){
                                window.sessionStorage.setItem("user",JSON.stringify(resp.result));
                                this.$router.replace('/home');
                            }
                        });
                    }else{
                        this.$message.error("请输入所有信息!"); 
                        return false;
                    }
                });
            }
        }
    }
</script>

<style>
    .loginContainer{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 250px;
        padding: 25px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .loginTitle{
        margin: 0px auto 20px auto;
        text-align: center;
        color: #505458;
    }
    .loginRemember{
        text-align: left;
        margin: 0px 0px 15px 0px;
    }
</style>