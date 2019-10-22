package com.wyyx.cn.consumer.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by GY on 2019/10/20 15:38
 */
/**
 * Created by jt on 2019/10/18 8:47
 */
@Data
public class OrderVo implements Serializable {

    private PageVo pageVo;
    private Long orderId;

    private Long userId;

    private Long goodsId;

    private String deliveryAddr;

    private String goodsName;

    private Integer goodsCount;

    private Double goodsPrice;

    private String orderChannel;

    private Integer orderStatus;

    private Date createTime;

    private Date payTime;

    private String goodsFreight;

    private Integer goodsScores;

    private Integer startItem;

    private Integer pageSize;
}
