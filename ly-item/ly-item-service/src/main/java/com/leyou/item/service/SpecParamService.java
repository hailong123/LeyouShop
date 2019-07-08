package com.leyou.item.service;

import com.leyou.item.pojo.SpecParam;

import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-22 10:08
 * @Description:
 */
public interface SpecParamService {

    //查询商品
    List<SpecParam> queryParamList(Long gid, Long cid, Boolean searching);

    //保存商品
    void saveParam(SpecParam specParam);

    //删除商品
    void deleteParam(Long id);

    //编辑商品
    void updateParam(SpecParam specParam);
}
