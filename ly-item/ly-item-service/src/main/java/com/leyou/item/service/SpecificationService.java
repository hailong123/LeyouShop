package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;

import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-20 20:52
 * @Description:
 */
public interface SpecificationService {
    List<SpecGroup> queryGroupById(Long cid);
}
