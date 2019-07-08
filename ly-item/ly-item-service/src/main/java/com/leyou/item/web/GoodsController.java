package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-25 20:43
 * @Description:
 */

@RestController
@RequestMapping("")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    //分页查询
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page" , defaultValue = "1") Integer page,
            @RequestParam(value = "rows" , defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable" , required = false) Boolean saleable,
            @RequestParam(value = "key"  ,     required = false) String key
    ) {
        return ResponseEntity.ok(goodsService.querySpuByPage(page, rows, saleable, key));
    }

    //新增商品
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu) {
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //查询商品详情
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<SpuDetail> editSpuById(@PathVariable("id") Long spuId) {
        return ResponseEntity.ok(goodsService.querySpuDetailById(spuId));
    }

    //根据spu查询下面的所有sku
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId (@RequestParam("id") Long spuId) {
        return ResponseEntity.ok(goodsService.querySkuBySpuId(spuId));
    }

}
