package com.wyyx.cn.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyyx.cn.provider.mapper.OrderMapper;
import com.wyyx.cn.provider.model.Order;
import com.wyyx.cn.provider.model.OrderExample;
import com.wyyx.cn.provider.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 2019/10/17 18:48
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询全部订单，并根据支付时间降序排列；
     * 排除已删除（到回收站的）订单，限制条件写在了SQL语句里
     *
     * @param order
     * @return
     */
    @Override
    public List<Order> getOrders(Order order) {
        String orderByClause = "pay_time DESC";
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUserIdEqualTo(order.getUserId());
        orderExample.setOrderByClause(orderByClause);

        List<Order> orderList = orderMapper.getOrderList(order);
        if (orderList.size() > 0) {
            return orderList;
        }
        return null;
    }


    @Override
    public long totalOrderList(Order order) {
        List<String> list = new ArrayList<>();
        list.add("-1");
        OrderExample orderExample = new OrderExample();
//        orderExample.createCriteria().andUserIdEqualTo(order.getUserId()).andOrderStatusNotIn(list);
        return orderMapper.countByExample(orderExample);
    }

    @Override
    public List<Order> getOrderByStatus(Order order) {
        List<Order> orderList = orderMapper.getOrderByStatus(order);
        if (orderList.size() > 0) {
            return orderList;
        }
        return null;
    }

    @Override
    public int statusOrderList(Order order) {
        return orderMapper.statusOrderList(order);
    }

    @Override
    public boolean updateOrderStatus(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order) == 1 ? true : false;
    }

    @Override
    public boolean delOrder(Long orderId) {
        return orderMapper.deleteByPrimaryKey(orderId) == 1 ? true : false;
    }

    @Override
    public List<Order> getOrdersByLike(Order order) {
        return orderMapper.getOrdersByLike(order);
    }

    @Override
    public Integer totalByLike(Order order) {
        return orderMapper.totalByLike(order);
    }

}
