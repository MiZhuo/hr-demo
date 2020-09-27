package fun.mizhuo.hrserver.mapper;

import fun.mizhuo.hrserver.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    List<Employee> getAllEmp(Map<String,Object> params);

    String getNewWorkId();

    void addEmployee(Employee employee);

    int deleteEmployee(String id);

    int checkEmployeeIsExist(Employee employee);

    int updateEmployee(Employee employee);
}