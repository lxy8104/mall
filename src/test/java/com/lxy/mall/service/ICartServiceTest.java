package com.lxy.mall.service;

import com.lxy.mall.MallApplicationTests;
import com.lxy.mall.form.CartAddForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ICartServiceTest extends MallApplicationTests {

    @Autowired
    private ICartService cartService;

    @Test
    public void add() {
        CartAddForm form = new CartAddForm();
        form.setProductId(27);
        form.setSelected(true);
        cartService.add(1,form);
    }
}