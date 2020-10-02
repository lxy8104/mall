package com.lxy.mall.service;

import com.github.pagehelper.PageInfo;
import com.lxy.mall.MallApplicationTests;
import com.lxy.mall.enums.ResponseEnum;
import com.lxy.mall.vo.ProductDetailVo;
import com.lxy.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IProductServiceTest extends MallApplicationTests {
    @Autowired
    private IProductService productService;

    @Test
    public void list() {

        ResponseVo<PageInfo> listResponseVo = productService.list(null, 2, 2);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());
    }

    @Test
    public void detail() {
        ResponseVo<ProductDetailVo> responseVo = productService.detail(26);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}