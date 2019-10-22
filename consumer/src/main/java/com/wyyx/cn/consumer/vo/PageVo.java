package com.wyyx.cn.consumer.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jt on 2019/10/18 8:49
 */
@Data
public class PageVo implements Serializable {
    private static final long serialVersionUID = 7553158974876594599L;
    //当前页
    private int curPage;
    //起始条数
    private int startItem;
    //每页大小
    private int pageSize;

    public void setCurPage(int curPage) {
        if (curPage > 0) {
            this.curPage = curPage;
        } else {
            this.curPage = 1;
        }
    }

    public Integer getStartItem() {
        return startItem = (this.curPage - 1) * pageSize;
    }
}
