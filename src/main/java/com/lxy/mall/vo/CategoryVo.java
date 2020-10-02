package com.lxy.mall.vo;

import lombok.Data;

import java.util.List;

/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/27
 */
@Data
public class CategoryVo {

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> subCategories;

}
