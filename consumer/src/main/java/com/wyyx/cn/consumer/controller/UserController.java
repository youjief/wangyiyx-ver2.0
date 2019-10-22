package com.wyyx.cn.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.wyyx.cn.consumer.config.custom.LoginRequired;
import com.wyyx.cn.consumer.contants.ReturnResultContants;
import com.wyyx.cn.consumer.contants.UserContants;
import com.wyyx.cn.consumer.contants.wxLogin.WxLogin;
import com.wyyx.cn.consumer.untils.IpAdrressUtil;
import com.wyyx.cn.consumer.untils.redis.RedisUtils;
import com.wyyx.cn.consumer.untils.result.ReturnResult;
import com.wyyx.cn.consumer.untils.result.ReturnResultUtils;
import com.wyyx.cn.consumer.untils.url.UrlUtils;
import com.wyyx.cn.consumer.vo.PerfectVo;
import com.wyyx.cn.consumer.vo.UserVo;
import com.wyyx.cn.consumer.wxUser.WxUserLogin;
import com.wyyx.cn.provider.model.User;
import com.wyyx.cn.provider.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/17
 * Time: 19:47
 * Description: No Description
 */

/**
 * Created by GY on 2019/10/17 14:23
 */
@Api(tags = "用户控制")
@Controller
@RequestMapping(value = "/wx")
public class UserController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private WxLogin wxLogin;

    @Reference
    private UserService userService;

    /**
     * Created with IntelliJ IDEA.
     * User: 91917
     * Date: 2019/10/17
     * Time: 19:47
     * Description: No Description
     */
    @ApiOperation(value = "订单完成后给该用户增加商品价格百分之十的积分")
    @GetMapping(value = "/addUser_scores")
    //订单完成后给该用户增加商品价格百分之十的积分（放在微信回调）
    public int addUser_scores(User user, BigDecimal goods_price) {
        return userService.addUser_scores(user, goods_price);
    }

    @ApiOperation(value = "登录加经验")
    @GetMapping(value = "/loginAddExp")
    //登录加经验
    public int loginAddExp(User user) {
        return userService.loginAddExp(user);
    }

    @ApiOperation(value = "购买商品加经验")
    @GetMapping(value = "/buyAddExp")
    //购买商品加经验（放在微信回调）
    public int buyAddExp(User user, BigDecimal goods_price) {
        return userService.buyAddExp(user, goods_price);
    }

    @ApiOperation(value = "根据经验值来修改等级")
    @GetMapping(value = "/changeAndReturnLevel")
    //根据经验值来修改等级
    public int changeAndReturnLevel(User user) {
        return userService.changeAndReturnLevel(user);
    }

    @ApiOperation(value = "becomeVip")
    @GetMapping(value = "/buyVip")
    //becomeVip(放入微信支付成功回调函数里)增加30天
    public int buyVip(User user) {
        return userService.buyVip(user);
    }

    @ApiOperation(value = "判断是否是超级会员")
    @GetMapping(value = "/isVip")
    //判断是否是超级会员(登录时使用)
    public boolean isVip(User user) {
        return userService.isVip(user);
    }

    @ApiOperation(value = "用id查找用户")
    @GetMapping(value = "/search")
    //用id查找用户
    public User search(Long id) {
        return userService.search(id);
    }


    @ApiOperation(value = "修改个人信息只能修改一次")
    @GetMapping(value = "/changePersonInfo")
    //修改个人信息只能修改一次
    public int changePersonInfo(User user) {
        return userService.changePersonInfo(user);
    }

    @ApiOperation(value = "判断是否是生日")
    @GetMapping(value = "/isBirthDay")
    //判断是否是生日
    public boolean isBirthDay(User user) {
        return userService.isBirthDay(user);
    }

    @ApiOperation(value = "生日增加一天会员时间")
    @GetMapping(value = "/becomeTempVip")
    //生日增加一天会员时间
    public int becomeTempVip(User user) {
        return userService.becomeTempVip(user);
    }

    @ApiOperation(value = "变成会员状态")
    @GetMapping(value = "/becomeVipStatus")
    //变成会员状态
    public int becomeVipStatus(User user) {
        return userService.becomeVipStatus(user);
    }

    @ApiOperation(value = "取消会员状态")
    @GetMapping(value = "/cannelVipStatus")
    //取消会员状态
    public int cannelVipStatus(User user) {
        return userService.cannelVipStatus(user);
    }


    @ApiOperation(value = "ces")
    @GetMapping(value = "/t")
    public String test(User user) {
        int i = userService.buyVip(user);
        if (i == 0) {
            return "fail";
        }
        return "success";
    }

    @ApiOperation(value = "addmouth")
    @GetMapping(value = "/twa")
    public String testa(User user) {
        user = userService.search(user.getUserId());
        boolean i = userService.isVip(user);
        if (i) {
            return "fail";
        }
        return "success";
    }

    /**
     * Created by GY on 2019/10/17 14:23
     */
    @ApiOperation("微信登录")
    @GetMapping("/send")
    public String sendCode() {
        return wxLogin.reqCodeUrl();
    }


    @ResponseBody
    @GetMapping("/return")
    public String returnCode(String code) {
        String wxLoginUrl = wxLogin.reqAccessTokenUrl(code);
        String wxReback = UrlUtils.loadURL(wxLoginUrl);
        JSONObject js = JSONObject.parseObject(wxReback);
        String accessToken = js.getString("access_token");
        String openid = js.getString("openid");
        String userInfoUrl = wxLogin.reqUserInfoUrl(accessToken, openid);
        String userInfoStr = UrlUtils.loadURL(userInfoUrl);
        WxUserLogin wxUserLogin = JSONObject.parseObject(userInfoStr, WxUserLogin.class);
        User user = new User();
        user.setOpenid(wxUserLogin.getOpenid());
        user.setNickname(wxUserLogin.getNickname());
        user.setSex(wxUserLogin.getSex());
        user.setLanguage(wxUserLogin.getLanguage());
        user.setCity(wxUserLogin.getCity());
        user.setProvince(wxUserLogin.getProvince());
        user.setCountry(wxUserLogin.getCountry());
        user.setHeadimgurl(wxUserLogin.getHeadimgurl());
        boolean isEixt = userService.isExit(user);
        if (isEixt == false) {
            userService.register(user);
        }
        String wxId = JSONObject.parseObject(userInfoStr).getString("openid");
        redisUtils.set(UserContants.LOGIN_NAME_SPACE + wxId, userInfoStr, 180);
        return "redirect:http://localhost:8080/doc.html";
    }

    @ApiOperation("注册")
    @GetMapping(value = "/register")
    @ResponseBody
    public ReturnResult Register(@Valid UserVo userVo, HttpServletRequest request) {

        if (userVo.getAgree() != null) {
            boolean isExit = null == redisUtils.get(UserContants.LOGIN_NAME_SPACE + userVo.getUserName()) ? false : true;
            if (!isExit) {
                User user = new User();
                user.setUserName(userVo.getUserName());
                user.setUserPassword(userVo.getUserPassword());
                user.setUserPhone(userVo.getUserPhone());
                user.setBirthday(userVo.getBirthday());
                user.setSex(userVo.getSex());
                Date date = new Date();
                user.setCreateTime(date);
                user.setIsDelete(0);
                user.setUserLevel(1);
                user.setUpdateTime(date);
                String ip = IpAdrressUtil.getIpAddr(request);
                user.setIpAddress(ip);
                try {
                    redisUtils.set(UserContants.LOGIN_NAME_SPACE + userVo.getUserName(), user, 180);
                    userService.register(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ReturnResultUtils.returnSuccess(ReturnResultContants.MSG_SUCCESS_EXIST);
            }
            return ReturnResultUtils.returnFail(ReturnResultContants.LOGIN_WRONG, ReturnResultContants.MSG_REGISTER_ALREADY_EXIST);
        }
        return ReturnResultUtils.returnFail(ReturnResultContants.LOGIN_WRONG, ReturnResultContants.MSG_LOGIN_AGREE);
    }

    @ApiOperation("完善用户信息")
    @GetMapping(value = "/userPhone")
    @ResponseBody
    public ReturnResult userPhone(@Valid PerfectVo perfectVo) {
        User user = new User();
        user.setUserPhone(perfectVo.getUserPhone());
        user.setUserName(perfectVo.getUserName());
        user.setUserPassword(perfectVo.getUserPassword());
        user.setBirthday(perfectVo.getBirthday());
        user.setSex(perfectVo.getSex());
        userService.userPhone(user);
        return ReturnResultUtils.returnSuccess(ReturnResultContants.MSG_SUCCESS_EXIST);
    }

    @ApiOperation("普通登录")
    @GetMapping(value = "/Login")
    @ResponseBody
    public ReturnResult Login(@ApiParam(value = "用户名", required = true) @RequestParam(value = "userName") String userName,
                              @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String userPassword,
                              @ApiParam(value = "同意服务条款", required = true) @RequestParam(value = "agree") String agree,
                              HttpServletRequest request) {
        if (agree != null) {
            String token = request.getSession().getId();
            User user = userService.login(userName, userPassword);
            if (null != user) {
                String str = JSONObject.toJSONString(user);
                redisUtils.set(token, str, 180);
                request.getSession().setAttribute("token", token);
                String ip = IpAdrressUtil.getIpAddr(request);
                String addr = userService.findIpAddr(userName);
                if (addr.equals(ip)) {
                    loginAddExp(user);
                    return ReturnResultUtils.returnSuccess();
                }
                return ReturnResultUtils.returnSuccess(ReturnResultContants.MSG_NOT_ONELY_ONE);
            }
            return ReturnResultUtils.returnFail(ReturnResultContants.LOGIN_WRONG, ReturnResultContants.MSG_WRONG_LOGIN);
        }
        return ReturnResultUtils.returnFail(ReturnResultContants.LOGIN_WRONG, ReturnResultContants.MSG_LOGIN_AGREE);
    }
}
