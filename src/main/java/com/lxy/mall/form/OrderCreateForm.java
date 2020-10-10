package com.lxy.mall.form;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/10/4
 */

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderCreateForm {
    @NotNull
    private Integer shippingId;
}
