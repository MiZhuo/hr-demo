<template>
    <div>
        <div>
            <el-input placeholder="输入部门名称搜索部门..." size="mini" style="width: 400px"  v-model="filterText"
                      prefix-icon="el-icon-search">
            </el-input>
        </div>
        <div style="margin-top: 10px">
            <el-tree class="filter-tree" :data="deptTreeData" :props="deptTreeProps"
                     :filter-node-method="filterNode" ref="deptTree">
            </el-tree>
        </div>
    </div>
</template>

<script>
    export default {
        name: "DepManager",
        data(){
            return {
                deptTreeData:[],
                deptTreeProps: {
                    children: 'children',
                    label: 'name'
                },
                filterText: ''

            }
        },
        methods:{
            initDepTree(){
                this.getRequest("/system/basic/dept/deptTree").then(res=>{
                    if(res){
                        this.deptTreeData = res.result;
                    }
                });
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.name.indexOf(value) !== -1;
            }
        },
        watch: {
            filterText(val) {
                this.$refs.deptTree.filter(val);
            }
        },
        mounted() {
            this.initDepTree();
        }

    }
</script>

<style scoped>

</style>