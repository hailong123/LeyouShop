package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-10 20:43
 * @Description:
 */

@RestController
@RequestMapping("brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    //分页查询数据
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page" , defaultValue = "1") Integer page,
            @RequestParam(value = "rows" , defaultValue = "5") Integer rows,
            @RequestParam(value = "desc" , defaultValue = "false") Boolean desc,
            @RequestParam(value = "key"  , required = false) String key,
            @RequestParam(value = "sortBy" , required = false) String sortBy
            ) {
        PageResult<Brand> result = brandService.queryBrandByPage(page, rows, sortBy, desc, key);
        return ResponseEntity.ok(result);
    }

    //添加商品
    @RequestMapping
    public ResponseEntity<Void> saveBrand(@Validated Brand brand, @RequestParam("cids") List<Long> cids ) {
        brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //查询品牌
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid (@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }
}
