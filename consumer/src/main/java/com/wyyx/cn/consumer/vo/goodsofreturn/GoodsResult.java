package com.wyyx.cn.consumer.vo.goodsofreturn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 91917
 * Date: 2019/10/18
 * Time: 14:01
 * Description: No Description
 */
public class GoodsResult<T> implements Serializable {
    private int scores;

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private BigDecimal goodsPrice;


    private Map<T, T> stockToMsg;
    private Map<T, T> praiseToClor;

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

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Map<T, T> getStockToMsg() {
        return stockToMsg;
    }

    public void setStockToMsg(Map<T, T> stockToMsg) {
        this.stockToMsg = stockToMsg;
    }

    public Map<T, T> getPraiseToClor() {
        return praiseToClor;
    }

    public void setPraiseToClor(Map<T, T> praiseToClor) {
        this.praiseToClor = praiseToClor;
    }
}



