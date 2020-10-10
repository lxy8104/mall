package com.lxy.mall.service;

import com.lxy.mall.pojo.User;
import com.lxy.mall.vo.ResponseVo;

public interface IUserService  {

    /*
    注册
     */
    ResponseVo<User> register(User user);

    /*
    登录
     */
    ResponseVo<User> login(String userName, String password);
}
