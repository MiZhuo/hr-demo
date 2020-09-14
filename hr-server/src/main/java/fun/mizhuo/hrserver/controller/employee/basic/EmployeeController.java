package fun.mizhuo.hrserver.controller.employee.basic;

import com.github.pagehelper.PageInfo;
import fun.mizhuo.hrserver.model.Employee;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.employee.basic.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/")
    public ResponseVo getAllEmp(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Employee> data = employeeService.getAllEmp(pageNum,pageSize);
        return ResponseVo.ok("",data);
    }
}
