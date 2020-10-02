package com.lxy.mall.service.impl;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/29
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.mall.dao.ProductMapper;
import com.lxy.mall.enums.ProductStatusEnum;
import com.lxy.mall.enums.ResponseEnum;
import com.lxy.mall.pojo.Product;
import com.lxy.mall.service.ICategoryService;
import com.lxy.mall.service.IProductService;
import com.lxy.mall.vo.ProductDetailVo;
import com.lxy.mall.vo.ProductVo;
import com.lxy.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;


    //先根据categoryId在数据库表mall_category中找到该id的所有子类的id，并把该id也添加到list中，然后根据这个list
    //中的每个id在mall_product查找每个id的信息
    @Override
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        //查询该id子类的id
        if (categoryId != null) {
            categoryService.findSubCategoryId(categoryId,categoryIdSet);
            categoryIdSet.add(categoryId);
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo<>(productList);
        pageInfo.setList(productVoList);

        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);

        //只对确定性条件判断
        if (product.getStatus().equals(ProductStatusEnum.OFF_SALE.getCode())
                || product.getStatus().equals(ProductStatusEnum.DELETE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }

        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product,productDetailVo);
        //敏感数据处理
        productDetailVo.setStock(product.getStock() > 100 ? 100 : product.getStock());

        return ResponseVo.success(productDetailVo);
    }
}
