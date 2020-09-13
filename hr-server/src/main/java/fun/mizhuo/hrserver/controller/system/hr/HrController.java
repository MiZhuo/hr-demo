package fun.mizhuo.hrserver.controller.system.hr;

import fun.mizhuo.hrserver.model.Hr;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.model.Role;
import fun.mizhuo.hrserver.service.system.basic.RoleTeamService;
import fun.mizhuo.hrserver.service.system.hr.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/13 11:04 上午
 * @description: HR-Controller
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;

    @Autowired
    RoleTeamService roleTeamService;

    @GetMapping("/")
    public ResponseVo getAllHrs(){
        List<Hr> hrs = hrService.getAllHrs();
        return ResponseVo.ok("",hrs);
    }

    @PutMapping("/{id}/{enabled}")
    public ResponseVo enabledHrById(@PathVariable Integer id,@PathVariable Boolean enabled){
        Hr hr = new Hr();
        hr.setId(id);
        hr.setEnabled(enabled);
        if(hrService.enabledHrById(hr) == 1){
            if(enabled){
                return ResponseVo.ok("启用成功!");
            }else{
                return ResponseVo.ok("禁用成功!");
            }
        }
        return ResponseVo.error("更新失败!");
    }

    @GetMapping("/roleList")
    public ResponseVo getRoleList(){
        List<Role> roleList = roleTeamService.getAllRoles();
        return ResponseVo.ok("",roleList);
    }
}
