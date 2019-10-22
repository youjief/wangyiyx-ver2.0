package com.wyyx.cn.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyyx.cn.provider.mapper.CartMapper;
import com.wyyx.cn.provider.model.Cart;
import com.wyyx.cn.provider.model.CartExample;
import com.wyyx.cn.provider.model.User;
import com.wyyx.cn.provider.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

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
 * 17:33
 */

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;


    @Override
    public int addIntoCart(Cart cart) {
        return cartMapper.insertSelective(cart);
    }

    /**
     * Created with IntelliJ IDEA.
     * cc
     * 2019/10/18
     * 17:33
     */

    @Override
    public List<Cart> qureyAll(User user) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(user.getUserId());
        return cartMapper.selectByExample(cartExample);

    }

    @Override
    public int deleteAll(long cartId) {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andCartIdEqualTo(cartId);
        return cartMapper.deleteByExample(cartExample);
    }

    @Override
    public int sumAll(Cart cart) {
        cart.setGoodsSumprice(cart.getGoodsPrice() * cart.getGoodsNumbers());
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andCartIdEqualTo(cart.getCartId());
        int a = cartMapper.updateByExampleSelective(cart, cartExample);
        return cartMapper.updateByExampleSelective(cart, cartExample);
    }
}
