package fun.mizhuo.hrserver.controller.common;

import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: Mizhuo
 * @time: 2020/9/19 9:09 下午
 * @description: 公用Controller
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @GetMapping("/dropDown/{code}")
    public ResponseVo getDropDownList(@PathVariable String code){
        List<Map<String,String>> data = commonService.getDropDownList(code);
        return ResponseVo.ok("",data);
    }
} 
