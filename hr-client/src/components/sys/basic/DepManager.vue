<template>
    <div>
        <div>
            <el-input placeholder="输入部门名称搜索部门..." size="mini" style="width: 400px"
                      prefix-icon="el-icon-search" v-model="dept.name" @keydown.enter.native="searchDept">
            </el-input>
        </div>
        <div>
            <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
        </div>
    </div>
</template>

<script>
    export default {
        name: "DepManager",
        data(){
            return {
                dept:{}
            }
        },
        methods:{
            searchDept(){

            }
        }

    }
</script>

<style scoped>

</style>