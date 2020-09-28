package fun.mizhuo.hrserver.controller.employee.basic;

import com.github.pagehelper.PageInfo;
import fun.mizhuo.hrserver.model.Employee;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.employee.basic.EmployeeService;
import fun.mizhuo.hrserver.util.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: Mizhuo
 * @time: 2020/9/14 9:47 下午
 * @description: 员工资料Controller
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/getAll")
    public ResponseVo getAllEmp(@RequestParam Map<String,Object> params){
        PageInfo<Employee> data = employeeService.getAllEmp(params);
        return ResponseVo.ok("",data);
    }

    @GetMapping("/getWorkId")
    public ResponseVo getNewWorkId(){
        String workId = employeeService.getNewWorkId();
        return ResponseVo.ok("",workId);
    }

    @PostMapping("/")
    public ResponseVo saveEmployee(@RequestBody Employee employee){
        boolean flag = employeeService.checkEmployeeIsExist(employee);
        if(flag){
            if(employeeService.updateEmployee(employee) > 0) {
                return ResponseVo.ok("更新员工资料成功");
            }
            return ResponseVo.error("更新员工资料失败");
        }else {
            Long contract = Math.abs(employee.getEndContract().getTime() - employee.getBeginContract().getTime()) / (1000 * 60 * 60 * 24);
            employee.setContractTerm(contract * 1.00 / 365);
            employee.setWorkState("在职");
            employeeService.addEmployee(employee);
            return ResponseVo.ok("建档成功,员工号为" + employee.getWorkId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseVo deleteEmployee(@PathVariable String id){
        if(employeeService.deleteEmployee(id) > 0){
            return ResponseVo.ok("删除成功!");
        }
        return ResponseVo.error("删除失败!");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportEmployeeData() throws IOException, IllegalAccessException {
        List<Employee> emps = employeeService.getAllEmp();
        return PoiUtils.list2Excel(emps,Employee.class,"员工信息表");
    }
}
