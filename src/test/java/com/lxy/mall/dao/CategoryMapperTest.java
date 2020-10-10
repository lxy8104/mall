package com.lxy.mall.dao;

import com.lxy.mall.MallApplicationTests;
import com.lxy.mall.pojo.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class CategoryMapperTest extends MallApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findById() {
        Category category = categoryMapper.selectByPrimaryKey(100001);
        System.out.println(category.toString());
    }

    @Test
    public void queryById() {
        Category category = categoryMapper.selectByPrimaryKey(100001);
        System.out.println(category.toString());
    }
}