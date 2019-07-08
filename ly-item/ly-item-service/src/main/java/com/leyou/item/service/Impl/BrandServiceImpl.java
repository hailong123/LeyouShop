package com.leyou.item.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-11 15:41
 * @Description:
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {

        //分页
        PageHelper.startPage(page, rows);

        //过滤
        Example example = new Example(Brand.class);

        if (StringUtils.isNoneBlank(key)) {
            //过滤条件
            example.createCriteria()
                    .orLike("name","%"+key+"%")
                    .orEqualTo("letter",key.toUpperCase());
        }

        //排序
        if (StringUtils.isNoneBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC":" ASC");
            example.setOrderByClause(orderByClause);
        }

        //查询
        List<Brand> list = brandMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOND);
        }

        PageInfo<Brand> info = new  PageInfo<>(list);

        return new PageResult<>(info.getTotal(),list);
    }


    //新增品牌
    @Transactional
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增品牌
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if (count != 1) {
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }

        //新增中间表
        for (Long cid: cids
             ) {
            count = brandMapper.insertCategoryBrand(cid,brand.getId());
           if (count != 1) {
               throw new LyException(ExceptionEnum.BRAND_INSERT_ERROR);
           }
        }
    }

    //选择商品
    @Override
    public List<Brand> queryBrandByCid(Long cid) {

        List<Brand> list = brandMapper.queryByCategoryId(cid);

        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.BRAND_SELECT_ERROR);
        }

        return list;
    }


    @Override
    public Brand queryBrandById(Long id) {
       Brand brand = brandMapper.selectByPrimaryKey(id);
       if (brand == null) {
           throw new LyException(ExceptionEnum.BRAND_NOT_FOND);
       }

       return brand;
    }
}
