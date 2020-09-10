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
            <el-collapse v-model="activeName" accordion>
                <el-collapse-item title="一致性 Consistency" name="1">
                    <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
                    <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
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
                role:{}
            }
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