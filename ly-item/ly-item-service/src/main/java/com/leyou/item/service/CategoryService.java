package com.leyou.item.service;

import java.util.List;
import com.leyou.item.pojo.Category;

/**
 * @Auther: seadragon
 * @Date: 2019-06-09 16:56
 * @Description:
 */

public interface CategoryService {

    //获取分类数据
     List<Category> queryCategoryListByPid(Long id);

     //
    List<Category> queryCategoryListByIds(List<Long> ids);
}
