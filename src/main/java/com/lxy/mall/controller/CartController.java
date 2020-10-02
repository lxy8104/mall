package com.lxy.mall.controller;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/30
 */

import com.lxy.mall.form.CartAddForm;
import com.lxy.mall.vo.CartVo;
import com.lxy.mall.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CartController {
    @PostMapping("/carts")
    public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm){
        return null;
    }
}
