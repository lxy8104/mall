package com.lxy.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/30
 */
@Data
public class CartProductVo {
    private Integer productId;

    //购买的数量
    private Integer quantity;

    private String productName;

    private String productSubtitle;

    private String productMainImage;

    private BigDecimal productPrice;

    private Integer productStatus;

    //等于 数量 * 单价
    private BigDecimal productTotalPrice;

    private Integer productStock;

    //商品是否选中
    private Boolean productSelected;

}
