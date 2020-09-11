<template>
    <div>
        <div class="role_input_div">
            <el-input size="mini" placeholder="请输入角色英文名" v-model="role.name">
                <template slot="prepend">ROLE_</template>
            </el-input>
            <el-input size="mini" placeholder="请输入角色中文名" v-model="role.namezh"></el-input>
            <el-button type="primary" size="mini"
                       icon="el-icon-plus" style="margin-left: 10px" @click="addRole">添加角色
            </el-button>
        </div>
        <div class="role_info_div">
            <el-collapse accordion @change="changeRoleMenu">
                <el-collapse-item :title="role.nameZh" :name="role.id" v-for="(role,index) in roles">
                    <el-card class="box-card" shadow="hover">
                        <div slot="header" class="clearfix">
                            <span>可访问资源</span>
                            <el-button style="float: right; padding: 3px 0;color:red" icon="el-icon-delete" type="text"></el-button>
                        </div>
                        <div>
                            <el-tree
                                :data="menuTreeData"
                                show-checkbox
                                node-key="id"
                                :default-checked-keys="checkedMenu"
                                :props="menuTreeProps">
                            </el-tree>
                        </div>
                    </el-card>
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</template>

<script>
    export default {
        name: "RoleTeam",
        data(){
            return{
                role:{},
                roles:[],
                menuTreeData:[],
                menuTreeProps: {
                    children: 'children',
                    label: 'name'
                },
                checkedMenu:[]
            }
        },
        methods:{
            initRoles(){
                this.getRequest("/system/basic/roleTeam/").then(res=>{
                    if(res) {
                        this.roles = res.result;
                    }
                });
            },
            initMenuTreeData(){
                this.getRequest("/system/basic/roleTeam/menuTree").then(res=>{
                    if(res) {
                        this.menuTreeData = res.result;
                    }
                });
            },
            initMenuTreeChecked(roleId){
                this.getRequest("/system/basic/roleTeam/menuTree/checked/" + roleId).then(res=>{
                    if(res) {
                        this.checkedMenu = res.result;
                    }
                });
            },
            changeRoleMenu(roleId){
                if(roleId){
                    this.initMenuTreeData();
                    this.initMenuTreeChecked(roleId);
                }
            }
        },
        mounted() {
            this.initRoles();
        }
    }
</script>

<style>
.role_input_div .el-input{
    width: 250px;
    margin-right: 7px;
}

.role_info_div{
    margin-top: 10px;
    width: 600px
}
</style>