package com.lxy.mall.service;

import com.lxy.mall.MallApplicationTests;
import com.lxy.mall.enums.ResponseEnum;
import com.lxy.mall.form.ShippingForm;
import com.lxy.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
@Slf4j
public class IShippingServiceTest extends MallApplicationTests {
    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;

    private ShippingForm form;

    private Integer shippingId;

    @Before
    public void before() {
        ShippingForm form = new ShippingForm();
        form.setReceiverName("刘大哥");
        form.setReceiverAddress("重庆邮电大学");
        form.setReceiverCity("重庆");
        form.setReceiverMobile("12345");
        form.setReceiverPhone("0236764");
        form.setReceiverProvince("重庆");
        form.setReceiverDistrict("南岸区");
        form.setReceiverZip("400065");
        this.form=form;
        add();
    }


    public void add() {

        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
        log.info("result={}",responseVo);
        this.shippingId = responseVo.getData().get("shippingId");
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());

    }

    @After
    public void delete() {
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result={}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    public void update() {
        form.setReceiverCity("成都");
        ResponseVo responseVo = shippingService.update(uid, shippingId, form);
        log.info("result={}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo responseVo = shippingService.list(uid, 1, 10);
        log.info("result={}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }
}