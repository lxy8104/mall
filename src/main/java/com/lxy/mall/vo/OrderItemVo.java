package com.lxy.mall.vo;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/10/4
 */

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItemVo {
    private Long orderNo;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createTime;
}
