package com.leyou.item.service.Impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-11 15:36
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryListByPid(Long id) {

        Category t = new Category();

        t.setParentId(id);

        //查询条件 mapper 会把对象中的非空属性作为查询条件
        List<Category> list = categoryMapper.select(t);

        //判断结果是否为空
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }

        return list;
    }

    @Override
    public List<Category> queryCategoryListByIds(List<Long> ids) {

        List<Category> categories = categoryMapper.selectByIdList(ids);

        if (CollectionUtils.isEmpty(categories)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }

        return categories;
    }
}
