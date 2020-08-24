<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">微人事</div>
                <el-dropdown class="userInfo" @command="commandHandler">
                  <span class="el-dropdown-link">
                      <img :src="user.userface">{{user.name}}
                      <i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="userinfo"><i class="el-icon-user-solid"></i>个人中心</el-dropdown-item>
                    <el-dropdown-item command="setting"><i class="el-icon-s-tools"></i>设置</el-dropdown-item>
                    <el-dropdown-item command="logout" divided><i class="el-icon-s-fold"></i>注销登录</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
            </el-header>
        </el-container>
        <el-container>
            <el-aside width="180px">
                <el-menu router>
                    <el-submenu :index="index + ''" v-for="(item,index) in routes" :key="index" v-if="!item.hidden">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span slot="title">{{item.name}}</span>
                        </template>
                        <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">{{child.name}}</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-main>Main</el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    name : "Home",
    data(){
        return {
            user : JSON.parse(window.sessionStorage.getItem("user")),
            collapse : true
        }
    },
    methods:{
        commandHandler(cmd){
            switch (cmd) {
                case "userinfo" : {
                    break;
                }
                case "setting" : {
                    break;
                }
                case "logout" : {
                    this.$confirm('是否退出登录?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest("/logout");
                        window.sessionStorage.removeItem("user");
                        this.$router.replace("/");
                    });
                    break;
                }
                default : {

                }
            }
        },
        mouseOver(){
            this.collapse = false;
        },
        mouseOut(){
            this.collapse = true;
        }
    },
    computed:{
        routes(){
            return this.$store.state.routes;
        }
    }
}
</script>

<style>
.homeHeader{
    background-color: #409eff;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0px 15px;
    box-sizing: border-box;
}

.homeHeader .title{
    font-size: 30px;
    font-family: 微软雅黑;
    color: white;
}

.homeHeader .userInfo{
    cursor: pointer;
}

.el-dropdown-link img{
    width : 38px;
    height: 38px;
    border-radius: 20px;
    margin-right: 8px;
}

.el-dropdown-link{
    display: flex;
    align-items: center;
}
</style>