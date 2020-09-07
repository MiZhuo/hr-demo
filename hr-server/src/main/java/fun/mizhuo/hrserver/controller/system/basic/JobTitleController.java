package fun.mizhuo.hrserver.controller.system.basic;

import fun.mizhuo.hrserver.model.JobLevel;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.system.basic.JobLevelService;
import fun.mizhuo.hrserver.service.system.basic.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/7 22:42 下午
 * @description:
 */
@RestController
@RequestMapping("/system/basic/jobTitle")
public class JobTitleController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public ResponseVo getAllJobTitle(){
        List<JobLevel> jobTitles = jobLevelService.getAllJobTitle();
        return ResponseVo.ok("",jobTitles);
    }

    @PostMapping("/")
    public ResponseVo addJobTitle(@RequestBody JobLevel jobTitle){
        if(jobLevelService.addJobTitle(jobTitle) == 1){
            return ResponseVo.ok("添加成功!");
        }
        return ResponseVo.error("添加失败!");
    }

    @PutMapping("/")
    public ResponseVo updateJobTitle(@RequestBody JobLevel jobTitle){
        if(jobLevelService.updateJobTitle(jobTitle) == 1){
            return ResponseVo.ok("更新成功!");
        }
        return ResponseVo.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public ResponseVo deleteJobTitle(@PathVariable Integer id){
        if(jobLevelService.deleteJobTitle(id) == 1){
            return ResponseVo.ok("删除成功!");
        }
        return ResponseVo.error("删除失败!");
    }

    @DeleteMapping("/deleteMore")
    public ResponseVo deleteMoreJobTitle(@RequestBody List<JobLevel> jobTitles){
        if(jobLevelService.deleteMoreJobTitle(jobTitles) > 0){
            return ResponseVo.ok("删除成功!");
        }
        return ResponseVo.error("删除失败!");
    }
}
