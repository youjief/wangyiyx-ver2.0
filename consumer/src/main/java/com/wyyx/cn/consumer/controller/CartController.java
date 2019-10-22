package com.wyyx.cn.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyyx.cn.consumer.config.custom.CurrentUser;
import com.wyyx.cn.consumer.untils.redis.RedisUtils;
import com.wyyx.cn.consumer.vo.SumUserVo;
import com.wyyx.cn.provider.model.Cart;
import com.wyyx.cn.provider.model.Goods;
import com.wyyx.cn.provider.model.User;
import com.wyyx.cn.provider.service.CartService;
import com.wyyx.cn.provider.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/20
 * Time: 16:36
 * Description: No Description
 */

/**
 * Created with IntelliJ IDEA.
 * cc
 * 2019/10/18
 * 17:32
 */
@Api(tags = "购物车操作")
@RestController
@RequestMapping(value = "/cart")
public class CartController {


    @Reference
    private CartService cartService;
    @Autowired
    private RedisUtils redisUtils;
    @Reference
    private GoodsService goodsService;


    private Jedis jedis = null;

    public void init() {
        jedis = new Jedis("192.168.1.139", 6379);
    }



    @ApiOperation(value = "增加购物车redis")
    @GetMapping(value = "/addredis")
    // 添加商品到购物车
    public void AddItemToCart(String goods_id, int numbers) {

        init();

        jedis.hset("cart:user_local", goods_id, numbers + "");

        jedis.close();
    }

    // 遍历购物车信息加入mysql
    public void GetCartInfo() {
        Map<String, String> cart = jedis.hgetAll("cart:user_local");
        Set<Map.Entry<String, String>> entrySet = cart.entrySet();
        for (Map.Entry<String, String> ent : entrySet) {
            Cart cart1 = new Cart();
            Goods goods = goodsService.queryByid(Long.valueOf(ent.getKey()));
            BeanUtils.copyProperties(goods, cart);
            cart1.setGoodsNumbers(Integer.valueOf(ent.getValue()));
            cartService.addIntoCart(cart1);

        }
        jedis.close();
    }

    // 更改购物车
    public void editCart(String goods_id, int numbers) {
        init();
        jedis.hincrBy("cart:user_local", goods_id, 1);
        jedis.close();
    }


    // 从购物车中删除商品项
    public void delItemFromCart(String goods_id) {
        init();
        jedis.hdel("cart:user_l init();ocal", goods_id);
        jedis.close();
    }


    @ApiOperation(value = "增加购物车")
    @GetMapping(value = "/add")
    public boolean add(Goods goods, int numbers, @CurrentUser SumUserVo sumUserVo) {
        //当前没登录的话存redis
        if (sumUserVo == null) {
            AddItemToCart(goods.getGoodsId().toString(), numbers);
            return true;
        } else //增加进mysql
        {
            Cart cart = new Cart();
            BeanUtils.copyProperties(goods, cart);
            cart.setGoodsNumbers(numbers);
            cartService.addIntoCart(cart);
            return true;
        }

    }


    /**
     * Created with IntelliJ IDEA.
     * cc
     * 2019/10/18
     * 17:32
     */
    @ApiOperation("展示购物车信息")
    @GetMapping("getmap")
    public List<Cart> Query(User user) {
        return cartService.qureyAll(user);
    }

    @ApiOperation("物理删除购物车商品")
    @GetMapping("delete")
    public int delete(long cartId) {
        return cartService.deleteAll(cartId);
    }

    @ApiOperation("修改购物车")
    @GetMapping("sum")
    public int sumAll(Cart cart) {

        return cartService.sumAll(cart);
    }
}


