package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: seadragon
 * @Date: 2019-06-09 16:55
 * @Description:
 */
public interface CategoryMapper extends Mapper<Category> , IdListMapper<Category,Long> {
}
