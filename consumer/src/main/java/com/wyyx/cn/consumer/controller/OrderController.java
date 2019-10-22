package com.wyyx.cn.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyyx.cn.consumer.config.custom.CurrentUser;
import com.wyyx.cn.consumer.contants.OrderContants;
import com.wyyx.cn.consumer.untils.result.ReturnResult;
import com.wyyx.cn.consumer.untils.result.ReturnResultUtils;
import com.wyyx.cn.consumer.vo.OrderVo;
import com.wyyx.cn.consumer.vo.PageVo;
import com.wyyx.cn.consumer.vo.Pages;
import com.wyyx.cn.consumer.vo.UserVo;
import com.wyyx.cn.provider.model.Order;
import com.wyyx.cn.provider.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jt on 2019/10/17 14:30
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    static String regEx = "[\u4e00-\u9fa5]";
    static Pattern pat = Pattern.compile(regEx);

    /**
     * 判定是否包含中文，包含返回true，反之返回false
     *
     * @param str
     * @return
     */
    public static boolean isContainsChinese(String str) {
        Matcher matcher = pat.matcher(str);
        boolean flag = false;
        if (matcher.find()) {
            flag = true;
        }
        return flag;
    }

    @Reference
    private OrderService orderService;

    /**
     * 根据当前用户id查所有订单，不包括已删除（状态为-1的）订单，
     * 分页，并且按照支付时间降序（DESC）排列
     *
     * @param orderVo
     * @return
     */
    @ApiOperation("全部订单")
    @GetMapping(value = "/getOrders")
    public ReturnResult<Pages> getOrders(@Valid OrderVo orderVo) {


        Order order = new Order();
        BeanUtils.copyProperties(orderVo, order);
        order.setStartItem(orderVo.getPageVo().getStartItem());
        order.setPageSize(orderVo.getPageVo().getPageSize());

        long totalOrderList = orderService.totalOrderList(order);
        if (totalOrderList > 0) {
            List<Order> orders = orderService.getOrders(order);

            Pages pages = new Pages();
            pages.setCurrentPage(orderVo.getPageVo().getCurPage());
            pages.setPagesize(orderVo.getPageVo().getPageSize());
            pages.setCurrList(orders);
            pages.setTotalCount(totalOrderList);

            return ReturnResultUtils.returnSuccess(pages);
        }
        return ReturnResultUtils.returnFail(OrderContants.NO_ORDER_CODE, OrderContants.NO_ORDER_MSG);
    }

    /**
     * 查询待付款、待发货、已收货、待评价、已删除（到回收站）订单
     *
     * @param pageVo
     * @param orderVo
     * @return
     */
    @ApiOperation("根据订单状态查询")
    @GetMapping(value = "/getOrderByStatus")
    public ReturnResult getOrderByStatus(@Valid PageVo pageVo,
                                         @Valid OrderVo orderVo) {
        Order order = new Order();
        BeanUtils.copyProperties(orderVo, order);
        order.setStartItem(orderVo.getPageVo().getStartItem());
        order.setPageSize(orderVo.getPageVo().getPageSize());

        int statusOrderList = orderService.statusOrderList(order);
        if (statusOrderList > 0) {
            List<Order> orderByStatus = orderService.getOrderByStatus(order);
            if (orderByStatus.size() > 0) {
                Pages pages = new Pages();
                pages.setCurrentPage(pageVo.getCurPage());
                pages.setPagesize(pageVo.getPageSize());
                pages.setCurrList(orderByStatus);
                pages.setTotalCount(statusOrderList);
                return ReturnResultUtils.returnSuccess(orderByStatus);
            }
        }
        return ReturnResultUtils.returnFail(OrderContants.NO_ORDER_CODE, OrderContants.NO_ORDER_MSG);
    }

    /**
     * 修改订单状态为已支付、已发货、确认收货、已评价订单，包括逻辑删除订单到回收站
     *
     * @param order
     * @return
     */
    @ApiOperation("修改订单状态")
    @GetMapping(value = "/updateOrderStatusTo")
    public ReturnResult updateOrderStatusTo(@Valid Order order) {
        if (orderService.updateOrderStatus(order)) {
            return ReturnResultUtils.returnSuccess(OrderContants.DO_SUCCESS_MSG);
        }
        return ReturnResultUtils.returnFail(OrderContants.NO_ORDER_CODE, OrderContants.NO_ORDER_MSG);
    }

    /**
     * 彻底从数据库删除订单，不可恢复
     *
     * @param orderId
     * @return
     */
    @ApiOperation(("彻底删除订单"))
    @GetMapping(value = "/delOrder")
    public ReturnResult delOrder(@ApiParam(value = "orderId", required = true) @RequestParam(value = "orderId") Long orderId) {
        if (orderService.delOrder(orderId)) {
            return ReturnResultUtils.returnSuccess(OrderContants.DO_SUCCESS_MSG);
        }
        return ReturnResultUtils.returnFail(OrderContants.DO_FAIL_CODE, OrderContants.DO_FAIL_MSG);
    }

    /**
     * 模糊查询，可根据商品名称或者订单id编号查询
     *
     * @param
     * @return
     */
    @ApiOperation("模糊查询")
    @GetMapping(value = "/getOrdersByLike")
    public ReturnResult getOrdersByLike(@ApiParam(value = "searchKey") @RequestParam(value = "searchKey", required = false) String searchKey,
                                        @Valid Order order) {
        if (null != searchKey) {
            boolean b = isContainsChinese(searchKey);
            if (b) {
                order.setGoodsName(searchKey);
            } else {
                /**
                 * Long.ValueOf("String")返回Long包装类型
                 * Long.parseLong("String")返回long基本数据类型
                 */
                order.setOrderId(Long.valueOf(searchKey));
            }
        }

        List<Order> ordersByLike = orderService.getOrdersByLike(order);
        if (ordersByLike.size() > 0) {
            return ReturnResultUtils.returnSuccess(ordersByLike);
        }
        return ReturnResultUtils.returnFail(OrderContants.DO_FAIL_CODE, OrderContants.DO_FAIL_MSG);
    }

    /**
     * 为订单商品打分，满分10分，
     * 0.5颗星1分，1颗星2分
     * 用户可以不打分，默认为4.5颗星（9分）
     *
     * @param star
     * @param orderId
     * @return
     */
    @ApiOperation("评价商品得分")
    @GetMapping(value = "/evaGoodsScore")
    public ReturnResult evaGoodsScore(@ApiParam(value = "star") @RequestParam(value = "star", required = false) Double star,
                                      @ApiParam(value = "orderId", required = true) @RequestParam(value = "orderId") Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);

        int goodsScore = 9;
        if (null != star && star >= 0.5 && star <= 5) {
            goodsScore = (int) (star * 2);
            order.setGoodsScores(goodsScore);
        } else if (null == star) {
            order.setGoodsScores(goodsScore);
        } else {
            order.setGoodsScores(goodsScore);
        }

        if (orderService.updateOrderStatus(order)) {
            return ReturnResultUtils.returnSuccess(OrderContants.DO_SUCCESS_MSG);
        }
        return ReturnResultUtils.returnFail(OrderContants.DO_FAIL_CODE, OrderContants.DO_FAIL_MSG);
    }

}
