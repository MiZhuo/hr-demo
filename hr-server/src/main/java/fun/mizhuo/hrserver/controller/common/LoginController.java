package fun.mizhuo.hrserver.controller.common;

import com.wf.captcha.ArithmeticCaptcha;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author: Mizhuo
 * @time: 2020/8/19 10:57 下午
 * @description:
 */
@RestController
public class LoginController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/login")
    public ResponseVo login(){
        return ResponseVo.error("尚未登录,请登录!");
    }

    @GetMapping("/auth/captcha")
    public ResponseVo initCaptcha(){
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100,30);
        // 几位数运算，默认是两位
        captcha.setLen(3);
        // 获取运算的结果：5
        String result = captcha.text();
        String captchaKey = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为2分钟
        redisUtils.set(captchaKey,result,2 * 60);
        return ResponseVo.build("",new HashMap<String,Object>(2){{put("captchaKey", captchaKey);put("img", captcha.toBase64());}});
    }

}
