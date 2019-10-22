package com.wyyx.cn.provider.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private Long cartId;

    private Long userId;

    private Long goodsId;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private String goodsDetail;

    private Double goodsPrice;

    private Integer goodsNumbers;

    private Double goodsSumprice;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNumbers() {
        return goodsNumbers;
    }

    public void setGoodsNumbers(Integer goodsNumbers) {
        this.goodsNumbers = goodsNumbers;
    }

    public Double getGoodsSumprice() {
        return goodsSumprice;
    }

    public void setGoodsSumprice(Double goodsSumprice) {
        this.goodsSumprice = goodsSumprice;
    }
}