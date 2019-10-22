package com.wyyx.cn.provider.service;

import com.wyyx.cn.provider.model.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/17
 * Time: 14:16
 * Description: No Description
 */

public interface UserService {
    //订单完成后给该用户增加商品价格百分之十的积分（放在微信回调）
    int addUser_scores(User user, BigDecimal goods_price);

    //登录加经验
    int loginAddExp(User user);

    //购买商品加经验（放在微信回调）
    int buyAddExp(User user, BigDecimal goods_price);

    //根据经验值来修改等级
    int changeAndReturnLevel(User user);

    //becomeVip(放入微信支付成功回调函数里)增加30天
    int buyVip(User user);

    //判断是否是超级会员(登录时使用)
    boolean isVip(User user);

    //用id查找用户
    User search(Long id);

    //修改个人信息只能修改一次
    int changePersonInfo(User user);

    //判断是否是生日
    boolean isBirthDay(User user);

    //生日增加一天会员时间
    int becomeTempVip(User user);

    //变成会员状态
    int becomeVipStatus(User user);

    //取消会员状态
    int cannelVipStatus(User user);


    /**
     * Created by GY on 2019/10/18 10:44
     */
    //注册
    int register(User user);

    //查用户名
    boolean isExit(User user);

    //查所有用户
    List<User> getAllUser(User user);

    //完善用户信息
    List<User> userPhone(User user);

    //登录
    User login(String userName, String userPassword);

    //查询购买地址
    String findAddr(User user);

    //查询ip
    String findIpAddr(String userName);
}
