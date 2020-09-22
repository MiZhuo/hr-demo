package fun.mizhuo.hrserver.controller.employee.basic;

import com.github.pagehelper.PageInfo;
import fun.mizhuo.hrserver.model.Employee;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.employee.basic.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseVo addEmployee(@RequestBody Employee employee){
        Long contract = (employee.getEndContract().getTime() - employee.getBeginContract().getTime()) / (1000 * 60 * 60 * 24);
        employee.setContractTerm(contract * 1.00 / 365);
        employee.setWorkState("在职");
        employeeService.addEmployee(employee);
        return ResponseVo.ok("建档成功,员工号为" + employee.getWorkId());
    }

}
