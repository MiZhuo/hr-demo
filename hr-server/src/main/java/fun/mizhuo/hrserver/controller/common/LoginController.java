package fun.mizhuo.hrserver.controller.common;

import com.wf.captcha.ArithmeticCaptcha;
import fun.mizhuo.hrserver.model.ResponseVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/auth/captcha")
    public ResponseVo initCaptcha(HttpServletRequest request, HttpServletResponse response){
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100,30);
        // 几位数运算，默认是两位
        captcha.setLen(3);
        // 获取运算的结果：5
        String result = captcha.text();
        //存入Session中
        request.getSession().setAttribute("captcha",result);
        return ResponseVo.build("",new HashMap<String,Object>(2){{put("img", captcha.toBase64());}});
    }

}
