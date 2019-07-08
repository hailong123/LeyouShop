package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-10 20:41
 * @Description:
 */

public interface BrandService {
    //查询页面
    PageResult<Brand> queryBrandByPage(Integer page,
                                       Integer rows,
                                       String sortBy,
                                       Boolean desc,
                                       String key);

    void saveBrand(Brand brand, List<Long> cids);

    //查询品牌
    List<Brand> queryBrandByCid(Long cid);

    //根据id进行查询排行
    Brand queryBrandById(Long id);
}
