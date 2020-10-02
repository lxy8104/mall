package com.lxy.mall.service;

import com.lxy.mall.form.CartAddForm;
import com.lxy.mall.vo.CartVo;
import com.lxy.mall.vo.ResponseVo;

public interface ICartService {

    ResponseVo<CartVo> add(Integer uid,CartAddForm form);

}
