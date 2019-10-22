//package com.wyyx.cn.consumer.config.custom;
//
//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.alibaba.fastjson.JSONObject;
//import com.wyyx.cn.consumer.untils.redis.RedisUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * Created with IntelliJ IDEA.
// * User: 91917
// * Date: 2019/10/18
// * Time: 13:44
// * Description: No Description
// */
//public class CheckVipComplete implements HandlerInterceptor {
//    @Autowired
//    private RedisUtils redisUtils;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        // 判断接口是否需要登录
//        CheckVip methodAnnotation = method.getAnnotation(CheckVip.class);
//        if (methodAnnotation != null) {
//            String token = request.getHeader("token");  // 从 http 请求头中取出 token
//            String wxToken = request.getHeader("wxToken");
//
//            //只要token或者wxToken有一个不为空就可以继续执行
//            if (StringUtils.isNotEmpty(token) || StringUtils.isNotEmpty(wxToken)) {
//
//                //三元表达式判断userToken取值token或者wxToken
//                String userToken = (String) redisUtils.get(StringUtils.isNotEmpty(token) ? token : wxToken);
//
//                if (StringUtils.isNotEmpty(userToken)) {
//                    SumUserVo sumUserVo = JSONObject.parseObject(userToken, SumUserVo.class);
//                    request.setAttribute("userToken", sumUserVo);
//                    return true;
//                } else {
//                    throw new RuntimeException("login error");
//                }
//            }
//
//            return true;
//        }
//        return true;
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
