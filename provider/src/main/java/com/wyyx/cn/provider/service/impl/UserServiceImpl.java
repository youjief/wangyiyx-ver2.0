package com.wyyx.cn.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyyx.cn.provider.contants.UserContants;
import com.wyyx.cn.provider.contants.base.DateContants;
import com.wyyx.cn.provider.contants.base.NumberContants;
import com.wyyx.cn.provider.mapper.UserMapper;
import com.wyyx.cn.provider.model.User;
import com.wyyx.cn.provider.model.UserExample;
import com.wyyx.cn.provider.service.UserService;

import com.wyyx.cn.provider.until.dateutil.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/17
 * Time: 14:17
 * Description: No Description
 */

/**
 * Created by GY on 2019/10/17 15:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    TimeUtil timeUtil = new TimeUtil();

    /*
     * fyj
     * */


    //订单完成后给该用户增加商品价格百分之十的积分（放在微信回调）
    @Override
    public int addUser_scores(User user, BigDecimal goods_price) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        int oldScore = userMapper.selectByPrimaryKey(user.getUserId()).getUserScores();
        int newScore = oldScore + goods_price.intValue() / UserContants.PRICE_TO_ADD_SCORE_BASE;
        user.setUserScores(newScore);

        return userMapper.updateByExampleSelective(user, userExample);
    }

    //登录加经验
    @Override
    public int loginAddExp(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        int oldExpValue = user.getExpValue();
        int newExpValue = oldExpValue + UserContants.LOGIN_TO_ADD_EXP;
        user.setExpValue(newExpValue);
        //增加登录经验
        int returnExp = userMapper.updateByExampleSelective(user, userExample);
        //判断等级
        changeAndReturnLevel(user);
        //增加登录经验
        return returnExp;
    }

    //购买商品加经验（放在微信回调）
    @Override
    public int buyAddExp(User user, BigDecimal goods_price) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        //增加购买经验和价格一半经验值
        int oldExpValue = user.getExpValue();
        int halfOfPriceScore = goods_price.intValue() / UserContants.GOODS_TO_USER_EXP_BASE;
        int newExpValue = oldExpValue + UserContants.SUCCESS_BUY_GOODS_ADD_EXP + halfOfPriceScore;
        user.setExpValue(newExpValue);
        int returnExp = userMapper.updateByExampleSelective(user, userExample);
        //判断等级
        changeAndReturnLevel(user);
        return returnExp;
    }

    //根据经验值来修改等级
    @Override
    public int changeAndReturnLevel(User user) {
        int expValue = user.getExpValue();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        if (user.getUserLevel() >= UserContants.USER_LEVEL_3) {
            return UserContants.USER_LEVEL_3;
        } else if (expValue > UserContants.USER_LEVEL_3_EXP) {
            user.setUserLevel(UserContants.USER_LEVEL_3);
            userMapper.updateByExampleSelective(user, userExample);
            return UserContants.USER_LEVEL_3;
        } else if (expValue > UserContants.USER_LEVEL_2_EXP) {
            user.setUserLevel(UserContants.USER_LEVEL_2);
            userMapper.updateByExampleSelective(user, userExample);
            return UserContants.USER_LEVEL_2;
        } else if (expValue > UserContants.USER_LEVEL_1_EXP) {
            user.setUserLevel(UserContants.USER_LEVEL_1);
            userMapper.updateByExampleSelective(user, userExample);
            return UserContants.USER_LEVEL_1;
        } else {
            return UserContants.USER_LEVEL_0;
        }
    }


    //becomeVip(放入微信支付成功回调函数里)增加30天
    @Override
    public int buyVip(User user) {


        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        user.setVipStartTime(timeUtil.getTomorrow(NumberContants.BASE_NUMBER_ONE));
        user.setVipEndTime(timeUtil.addDays(DateContants.THIRTY_DAYS));
        if (userMapper.updateByExampleSelective(user, userExample) == NumberContants.BASE_NUMBER_ZERO) {
            return NumberContants.BASE_NUMBER_ZERO;
        }
        return becomeVipStatus(user);
    }

    //判断是否是超级会员(登录时使用)
    @Override
    public boolean isVip(User user) {
        Date startDate = user.getVipStartTime();
        Date endDate = user.getVipEndTime();
        Date nowTime = new Date();
        int resTime = nowTime.compareTo(startDate);
        int reeTime = endDate.compareTo(nowTime);

        if (resTime >= NumberContants.BASE_NUMBER_ZERO) {
            if (reeTime >= NumberContants.BASE_NUMBER_ZERO) {
                return true;
            }
            return false;
        } else {
            cannelVipStatus(user);
            return false;
        }
    }

    //用id查找用户
    @Override
    public User search(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //修改个人信息只能修改一次
    @Override
    @Transactional
    public int changePersonInfo(User user) {
        //判断是否修改过
        if (user.getBirthIsChange() == UserContants.PERSON_INFO_HAVED_CHANGED) {
            return NumberContants.BASE_NUMBER_ZERO;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        //修改个人信息
        if (userMapper.updateByExampleSelective(user, userExample) != NumberContants.BASE_NUMBER_ZERO) {
            //增加修改状态
            user.setBirthIsChange(UserContants.PERSON_INFO_HAVED_CHANGED);
            if (userMapper.updateByExampleSelective(user, userExample) != NumberContants.BASE_NUMBER_ZERO) {
                return NumberContants.BASE_NUMBER_ONE;
            } else {
                throw new RuntimeException();
            }
        }
        return NumberContants.BASE_NUMBER_ZERO;
    }

    //判断是否是生日
    @Override
    public boolean isBirthDay(User user) {


        Date birth = user.getBirthday();
        Calendar birthTime = Calendar.getInstance();
        birthTime.setTime(birth);
        //生日月，注意加 1
        int birth_month = birthTime.get(Calendar.MONTH) + NumberContants.BASE_NUMBER_ONE;
        //生日天
        int birth_day = birthTime.get(Calendar.DATE);

        Calendar nowTime = Calendar.getInstance();
        //当月
        int now_month = birthTime.get(Calendar.MONTH) + NumberContants.BASE_NUMBER_ONE;
        //当天
        int now_day = birthTime.get(Calendar.DATE);
        if (birth_month == now_month && birth_day == now_day) {
            return true;
        }


        return false;
    }

    //生日增加一天会员时间
    @Override
    public int becomeTempVip(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        user.setVipStartTime(timeUtil.getTomorrow(NumberContants.BASE_NUMBER_ZERO));
        user.setVipEndTime(timeUtil.getTomorrow(NumberContants.BASE_NUMBER_ONE));
        if (userMapper.updateByExampleSelective(user, userExample) == NumberContants.BASE_NUMBER_ZERO) {
            return 0;
        }
        return becomeVipStatus(user);
    }


    @Override
    public int becomeVipStatus(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        user.setVip(NumberContants.BASE_NUMBER_ONE);

        return userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public int cannelVipStatus(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        user.setVip(NumberContants.BASE_NUMBER_ZERO);
        return userMapper.updateByExampleSelective(user, userExample);
    }


    /**
     * Created by GY on 2019/10/17 15:22
     */
    @Override
    public int register(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public boolean isExit(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenidEqualTo(user.getOpenid());
        List<User> users = userMapper.selectByExample(userExample);

        if (users.isEmpty()) {
            return false;
        }
        return true;
    }


    @Override
    public List<User> getAllUser(User user) {
        List<User> list = userMapper.getAllUser(user);
        return list;
    }

    @Override
    public List<User> userPhone(User user) {
        return userMapper.userPhone(user);
    }

    @Override
    public User login(String userName, String userPassword) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if (!CollectionUtils.isEmpty(users)) {
            if (userPassword.equals(users.get(0).getUserPassword())) {
                return users.get(0);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public String findAddr(User user) {
        return userMapper.findAddr(user);
    }

    @Override
    public String findIpAddr(String userName) {
        return userMapper.findIpAddr(userName);
    }
}
