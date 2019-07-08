package com.leyou.common.vo;

import lombok.*;

import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-10 20:48
 * @Description:
 */

@Data
public class PageResult<T> {
    private Long total;//总条数
    private Integer totalPage;//总页数
    private List<T> items;//当前页数据

    public PageResult(){}

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total     = total;
        this.items     = items;
        this.totalPage = totalPage;
    }
}
