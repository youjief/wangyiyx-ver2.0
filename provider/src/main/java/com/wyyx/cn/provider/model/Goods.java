package com.wyyx.cn.provider.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Goods implements Serializable {
    private Long goodsId;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private Integer goodsKind;

    private BigDecimal goodsPrice;

    private Integer goodsStock;

    private Double goodsPraise;

    private String goodsIsSeckill;

    private Integer goodsSales;

    private Date goodsUpTime;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public Integer getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(Integer goodsKind) {
        this.goodsKind = goodsKind;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Double getGoodsPraise() {
        return goodsPraise;
    }

    public void setGoodsPraise(Double goodsPraise) {
        this.goodsPraise = goodsPraise;
    }

    public String getGoodsIsSeckill() {
        return goodsIsSeckill;
    }

    public void setGoodsIsSeckill(String goodsIsSeckill) {
        this.goodsIsSeckill = goodsIsSeckill;
    }

    public Integer getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(Integer goodsSales) {
        this.goodsSales = goodsSales;
    }

    public Date getGoodsUpTime() {
        return goodsUpTime;
    }

    public void setGoodsUpTime(Date goodsUpTime) {
        this.goodsUpTime = goodsUpTime;
    }
}