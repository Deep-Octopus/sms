package com.example.ktp.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.ktp.bean.annotations.PassToken;
import com.example.ktp.bean.annotations.UserLoginToken;
import com.example.ktp.bean.entity.User;
import com.example.ktp.dto.Result;
import com.example.ktp.service.UserService;
import com.example.ktp.util.HttpContextUtil;
import com.example.ktp.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

//@CrossOrigin(allowCredentials = "true")
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {

            // * 这里因为拦截了OPTION请求，返回false的话是不会有响应头的，需要我们自己写返回头

            //设置允许的访问源
            response.setHeader("Access-Control-Allow-Origin", "*");
            //设置验证
            response.setHeader("Access-Control-Allow-Credentials","false");
            //设置允许的请求类型
            response.setHeader("Access-Control-Allow-Methods", "*");
            //设置超时时间
            response.setHeader("Access-Control-Max-Age", "1800");
            //设置允许的请求头参数
            response.setHeader("Access-Control-Allow-Headers", "*");

            //返回203
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());

            //拦截掉，不让继续往下走
            return false;
        }
        String token = JwtUtils.getRequestToken(request);
        System.out.println("进来拦截了+"+token);
        if (StringUtils.isBlank(token)) {
            setReturn(response, 400, "用户未登录，请先登录");
            return false;
        }


        //1. 根据token，查询用户信息

//        User user = new User();
        String username;
        try {
            username = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        User user = userService.getByUsername(username);
        //2. 若用户不存在,
        if (user == null) {
            setReturn(response, 400, "用户不存在");
            return false;
        }
        //3. token失效
        if (JwtUtils.isTokenExpired(new Date())) {
            setReturn(response, 400, "用户登录凭证已失效，请重新登录");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //方法处理之后但是并未渲染视图的时候进行的操作
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //渲染视图之后进行的操作
    }

//    @ResponseBody
    //返回错误信息
    private static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        //UTF-8编码
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
//        return Result.buildErr(status,msg);

        String json = JSON.toJSONString(Result.buildErr(status,msg));
        httpResponse.getWriter().print(json);
    }


}
