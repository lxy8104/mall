package com.lxy.mall.service;

import com.lxy.mall.vo.CategoryVo;
import com.lxy.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;


public interface ICategoryService {

    ResponseVo<List<CategoryVo>> selectAll();

    void findSubCategoryId(Integer id, Set<Integer> resultSet);

}
