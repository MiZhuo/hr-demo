package fun.mizhuo.hrserver.service.employee.basic;

import com.github.pagehelper.PageInfo;
import fun.mizhuo.hrserver.model.Employee;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/14 10:11 下午
 * @description: 员工服务接口类
 */
public interface EmployeeService {
    PageInfo<Employee> getAllEmp(Integer pageNum, Integer pageSize);
}
