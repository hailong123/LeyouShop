package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecParamService;
import com.leyou.item.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-20 20:53
 * @Description:
 */

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Resource
    private SpecificationService specificationService;

    @Resource
    private SpecParamService specParamService;

    //规格数据查询
    @GetMapping(value = "groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupById(@PathVariable("cid") Long cid) {
        List<SpecGroup> list = specificationService.queryGroupById(cid);
        return ResponseEntity.ok(list);
    }

    //查询分组信息
    @RequestMapping(value = "params", method = RequestMethod.GET)
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching",required = false) Boolean searching) {
        return ResponseEntity.ok(specParamService.queryParamList(gid,cid,searching));
    }

    //插入商品
    @RequestMapping(value = "param", method = RequestMethod.POST)
    public ResponseEntity<Void> saveParam(@RequestBody SpecParam specParam) {
        specParamService.saveParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //删除商品
    @PostMapping(value = "param/{id}")
    public ResponseEntity<Void> deleteParam(@PathVariable("id") Long id) {
        specParamService.deleteParam(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //编辑商品
    @RequestMapping(value = "param", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateParam(@RequestBody SpecParam specParam) {
        specParamService.updateParam(specParam);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
