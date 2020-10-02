package com.lxy.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/30
 */
@Data
public class CartVo {

    private List<CartProductVo> cartProductVoListList;

    private Boolean selectAll;

    private BigDecimal cartTotalPrice;

    private Integer cartTotalQuantity;

}
