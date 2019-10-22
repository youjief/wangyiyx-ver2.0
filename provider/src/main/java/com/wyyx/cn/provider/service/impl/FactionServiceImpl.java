package com.wyyx.cn.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyyx.cn.provider.mapper.GoodsMapper;
import com.wyyx.cn.provider.model.Goods;
import com.wyyx.cn.provider.model.GoodsExample;
import com.wyyx.cn.provider.service.FactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * cc
 * 2019/10/17
 * 16:10
 */

@Service
public class FactionServiceImpl implements FactionService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> qureyAll(int goodsKind) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsKindEqualTo(goodsKind);
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<Goods> mohuQuery(String goodsname) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andGoodsNameLike('%' + goodsname + '%');
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public Goods qureyMoren() {
        String orderByClasuse = "goods_sales DESC";
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(orderByClasuse);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        Goods goods1 = goods.get(0);
        return goods1;
    }

    @Override
    public Goods newGoods() {
        String orderByClasuse = "goods_up_time DESC";
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(orderByClasuse);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        Goods goods1 = goods.get(0);
        return goods1;
    }


}
