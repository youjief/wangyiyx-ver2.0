package com.wyyx.cn.provider.service;


import com.wyyx.cn.provider.model.Goods;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * cc
 * 2019/10/17
 * 16:06
 */
public interface FactionService {
    //根据种类查
    List<Goods> qureyAll(int goodsKind);
    //模糊查询
    List<Goods> mohuQuery(String goodsname);
    //默认火爆商品
    Goods qureyMoren();
    //最新产品
    Goods newGoods();



}
