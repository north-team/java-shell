package com.fit2cloud.java.shell.config;

import com.fit2cloud.java.shell.util.CodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author : zhm
 * @description :
 * @date : 2019/4/1 10:59
 */
@Component
public class TokenHandleInterceptor implements HandlerInterceptor {

    @Autowired
    Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //如果不是映射到方法，直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = ((HandlerMethod) o);

        Method method = handlerMethod.getMethod();

        //获取方法上的tokenvalid注解
        TokenValid tokenValid = method.getAnnotation(TokenValid.class);

        //如果方法上有tokenValid注解
        if (tokenValid != null) {
            //获取请求上传的token信息
            String shellToken = httpServletRequest.getHeader("shellToken");
            //获取系统的token
            String token = CodingUtil.md5("Fit2cloudShellToken:" + environment.getProperty("login.username") + environment.getProperty("login.password"));
            //如果token合法
            if (!token.equals(shellToken)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

