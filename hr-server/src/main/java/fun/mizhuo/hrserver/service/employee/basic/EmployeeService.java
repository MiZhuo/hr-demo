package fun.mizhuo.hrserver.service.employee.basic;

import com.github.pagehelper.PageInfo;
import fun.mizhuo.hrserver.model.Employee;

import java.util.List;
import java.util.Map;

/**
 * @author: Mizhuo
 * @time: 2020/9/14 10:11 下午
 * @description: 员工服务接口类
 */
public interface EmployeeService {
    PageInfo<Employee> getAllEmp(Map<String,Object> params);

    String getNewWorkId();

    void addEmployee(Employee employee);
}
