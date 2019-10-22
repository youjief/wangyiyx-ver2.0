package com.wyyx.cn.provider.mapper;

import com.wyyx.cn.provider.model.Order;
import com.wyyx.cn.provider.model.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    //---------------------------------

    List<Order> getOrderList(Order order);

    List<Order> getOrderByStatus(Order order);

    int statusOrderList(Order order);

    int updateOrderStatus(Order order);

    int delOrder(Order order);

    List<Order> getOrdersByLike(Order order);


    Integer totalByLike(Order order);

}