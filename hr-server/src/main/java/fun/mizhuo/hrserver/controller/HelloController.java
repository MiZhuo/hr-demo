package fun.mizhuo.hrserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mizhuo
 * @time: 2020/8/19 10:19 下午
 * @description:
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/employee/basic/hello2")
    public String hello2(){
        return "hello2";
    }

    @GetMapping("/employee/advanced/hello3")
    public String hello3(){
        return "hello3";
    }
}
