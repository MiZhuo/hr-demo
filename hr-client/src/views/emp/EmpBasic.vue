<template>
    <div>
        <div>
            <el-input size="mini" placeholder="通过员工姓名搜索员工" v-model="keyWord"
                      style="width: 300px" prefix-icon="el-icon-search"
                      @keydown.enter.native="searchEmployee"></el-input>
            <el-button type="primary" size="mini"
                       icon="el-icon-search" style="margin-left: 10px" @click="searchEmployee">搜索
            </el-button>
            <el-button type="primary" size="mini"  style="margin-left: 10px" @click="">
                       <i class="fa fa-angle-double-down" aria-hidden="true" style="margin-right: 5px"/>高级搜索
            </el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table :data="employeeTable" size="mini" class="component-table"
                      border stripe style="width: 100%"
                      header-cell-style="text-align:center"
                      cell-style="text-align:center"
                      @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="40" fixed></el-table-column>
                <el-table-column prop="id" label="编号" width="60" fixed></el-table-column>
                <el-table-column prop="name" label="员工姓名" width="100" fixed></el-table-column>
                <el-table-column prop="gender" label="性别" width="60" fixed></el-table-column>
                <el-table-column prop="birthday" label="出生年月" width="100" fixed></el-table-column>
                <el-table-column prop="idCard" label="身份证号" width="150" fixed></el-table-column>
                <el-table-column prop="wedlock" label="婚姻状况" width="80"></el-table-column>
                <el-table-column prop="nationId" label="民族" width="100">
                    <template slot-scope="scope">
                        {{getTableValueByKey('nation',scope.row.nationId)}}
                    </template>
                </el-table-column>
                <el-table-column prop="nativePlace" label="籍贯" width="70"></el-table-column>
                <el-table-column prop="politicId" label="政治面貌" width="100">
                    <template slot-scope="scope">
                        {{getTableValueByKey('politicsstatus',scope.row.politicId)}}
                    </template>
                </el-table-column>
                <el-table-column prop="email" label="邮箱" width="130"></el-table-column>
                <el-table-column prop="phone" label="电话号码" width="100"></el-table-column>
                <el-table-column prop="address" label="联系地址" width="200"></el-table-column>
                <el-table-column prop="departmentId" label="所属部门" width="70">
                    <template slot-scope="scope">
                        {{getTableValueByKey('department',scope.row.departmentId)}}
                    </template>
                </el-table-column>
                <el-table-column prop="jobLevelId" label="职称" width="90">
                    <template slot-scope="scope">
                        {{getTableValueByKey('joblevel',scope.row.jobLevelId)}}
                    </template>
                </el-table-column>
                <el-table-column prop="posId" label="职位" width="90">
                    <template slot-scope="scope">
                        {{getTableValueByKey('position',scope.row.posId)}}
                    </template>
                </el-table-column>
                <el-table-column prop="engageForm" label="聘用形式" width="80"></el-table-column>
                <el-table-column prop="tipTopDegree" label="最高学历" width="100"></el-table-column>
                <el-table-column prop="specialty" label="所属专业" width="140"></el-table-column>
                <el-table-column prop="school" label="毕业院校" width="120"></el-table-column>
                <el-table-column label="入职日期" width="130">
                    <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.beginDate }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="workState" label="在职状态" width="100"></el-table-column>
                <el-table-column prop="workId" label="工号" width="100"></el-table-column>
                <el-table-column prop="contractTerm" label="合同期限" width="70"></el-table-column>
                <el-table-column label="转正日期" width="120">
                    <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.conversionTime }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="离职日期" width="120">
                    <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.notWorkDate }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="合同起始日期" width="120">
                    <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.beginContract }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="合同终止日期" width="120">
                    <template slot-scope="scope">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">{{ scope.row.endContract }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="workAge" label="工龄" width="100"></el-table-column>
                <el-table-column prop="opera" label="操作" width="150">
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
        </div>
    </div>
</template>

<script>
    export default {
        name: "EmpBasic",
        data(){
            return{
                keyWord:'',
                employeeTable:[],
                dropDowns: new Map()
            }
        },
        methods:{
            searchEmployee(){

            },
            initEmployeeTable(){
                this.getRequest('/employee/basic/').then((res)=>{
                    if(res){
                        this.employeeTable = res.result.list;
                    }
                });
            },
            handleSelectionChange(){

            },
            initDropDown(code){
                this.getRequest('/common/dropDown/' + code).then((res)=>{
                    if(res){
                        this.dropDowns.set(code,res.result);
                    }
                });
            },
            getTableValueByKey(code,key) {
                let list = this.dropDowns.get(code);
                for (let i = 0; i < list.length; i++) {
                    if (list[i].id == key) {
                        return list[i].name;
                    }
                }
            }
        },
        mounted() {
            this.initDropDown("nation");
            this.initDropDown("politicsstatus");
            this.initDropDown("department");
            this.initDropDown("joblevel");
            this.initDropDown("position");
            this.initEmployeeTable();
        }
    }
</script>

<style scoped>

</style>