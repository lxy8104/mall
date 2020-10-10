package com.lxy.mall.service;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lxy.mall.MallApplicationTests;
import com.lxy.mall.enums.ResponseEnum;
import com.lxy.mall.form.CartAddForm;
import com.lxy.mall.vo.CartVo;
import com.lxy.mall.vo.OrderVo;
import com.lxy.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
public class IOrderServiceTest extends MallApplicationTests {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService cartService;

    private Integer uid = 1;

    private Integer shippingId = 6;

    private Integer productId = 26;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Before
    public void before() {
        CartAddForm form = new CartAddForm();
        form.setProductId(productId);
        form.setSelected(true);

        ResponseVo<CartVo> responseVo = cartService.add(uid, form);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());

    }

    @Test
    public void createTest() {
        ResponseVo<OrderVo> responseVo = create();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }


    private ResponseVo<OrderVo> create() {
        ResponseVo<OrderVo> ResponseVo = orderService.create(uid, shippingId);
        log.info("result={}", gson.toJson(ResponseVo));

        return ResponseVo;
    }

    @Test
    public void list() {
        ResponseVo<PageInfo> ResponseVo = orderService.list(uid, 1,2);
        log.info("result={}", gson.toJson(ResponseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),ResponseVo.getStatus());
    }
    @Test
    public void detail() {
        ResponseVo<OrderVo> vo = create();
        ResponseVo<OrderVo> ResponseVo = orderService.detail(uid, vo.getData().getOrderNo());
        log.info("result={}", gson.toJson(ResponseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),ResponseVo.getStatus());
    }

    @Test
    public void cancel() {
        ResponseVo<OrderVo> vo = create();
        ResponseVo ResponseVo = orderService.cancel(uid, vo.getData().getOrderNo());
        log.info("result={}", gson.toJson(ResponseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),ResponseVo.getStatus());
    }
}