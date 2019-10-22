package com.wyyx.cn.consumer.config.custom;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

import com.wyyx.cn.consumer.untils.redis.RedisUtils;
import com.wyyx.cn.consumer.vo.SumUserVo;
import com.wyyx.cn.consumer.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class LoginReqComplete implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if (methodAnnotation != null) {
            String token = request.getHeader("token");  // 从 http 请求头中取出 token
            String wxToken = request.getHeader("wxToken");

            //只要token或者wxToken有一个不为空就可以继续执行
            if (StringUtils.isNotEmpty(token) || StringUtils.isNotEmpty(wxToken)) {

                //三元表达式判断userToken取值token或者wxToken
                String userToken = (String) redisUtils.get(StringUtils.isNotEmpty(token) ? token : wxToken);

                if (StringUtils.isNotEmpty(userToken)) {
                    UserVo userVo = JSONObject.parseObject(userToken, UserVo.class);
                    request.setAttribute("userToken", userVo);
                    return true;
                } else {
                    throw new RuntimeException("login error");
                }
            }

            return true;
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
