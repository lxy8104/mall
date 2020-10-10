package com.lxy.mall.service.impl;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/20
 */

import com.lxy.mall.dao.UserMapper;
import com.lxy.mall.enums.ResponseEnum;
import com.lxy.mall.enums.RoleEnum;
import com.lxy.mall.pojo.User;
import com.lxy.mall.service.IUserService;
import com.lxy.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    /*
    注册
     */
    @Override
    public ResponseVo<User> register(User user) {


        //username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }

        //email不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0) {
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }

        user.setRole(RoleEnum.CUSTOMER.getCode());

        //MD5摘要算法Spring自带
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)
        ));

        //写入数据库
        int resultCount = userMapper.insertSelective(user);
        if (resultCount == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);

        }

        return ResponseVo.success();

    }

    @Override
    public ResponseVo<User> login(String userName, String password) {
        User user = userMapper.selectByUsername(userName);
        if (user == null) {
            //用户不存在(返回：用户名或密码错误）
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            //密码错误(返回：用户名或密码错误）
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //返回的json中没有password具体的数据
        user.setPassword("");
        return ResponseVo.success(user);
    }

    private void error() {
        throw new RuntimeException("意外错误");
    }
}
