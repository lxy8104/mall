package com.lxy.mall.controller;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/27
 */

import com.lxy.mall.service.ICategoryService;
import com.lxy.mall.vo.CategoryVo;
import com.lxy.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll(){
        return categoryService.selectAll();
    }
}
