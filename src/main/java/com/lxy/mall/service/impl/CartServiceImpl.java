package com.lxy.mall.service.impl;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/30
 */

import com.google.gson.Gson;
import com.lxy.mall.dao.ProductMapper;
import com.lxy.mall.enums.ProductStatusEnum;
import com.lxy.mall.enums.ResponseEnum;
import com.lxy.mall.form.CartAddForm;
import com.lxy.mall.pojo.Cart;
import com.lxy.mall.pojo.Product;
import com.lxy.mall.service.ICartService;
import com.lxy.mall.vo.CartVo;
import com.lxy.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    public ResponseVo<CartVo> add(Integer uid, CartAddForm form) {
        Integer quantity = 1;
        Product product = productMapper.selectByPrimaryKey(form.getProductId());
        //判断商品是否存在

        if (product == null) {
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }

        //商品是否正常在售
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }

        //商品库存是否充足
        if (product.getStock() <= 0) {
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        //写入redis
        //key: cart_1
        redisTemplate.opsForValue().set(String.format(CART_REDIS_KEY_TEMPLATE, uid),
                gson.toJson(new Cart(product.getId(),quantity,form.getSelected())));


        return null;
    }
}
