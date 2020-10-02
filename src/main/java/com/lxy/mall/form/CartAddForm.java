package com.lxy.mall.form;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/30
 */

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加商品
 */
@Data
public class CartAddForm {
    @NotNull
    private Integer productId;

    private Boolean selected = true;
}
