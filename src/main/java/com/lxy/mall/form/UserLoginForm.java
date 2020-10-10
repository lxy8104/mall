package com.lxy.mall.form;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/23
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
