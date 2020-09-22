package fun.mizhuo.hrserver.service.impl.employee.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.mizhuo.hrserver.mapper.EmployeeMapper;
import fun.mizhuo.hrserver.model.Employee;
import fun.mizhuo.hrserver.service.employee.basic.EmployeeService;
import fun.mizhuo.hrserver.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Mizhuo
 * @time: 2020/9/14 10:12 下午
 * @description: 员工服务接口实现类
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> getAllEmp(Map<String,Object> params) {
        Integer pageNum = MapUtils.getIntegerValue(params,"pageNum",10);
        Integer pageSize = MapUtils.getIntegerValue(params,"pageSize",10);
        return PageHelper.startPage(pageNum,pageSize).doSelectPageInfo(()->employeeMapper.getAllEmp(params));
    }

    @Override
    public String getNewWorkId() {
        return employeeMapper.getNewWorkId();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }
}
