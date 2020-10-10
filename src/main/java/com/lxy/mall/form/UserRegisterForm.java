package com.lxy.mall.form;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/22
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
//该类用于注册使用
public class UserRegisterForm {

    //@NotBlank 用于String 判断空格  不允许空格传进来
    //@NotEmpty 检查集合
    //@NotNull
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
