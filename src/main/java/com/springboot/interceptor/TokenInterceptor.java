package com.springboot.interceptor;

import com.springboot.annotation.ManagerAuth;
import com.springboot.annotation.StudentAuth;
import com.springboot.annotation.TeacherAuth;
import com.springboot.enums.UserIdentity;
import com.springboot.exception.LimitAccessException;
import com.springboot.exception.NotLoginError;
import com.springboot.exception.TokenExpiredException;
import com.springboot.utils.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 全局的拦截器  用于token验证和权限识别
 */
@Component
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {

    public TokenInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        log.info("试图拦截" + method.getName());
        String token = request.getHeader("Authorization");


        if (!(method.getName()).equals("error")) {
            if (token != null && token != "" && JwtHelper.parseToken(token) == null) {
                throw new TokenExpiredException();
            }
        }


        if (method.isAnnotationPresent(StudentAuth.class)) {

            if (token == null || token.equals("")) {
                log.info("拦截成功,没有Token");
                throw new NotLoginError();
//                return false;
            } else if (JwtHelper.parseToken(token).get("identity").toString().equals(UserIdentity.STUDENT.toString())) {
                log.info("身份验证通过");
                return true;
            }
            log.info("拦截成功,非学生身份试图访问@StudentAuth");
            throw new LimitAccessException();
//            return false;
        } else if (method.isAnnotationPresent(TeacherAuth.class)) {


            if (token == null || token.equals("")) {
                log.info("拦截成功,没有Token");
                throw new NotLoginError();
//                return false;
            } else if (JwtHelper.parseToken(token).get("identity").toString().equals(UserIdentity.TEACHER.toString())) {
                log.info("身份验证通过");
                return true;
            }
            log.info("拦截成功,非老师身份试图访问@TeacherAuth");
            throw new LimitAccessException();

//            return false;
        } else if (method.isAnnotationPresent(ManagerAuth.class)) {
            if (token == null || token.equals("")) {
                log.info("拦截成功,没有Token");
                throw new NotLoginError();
//                return false;
            } else if (JwtHelper.parseToken(token).get("identity").toString().equals(UserIdentity.MANAGER.toString())) {
                log.info("身份验证通过");
                return true;
            }
            log.info("拦截成功,非管理员身份试图访问@ManagerAuth");
            throw new LimitAccessException();

//            return false;
        }

        log.info("未要求身份识别信息，直接通过");
        return true;
    }

    //    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

//        //2:header中拿token
////        System.out.println(method.getName() + "o98k d");
////        log.debug(method.getName());
////        log.debug("Method: " + method);
////        log.debug("Annos: " + Arrays.deepToString(method.getDeclaredAnnotations()));
////        System.out.println(request.getContextPath());
////        System.out.println(request.getServletPath());
////        System.out.println(request.getContextPath());
//        String token = request.getHeader("Authorization");
//        if(token==null|| token.equals("")){
//            // 没有从request中拿
//            token = request.getParameter("token");
//        }
//
//        //3:token为空
//        if(token==null|| token.equals("")){
//            System.out.println("token 为空，无法通过拦截器");
//            return  false;
//        }
//
//        //下面两步省略，自己可以创建一个简单用户表，然后里面设置token 信息
//        //4:查询token信息 没查到抛出token无效信息
//
//        //5：设置userId到request里，后续根据userId，获取用户信息
//
//        //return  true表示通过了拦截器 可以执行下面的操作
//        return true;
//
//    }

    public String test() throws Exception{
        try {
            int i = 100 / 0;
            return "asdsad";
        } catch (Exception e) {
            return null;
        }
    }


}
