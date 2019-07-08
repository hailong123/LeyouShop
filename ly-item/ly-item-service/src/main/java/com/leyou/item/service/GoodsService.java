package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-25 20:41
 * @Description:
 */
public interface GoodsService {

    PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);

    void saveGoods(Spu spu);

    SpuDetail querySpuDetailById(Long id);

    List<Sku> querySkuBySpuId(Long spuId);
}
