package fun.mizhuo.hrserver.controller.system.hr;

import fun.mizhuo.hrserver.model.Hr;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.system.hr.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/")
    public ResponseVo getAllHrs(){
        List<Hr> hrs = hrService.getAllHrs();
        return ResponseVo.ok("",hrs);
    }
}
