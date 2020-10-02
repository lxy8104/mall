package com.lxy.mall.service.impl;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/27
 */

import com.lxy.mall.consts.MallConst;
import com.lxy.mall.dao.CategoryMapper;
import com.lxy.mall.pojo.Category;
import com.lxy.mall.service.ICategoryService;
import com.lxy.mall.vo.CategoryVo;
import com.lxy.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 耗时: http(请求wechat api) > 磁盘 > 内存
     * mysql(内网 + 磁盘)
     * @return
     */

    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        //List<CategoryVo> categoryVoList = new ArrayList<>();
        List<Category> categories = categoryMapper.selectAll();

        //查出parent_id=0
//        for (Category category : categories) {
//            if (category.getParentId().equals(MallConst.ROOT_PARENT_ID)) {
//                CategoryVo categoryVo = new CategoryVo();
//                BeanUtils.copyProperties(category, categoryVo);
//                categoryVoList.add(categoryVo);
//            }
//        }

        //lambda + stream
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(MallConst.ROOT_PARENT_ID))
                .map(this::category2CategoryVo)
                //按照降序排序
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        //查询子目录
        findSubCategory(categoryVoList,categories);

        return ResponseVo.success(categoryVoList);
    }

    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        List<Category> categories = categoryMapper.selectAll();
        findSubCategoryId(id, resultSet, categories);

    }

    private void findSubCategoryId(Integer id, Set<Integer> resultSet, List<Category> categories) {
        for (Category category : categories) {
            if(category.getParentId().equals(id)){
                resultSet.add(category.getId());

                findSubCategoryId(category.getId(), resultSet,categories);
            }
        }

    }


    private void findSubCategory(List<CategoryVo> categoryList, List<Category> categories) {
        for (CategoryVo categoryVo : categoryList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();

            for (Category category : categories) {
                //如果查到内容 ， 设置subCategory, 继续往下查
                if (categoryVo.getId().equals(category.getParentId())) {
                    CategoryVo subCategoryVo = category2CategoryVo(category);
                    subCategoryVoList.add(subCategoryVo);
                }

                //按照SortOrder降序排序
                subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());

                categoryVo.setSubCategories(subCategoryVoList);

                findSubCategory(subCategoryVoList,categories);
            }
        }
    }

    private CategoryVo category2CategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
