package com.leyou.item.service.Impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.service.SpecificationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-20 20:52
 * @Description:
 */

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    @Override
    public List<SpecGroup> queryGroupById(Long cid) {

        SpecGroup specGroup = new SpecGroup();

        specGroup.setCid(cid);

        //查询条件
        List<SpecGroup> list = specGroupMapper.select(specGroup);

        //判断查询结果是否存在
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.SPECIFICATION_GROUP_NOT_FOND);
        }

        return list;
    }
}
