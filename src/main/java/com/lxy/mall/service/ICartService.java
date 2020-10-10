package com.lxy.mall.service;

import com.lxy.mall.form.CartAddForm;
import com.lxy.mall.form.CartUpdateForm;
import com.lxy.mall.pojo.Cart;
import com.lxy.mall.vo.CartVo;
import com.lxy.mall.vo.ResponseVo;

import java.util.List;

public interface ICartService {

    ResponseVo<CartVo> add(Integer uid,CartAddForm form);

    ResponseVo<CartVo> list(Integer uid);

    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm);

    ResponseVo<CartVo> delete(Integer uid, Integer productId);
    ResponseVo<CartVo> selectAll(Integer uid);
    ResponseVo<CartVo> unSelectAll(Integer uid);
    ResponseVo<Integer> sum(Integer uid);

    List<Cart> listForCart(Integer uid);



}
