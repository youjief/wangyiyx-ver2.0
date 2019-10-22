package com.wyyx.cn.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyyx.cn.consumer.config.custom.CurrentUser;
import com.wyyx.cn.consumer.config.custom.LoginRequired;
import com.wyyx.cn.consumer.contants.GoodsContants;
import com.wyyx.cn.consumer.contants.NumberContants;
import com.wyyx.cn.consumer.untils.IpAdrressUtil;
import com.wyyx.cn.consumer.untils.result.ReturnResult;
import com.wyyx.cn.consumer.untils.result.ReturnResultUtils;
import com.wyyx.cn.consumer.vo.GoodsVo;
import com.wyyx.cn.consumer.vo.OrderVo;
import com.wyyx.cn.consumer.vo.UserVo;
import com.wyyx.cn.consumer.vo.goodsofreturn.GoodsResult;
import com.wyyx.cn.provider.model.Cart;
import com.wyyx.cn.provider.model.Goods;
import com.wyyx.cn.provider.model.Order;
import com.wyyx.cn.provider.model.User;
import com.wyyx.cn.provider.service.FactionService;
import com.wyyx.cn.provider.service.GoodsService;
import com.wyyx.cn.provider.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.BreakIterator;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/18
 * Time: 14:46
 * Description: No Description
 * <p>
 * Created by GY on 2019/10/18 11:06
 * <p>
 * Created by GY on 2019/10/18 11:06
 * <p>
 * Created by GY on 2019/10/18 11:06
 * <p>
 * Created by GY on 2019/10/18 11:06
 * <p>
 * Created by GY on 2019/10/18 11:06
 */

/**
 * Created by GY on 2019/10/18 11:06
 */

/**
 * Created with IntelliJ IDEA.
 * cc
 * 2019/10/17
 * 16:04
 */
@Api(tags = "GoodsTest")
@RestController
@RequestMapping(value = "/goodstest")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @Reference
    private UserService userService;

    /**
     * Created with IntelliJ IDEA.
     * User: 91917
     * Date: 2019/10/18
     * Time: 14:46
     * Description: No Description
     */


    @ApiOperation("默认搜索框")
    @GetMapping(value = "/defaultBox")
    public String defaultBox( HttpServletRequest request) {
        String  ip = IpAdrressUtil.getIpAddr(request);
        return goodsService.defaultBox(ip);
    }


    @ApiOperation("id查详情")
    @GetMapping(value = "/queryByid")
    //id查详情
    public Goods queryByid(Long goods_id) {
        return goodsService.queryByid(goods_id);
    }

    @ApiOperation("积分抵扣")
    @GetMapping(value = "/deduction")
    //积分抵扣
    public void deduction(Long goods_id, User user) {

        goodsService.deduction(goods_id, user);
    }

    @ApiOperation("订单打分转商品好评")
    @GetMapping(value = "/ScoresToPraise")
    //订单打分转商品好评
    public int ScoresToPraise(Goods goods) {
        return goodsService.ScoresToPraise(goods);
    }

    @ApiOperation("返回详情")
    @GetMapping(value = "/returnxiangqing")
    public GoodsResult getOneGoods(Long goods_id, User user, boolean flag) {
        Goods goods = goodsService.queryByid(goods_id);
        GoodsVo goodsVo = new GoodsVo();
        GoodsResult goodsResult = new GoodsResult();
        BeanUtils.copyProperties(goods, goodsResult);

        //会员价格
        if (user.getVip() == NumberContants.BASE_NUMBER_ONE) {
            double vipPrice = goods.getGoodsPrice().doubleValue() * 0.98;
            goodsResult.setScores(user.getUserScores());

            if (flag) {
                double newPrice = vipPrice - user.getUserScores() / 100;
                goodsResult.setGoodsPrice(BigDecimal.valueOf(newPrice));

            } else {
                goodsResult.setGoodsPrice(BigDecimal.valueOf(vipPrice));
            }

        } else {
            goodsResult.setGoodsPrice(goods.getGoodsPrice());
        }


        //库存转msg
        double goods_surplus = goods.getGoodsStock() / (goods.getGoodsStock() + goods.getGoodsSales());
        if (goods_surplus > NumberContants.BASE_HALF_OF_ONE) {
            goodsVo.setMsg(GoodsContants.GOODS_IS_ENOUGH_STOCK);
        } else if (goods_surplus > NumberContants.BASE_NUMBER_ZERO) {
            goodsVo.setMsg(GoodsContants.GOODS_NOT_ENOGH_STOCK);
        } else {
            goodsVo.setMsg(GoodsContants.GOODS_IS_ENOUGH_STOCK);
        }
        Map<String, String> m = new HashMap<String, String>();
        m.put(goods.getGoodsStock().toString(), goodsVo.getMsg());
        goodsResult.setStockToMsg(m);

        //好评转颜色
        double goodPraise = goods.getGoodsPraise();
        if (goodPraise > GoodsContants.GOODS_PRAISE_FOR_COLOR_RED_NUM) {
            goodsVo.setColor(GoodsContants.GOODS_PRAISE_FOR_COLOR_RED);
        } else if (goodPraise > GoodsContants.GOODS_PRAISE_FOR_COLOR_GREEN_NUM) {
            goodsVo.setColor(GoodsContants.GOODS_PRAISE_FOR_COLOR_GREEN);
        } else if (goodPraise > GoodsContants.GOODS_PRAISE_FOR_COLOR_BLACK_NUM) {
            goodsVo.setColor(GoodsContants.GOODS_PRAISE_FOR_COLOR_WHITE);
        } else {
            goodsVo.setColor(GoodsContants.GOODS_PRAISE_FOR_COLOR_BLACK);
        }
        Map<String, String> n = new HashMap<String, String>();
        n.put(goods.getGoodsPraise().toString(), goodsVo.getColor());
        goodsResult.setPraiseToClor(n);

        return goodsResult;


    }

    @LoginRequired
    @ApiOperation("wxToken测试")
    @GetMapping(value = "/tt")
    public Void testLogin() {
        if (1 == 1) {
            throw new RuntimeException();
        }
        return null;
    }


    /**
     * Created by GY on 2019/10/18 11:06
     */
    @ApiOperation("商品")
    @GetMapping(value = "/findGoodId")
    public ReturnResult findGoodId(@ApiParam(value = "商品id", required = true) @RequestParam(value = "goodsId") Integer goodsId) {

        return ReturnResultUtils.returnSuccess(goodsService.findGodds(goodsId));

    }

    @LoginRequired
    @ApiOperation("结算")
    @GetMapping(value = "/settelMoney")
    public ReturnResult settelMoney(@ApiParam(value = "用户名登录", required = false) @RequestParam(value = "userName") Long userId,
                                    @CurrentUser UserVo userVo) {
        User users = new User();
        users.getUserId();
        double allSum = 0;
        double sum = goodsService.findSum(userVo.getGoodsSumprice());
        int level = goodsService.findLevel(userVo.getUserLevel());
        int vip = goodsService.findVip(userVo.getVip());
        if (level == 4) {
            if (vip == 1) {
                allSum = sum - 5;
            }
            if (vip == 0) {
                allSum = sum;
            }
        }
        if (level < 4) {
            allSum = sum + 10;
        }
        return ReturnResultUtils.returnSuccess(allSum);
    }

    @LoginRequired
    @ApiOperation("生成订单")
    @GetMapping(value = "/OrderPay")
    public void OrderPay(Cart cart) {
        goodsService.findOrder(cart);
        Order order = new Order();
        User user = new User();
        userService.findAddr(user);
        order.setUserId(cart.getUserId());
        order.setGoodsName(cart.getGoodsName());
        order.setGoodsCount(cart.getGoodsNumbers());
        order.setGoodsPrice(cart.getGoodsPrice());
        order.setDeliveryAddr(user.getUserAddr());
        goodsService.insterOrder(order);
        ReturnResultUtils.returnSuccess();
    }


    /**
     * Created with IntelliJ IDEA.
     * cc
     * 2019/10/17
     * 16:04
     */

    @Reference
    private FactionService factionService;


    @ApiOperation("进入首页出现所有商品")
    @GetMapping(value = "/getgoods")
    public List<Goods> Query(int goodsKind) {
        return factionService.qureyAll(goodsKind);
    }

    @ApiOperation("模糊查询")
    @GetMapping(value = "/Order1")
    public List<Goods> mohu(String goodsname) {
        return factionService.mohuQuery(goodsname);
    }

    @ApiOperation("默认销量")
    @GetMapping(value = "/Order2")
    public Goods moren() {
        return factionService.qureyMoren();
    }

    @ApiOperation("默认上新")
    @GetMapping(value = "/Order3")
    public Goods newgoods() {
        return factionService.newGoods();
    }


}
