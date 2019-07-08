package com.leyou.item.service.Impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecParamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-22 10:09
 * @Description:
 */

@Service
public class SpecParamServiceImpl implements SpecParamService {
    @Resource
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecParam> queryParamList(Long gid, Long cid, Boolean searching) {

        SpecParam specParam = new SpecParam();

        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGroupId(gid);

        List<SpecParam> list = specParamMapper.select(specParam);

        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.PARAM_NOT_FOND);
        }

        return list;
    }

    //保存商品
    @Transactional
    @Override
    public void saveParam(SpecParam specParam) {

        //设置自增ID
        specParam.setId(null);

        int count = specParamMapper.insert(specParam);

        if (count != 1) {
            throw new LyException(ExceptionEnum.PARAM_INSERT_ERROR);
        }
    }

    //删除商品
    @Override
    public void deleteParam(Long id) {

        SpecParam specParam = new SpecParam();

        specParam.setId(id);

        int count = specParamMapper.delete(specParam);

        if (count != 1) {
            throw new LyException(ExceptionEnum.PARAM_DELETE_ERROR);
        }
    }

    //编辑商品
    @Override
    public void updateParam(SpecParam specParam) {
        int count = specParamMapper.updateByPrimaryKey(specParam);
        if (count != 1) {
            throw new LyException(ExceptionEnum.PARAM_UPDATE_ERROR);
        }
    }
}
