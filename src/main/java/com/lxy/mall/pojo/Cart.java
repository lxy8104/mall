package com.lxy.mall.pojo;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/30
 */

import lombok.Data;

@Data
public class Cart {
    private Integer productId;
    private Integer quantity;
    private Boolean productSelectd;

    public Cart() {
    }

    public Cart(Integer productId, Integer quantity, Boolean productSelectd) {
        this.productId = productId;
        this.quantity = quantity;
        this.productSelectd = productSelectd;
    }
}
