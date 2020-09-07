<template>
    <div>
        <div>
            <el-input placeholder="添加职位..." size="mini" style="width: 300px"
                      prefix-icon="el-icon-plus" v-model="position.name" @keydown.enter.native="addPosition">
            </el-input>
            <el-button type="primary" size="mini"
                       icon="el-icon-plus" style="margin-left: 10px" @click="addPosition">添加
            </el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table :data="positionData" size="mini" class="component-table"
                    border stripe style="width: 65%"
                    @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="id" label="编号" width="80"></el-table-column>
                <el-table-column prop="name" label="职位名称" width="200"></el-table-column>
                <el-table-column prop="createDate" label="创建日期" width="200"></el-table-column>
                <el-table-column prop="enabled" label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-button size="mini" type="danger" :disabled="batchBtnDisabled" style="margin-top:10px" @click="batchDelete">批量删除</el-button>
        </div>
        <el-dialog title="编辑职位" :visible.sync="dialogFormVisible">
            <el-form v-model="positionForm" style="text-align: center">
                <el-form-item>
                    <el-tag type="info">职位编号</el-tag>
                    <el-input class="positionInput" size="mini" v-model="positionForm.id" disabled></el-input>
                </el-form-item>
                <el-form-item>
                    <el-tag>职位名称</el-tag>
                    <el-input class="positionInput" size="mini" v-model="positionForm.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-tag type="info">创建时间</el-tag>
                    <el-input class="positionInput" size="mini" v-model="positionForm.createDate" disabled></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" @click="dialogFormVisible = false">取 消</el-button>
                <el-button size="mini" type="primary" @click="updatePosition">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "PositionManager",
        data(){
            return {
                position:{
                    name : ""
                },
                dialogFormVisible: false,
                positionData:[],
                positionForm:{
                    id : "",
                    name : "",
                    createDate : ""
                },
                batchBtnDisabled: true,
                multipleSelection: []
            }
        },
        methods:{
            initPositionTable(){
                this.getRequest("/system/basic/position/").then(res=>{
                    if(res){
                        this.positionData = res.result;
                    }
                })
            },
            addPosition(){
                if(this.position.name){
                    this.postRequest("/system/basic/position/",this.position).then(()=>{
                        this.initPositionTable();
                        this.position.name = "";
                    });
                }else{
                    this.$message.error("职位名称不能为空!");
                }
            },
            updatePosition(){
                this.putRequest("/system/basic/position/",this.positionForm).then(()=>{
                    this.dialogFormVisible = false;
                    this.initPositionTable();
                })
            },
            handleEdit(index,row){
                Object.assign(this.positionForm,row);
                this.dialogFormVisible = true;
            },
            handleDelete(index,row){
                this.$confirm('此操作将永久删除【'+ row.name +'】职位, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/position/" + row.id).then(()=>{
                        this.initPositionTable();
                    });
                });

            },
            handleSelectionChange(val){
                this.multipleSelection = val;
                if(this.multipleSelection.length > 0){
                    this.batchBtnDisabled = false;
                }else{
                    this.batchBtnDisabled = true;
                }
            },
            batchDelete(){
                this.$confirm('此操作将永久删除所有所选职位, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/position/deleteMore",this.multipleSelection).then(()=>{
                        this.initPositionTable();
                    });
                });
            }
        },
        mounted() {
            this.initPositionTable();
        }
    }
</script>

<style scoped>
.positionInput{
    width: 300px;
    margin-left: 10px;
}
</style>