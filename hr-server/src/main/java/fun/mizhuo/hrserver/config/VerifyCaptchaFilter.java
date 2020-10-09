package fun.mizhuo.hrserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.mizhuo.hrserver.exception.HrException;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.util.ErrMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Mizhuo
 * @time: 2020/10/9 10:24 下午
 * @description: 验证验证码过滤器
 */
@Component
public class VerifyCaptchaFilter extends OncePerRequestFilter {

    @Bean
    public VerifyCaptchaFilter getVerifyCaptchaFilter(){
        return new VerifyCaptchaFilter();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (StringUtils.equals("/doLogin", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            // 1. 进行验证码的校验
            try {
                String requestCaptcha = request.getParameter("captcha");
                if (requestCaptcha == null) {
                    throw new HrException(ErrMessage.SYSTEM_ERROR_MESSAGE9);
                }
                String captcha = (String) request.getSession().getAttribute("captcha");
                if (StringUtils.isBlank(captcha)) {
                    throw new HrException(ErrMessage.SYSTEM_ERROR_MESSAGE10);
                }
                captcha = captcha.equals("0.0") ? "0" : captcha;
                logger.info("开始校验验证码，生成的验证码为：" + captcha + " ，输入的验证码为：" + requestCaptcha);
                if (!StringUtils.equals(captcha, requestCaptcha)) {
                    throw new HrException(ErrMessage.SYSTEM_ERROR_MESSAGE11);
                }
            } catch (HrException e) {
                // 2. 捕获步骤1中校验出现异常
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                ResponseVo result = ResponseVo.error(e.getMsg());
                ObjectMapper objectMapper = new ObjectMapper();
                out.write(objectMapper.writeValueAsString(result));
                out.flush();
                out.close();
            } finally {
                filterChain.doFilter(request, response);
            }
        } else{
            filterChain.doFilter(request, response);
        }
    }
}
