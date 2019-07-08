package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Auther: seadragon
 * @Date: 2019-06-08 17:20
 * @Description:
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    BRAND_NOT_FOND(404,"品牌不存在"),
    CATEGORY_NOT_FOND(404,"商品详情无数据"),
    BRAND_SAVE_ERROR(404,"保存商品失败"),
    BRAND_INSERT_ERROR(404,"新增商品失败"),
    UPLOAD_IMAGE_ERROR(404,"上传商品图片失败"),
    INVALID_FILE_TYPE_ERROR(404,"无效的文件类型"),
    SPECIFICATION_GROUP_NOT_FOND(404,"商品规格组无数据"),
    PARAM_NOT_FOND(404,"商品无数据"),
    PARAM_INSERT_ERROR(404,"商品插入失败"),
    PARAM_DELETE_ERROR(404,"商品删除失败"),
    PARAM_UPDATE_ERROR(404,"商品更新失败"),
    BRAND_SELECT_ERROR(404,"品牌查询失败"),
    GOODS_QUERY_NOT_FOND(404,"商品查询失败"),
    GOODS_SKU_NOT_FOND(404,"商品SKU查询失败"),
    GOODS_CREATE_ERROR(501,"新增商品失败"),
    GOODS_QUERY_ERROR(501,"商品查询失败"),
    GOODS_STOCK_QUERY_ERROR(501,"商品库存查询失败")
    ;
    private int code;
    private String message;
}
