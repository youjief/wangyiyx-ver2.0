package com.wyyx.cn.provider.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
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

    public Integer getStartItem() {
        return startItem;
    }

    public void setStartItem(Integer startItem) {
        this.startItem = startItem;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getGoodsFreight() {
        return goodsFreight;
    }

    public void setGoodsFreight(String goodsFreight) {
        this.goodsFreight = goodsFreight;
    }

    public Integer getGoodsScores() {
        return goodsScores;
    }

    public void setGoodsScores(Integer goodsScores) {
        this.goodsScores = goodsScores;
    }
}