package fun.mizhuo.hrserver.controller;

import fun.mizhuo.hrserver.model.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mizhuo
 * @time: 2020/8/19 10:57 下午
 * @description:
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseVo login(){
        return ResponseVo.error("尚未登录,请登录!");
    }
}
