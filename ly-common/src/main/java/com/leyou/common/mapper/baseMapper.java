package com.leyou.common.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;

/**
 * @Auther: seadragon
 * @Date: 2019-06-27 20:35
 * @Description:
 */

@RegisterMapper
public interface baseMapper<T> extends Mapper<T>, IdListMapper<T,Long>, InsertListMapper<T> {
}
