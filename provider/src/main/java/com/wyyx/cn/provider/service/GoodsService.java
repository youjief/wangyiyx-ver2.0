package com.wyyx.cn.provider.service;

import com.wyyx.cn.provider.model.Cart;
import com.wyyx.cn.provider.model.Goods;
import com.wyyx.cn.provider.model.Order;
import com.wyyx.cn.provider.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/18
 * Time: 12:07
 * Description: No Description
 */

/**
 * Created by GY on 2019/10/18 10:44
 */
public interface GoodsService {
    //id查详情
    Goods queryByid(Long goods_id);

    //积分抵扣
    void deduction(Long goods_id, User user);

    //订单打分转商品好评
    int ScoresToPraise(Goods goods);


    /**
     * Created by GY on 2019/10/18 10:44
     */
    List<Goods> findGodds(int goodsId);

    //查用户等级
    int findLevel(Integer userLevel);

    //查用户是否为超级会员
    int findVip(int vip);

    //查询总价
    double findSum(double goodsSumprice);

    //查所有购物车
    List<Cart> findOrder(Cart cart);

    //生成订单
    int insterOrder(Order order);


    //默认搜索框
    String defaultBox(String ip);


}
