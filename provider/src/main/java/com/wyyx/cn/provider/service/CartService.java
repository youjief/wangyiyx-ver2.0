package com.wyyx.cn.provider.service;

import com.wyyx.cn.provider.model.Cart;
import com.wyyx.cn.provider.model.User;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/20
 * Time: 16:28
 * Description: No Description
 */
/**
 * Created with IntelliJ IDEA.
 * cc
 * 2019/10/18
 * 17:36
 */
public interface CartService {
    //增加购物车
    int addIntoCart(Cart cart);

    /**
     * Created with IntelliJ IDEA.
     * cc
     * 2019/10/18
     * 17:36
     */

    //查询购物车所有商品
    List<Cart> qureyAll(User user);
    //物理删除购物车商品
    int deleteAll(long cartId);
    //修改购物车
    int sumAll(Cart cart);

}
