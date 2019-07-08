package com.leyou.item.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.*;
import com.leyou.item.pojo.*;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: seadragon
 * @Date: 2019-06-25 20:42
 * @Description:
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SpuDetailMapper spuDetailMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private StockMapper stockMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BrandService brandService;

    @Override
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {

        //分页
        PageHelper.startPage(page, rows);

        //过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //搜索字段过滤
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }

        //上下架过滤
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }

        //排序
        example.setOrderByClause("last_update_time DESC");

        //查询
        List<Spu> spus = spuMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(spus)) {
            throw new LyException(ExceptionEnum.GOODS_QUERY_NOT_FOND);
        }

        //解析分类和品牌的名称
        loadCategoryAndBrandName(spus);

        //解析分页结果
        PageInfo<Spu> info = new PageInfo<>(spus);

        return new PageResult<>(info.getTotal(),spus);
    }

    //加载分类
    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            //处理分类名称
           List<String> names = categoryService.queryCategoryListByIds(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3()))
                   .stream()
                   .map(Category::getName)
                   .collect(Collectors.toList());
           spu.setCname(StringUtils.join(names,"/"));
            //处理品牌名称
            spu.setBname(brandService.queryBrandById(spu.getBrandId()).getName());
        }
    }

    //保存商品
    @Override
    @Transactional
    public void saveGoods(Spu spu) {
        //新增spu
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setSaleable(true);
        spu.setValid(false);

        int count = spuMapper.insert(spu);

       if (count != 1) {
            throw new LyException(ExceptionEnum.GOODS_CREATE_ERROR);
       }

        //新增detail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());

       spuDetailMapper.insert(spuDetail);

       List<Stock> stockList = new ArrayList<>();

       //新增sku
       List<Sku> list = spu.getSkus();

        for (Sku sku: list
             ) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());

            count = skuMapper.insert(sku);

            if (count != 1) {
                throw new LyException(ExceptionEnum.GOODS_CREATE_ERROR);
            }

            //新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());

           stockList.add(stock);
        }

        //批量新增库存
        count = stockMapper.insertList(stockList);

        if (count != stockList.size()) {
            throw new LyException(ExceptionEnum.GOODS_CREATE_ERROR);
        }
    }

    @Override
    public SpuDetail querySpuDetailById (Long spuId) {

        SpuDetail detail = spuDetailMapper.selectByPrimaryKey(spuId);

        if (detail == null) {
            throw new LyException(ExceptionEnum.GOODS_QUERY_ERROR);
        }

        return detail;
    }

    @Override
    public List<Sku> querySkuBySpuId (Long spuId) {

        Sku sku = new Sku();
        sku.setSpuId(spuId);

        List<Sku> skuList = skuMapper.select(sku);

        if (CollectionUtils.isEmpty(skuList)) {
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOND);
        }

        //查询库存
//        for (Sku s:
//             skuList) {
//            Stock stock = stockMapper.selectByPrimaryKey(s.getId());
//            if (stock == null) {
//                throw new LyException(ExceptionEnum.GOODS_STOCK_QUERY_ERROR);
//            }
//
//            s.setStock(stock.getStock());
//        }

        //查询库存
        List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stockList = stockMapper.selectByIdList(ids);

        //
        if (CollectionUtils.isEmpty(stockList)) {
            throw new LyException(ExceptionEnum.GOODS_STOCK_QUERY_ERROR);
        }

        //我们把stock变成一个map,其key是:sku的id 值是库存值
        Map<Long, Integer> stockMap = stockList.stream()
                .collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));

        skuList.forEach(s -> sku.setStock(stockMap.get(s.getId())));

        return skuList;
    }
}
