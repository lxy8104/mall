package com.lxy.mall.service.impl;

import com.lxy.mall.MallApplicationTests;
import com.lxy.mall.enums.ResponseEnum;
import com.lxy.mall.enums.RoleEnum;
import com.lxy.mall.pojo.User;
import com.lxy.mall.service.IUserService;
import com.lxy.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImplTest extends MallApplicationTests {

    private static final String USERNAME = "jack";

    private static final String PASSWORD = "123456";

    @Autowired
    private IUserService userService;

    @Test
    public void register() {
        User user = new User(USERNAME,PASSWORD,"jack@qq.com", RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
        register();
        ResponseVo<User> responseVo = userService.login(USERNAME, PASSWORD);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }
}