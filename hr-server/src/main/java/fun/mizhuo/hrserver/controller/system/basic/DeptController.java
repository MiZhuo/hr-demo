package fun.mizhuo.hrserver.controller.system.basic;

import fun.mizhuo.hrserver.model.Department;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.system.basic.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/12 3:21 下午
 * @description: 部门Controller
 */
@RestController
@RequestMapping("/system/basic/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/deptTree")
    public ResponseVo getDeptTree(){
        List<Department> departments = deptService.getDeptTree();
        return ResponseVo.ok("",departments);
    }

    @PostMapping("/")
    public ResponseVo addDept(@RequestBody Department department){
        deptService.addDept(department);
        if( department.getResult()== 1){
            return ResponseVo.ok("添加成功",department);
        }
        return ResponseVo.error("添加失败!");
    }
}
