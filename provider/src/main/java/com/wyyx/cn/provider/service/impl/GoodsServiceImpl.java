package com.wyyx.cn.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyyx.cn.provider.contants.base.NumberContants;
import com.wyyx.cn.provider.mapper.CartMapper;
import com.wyyx.cn.provider.mapper.GoodsMapper;
import com.wyyx.cn.provider.mapper.OrderMapper;
import com.wyyx.cn.provider.mapper.UserMapper;
import com.wyyx.cn.provider.model.*;
import com.wyyx.cn.provider.service.GoodsService;
import com.wyyx.cn.provider.until.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Goods queryByid(Long goods_id) {
        Goods goods1 = goodsMapper.selectByPrimaryKey(goods_id);
        return goodsMapper.selectByPrimaryKey(goods_id);
    }

    @Override
    public void deduction(Long goods_id, User user) {
        user.setUserScores(NumberContants.BASE_NUMBER_ZERO);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(user.getUserId());
        userMapper.updateByExampleSelective(user, userExample);
    }


    @Override
    public int ScoresToPraise(Goods goods) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsIdEqualTo(goods.getGoodsId());
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andGoodsIdEqualTo(goods.getGoodsId());
        //返回该商品id的所有订单
        List<Order> orders = orderMapper.selectByExample(orderExample);
        int orderCounts = 0;
        int goodsSc = 0;
        for (Order order : orders) {
            goodsSc = goodsSc + order.getGoodsScores();
            System.out.println(order);
            orderCounts++;
        }
        int sumScores = orderCounts * 10;
        double praiseForGoods = goodsSc / sumScores;
        goods.setGoodsPraise(praiseForGoods);
        return goodsMapper.updateByExampleSelective(goods, goodsExample);
    }

    /**
     * Created by GY on 2019/10/18 10:44
     */


    @Override
    public List<Goods> findGodds(int goodsId) {
        return goodsMapper.findGoods(goodsId);
    }

    //查用户等级
    @Override
    public int findLevel(Integer userLevel) {
        return userMapper.findLevel(userLevel);
    }

    //查用户是否为超级会员
    @Override
    public int findVip(int vip) {
        return userMapper.findVip(vip);
    }

    @Override
    public double findSum(double goodsSumprice) {
        return cartMapper.findSum(goodsSumprice);
    }

    @Override
    public List<Cart> findOrder(Cart cart) {
        return cartMapper.findOrder(cart);
    }

    @Override
    public int insterOrder(Order order) {
        return orderMapper.insert(order);
    }

    int i = 1;

    @Override
    public String defaultBox(String ip) {


        redisUtils.set(ip, 1);
        i = i + Integer.valueOf(redisUtils.get(ip).toString());
        redisUtils.set(ip, i);

        GoodsExample goodsExample = new GoodsExample();
        Long count = goodsMapper.countByExample(goodsExample);


        int newcount = count.intValue();
        int choose = i % 2;
        int ex = (i + newcount) % newcount;

        switch (choose) {
            case 0:
                String orderByClasuseMostSales = "goods_sales DESC";
                GoodsExample goodsExampleMostSales = new GoodsExample();
                goodsExampleMostSales.setOrderByClause(orderByClasuseMostSales);
                List<Goods> goodsMostSales = goodsMapper.selectByExample(goodsExampleMostSales);
                return goodsMostSales.get(ex).getGoodsName();


            case 1:
                String orderByClasuseNew = "goods_up_time DESC";
                GoodsExample goodsExampleNew = new GoodsExample();
                goodsExampleNew.setOrderByClause(orderByClasuseNew);
                List<Goods> goodsNew = goodsMapper.selectByExample(goodsExampleNew);
                return goodsNew.get(ex).getGoodsName();
        }


        return null;
    }

}
