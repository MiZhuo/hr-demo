<template>
    <div>
        <div>
            <el-input placeholder="输入部门名称搜索部门..." size="mini" style="width: 450px"  v-model="filterText"
                      prefix-icon="el-icon-search">
            </el-input>
        </div>
        <div style="margin-top: 10px">
            <el-tree class="filter-tree" :data="deptTreeData" :props="deptTreeProps" :expand-on-click-node="false"
                     :filter-node-method="filterNode" ref="deptTree" style="width: 450px">
                    <span class="custom-tree-node" slot-scope="{ node, data }"
                        style="display: flex;justify-content: space-between;width: 100%">
                        <span>{{ node.label }}</span>
                        <span>
                          <el-button
                                  type="primary"
                                  size="mini"
                                  class="deptTreeBtn"
                                  @click="() => addDept(data)">
                            添加部门
                          </el-button>
                          <el-button
                                  type="danger"
                                  size="mini"
                                  class="deptTreeBtn"
                                  @click="() => deleteDept(node, data)">
                            删除部门
                          </el-button>
                        </span>
                    </span>
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
            },

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

<style>
.deptTreeBtn{
    padding: 2px;
}
</style>