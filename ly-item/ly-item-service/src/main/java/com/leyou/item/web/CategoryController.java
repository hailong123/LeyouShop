package com.leyou.item.web;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: seadragon
 * @Date: 2019-06-09 16:57
 * @Description:
 */

@RestController
@RequestMapping("category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam("pid") Long id) {
        return ResponseEntity.ok(categoryService.queryCategoryListByPid(id));
    }

}
