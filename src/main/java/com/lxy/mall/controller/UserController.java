package com.lxy.mall.controller;

import com.lxy.mall.consts.MallConst;
import com.lxy.mall.form.UserLoginForm;
import com.lxy.mall.form.UserRegisterForm;
import com.lxy.mall.pojo.User;
import com.lxy.mall.service.impl.UserServiceImpl;
import com.lxy.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/*
 *@Description
 *@Author:lxy
 *@Date:2020/9/20
 */
@RestController

@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user/register")
    public ResponseVo register(@Valid @RequestBody UserRegisterForm userform) {
        User user = new User();
        BeanUtils.copyProperties(userform,user);

        return userService.register(user);
    }

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm, HttpSession session) {
        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        
        //设置Session
        session.setAttribute(MallConst.CURRENT_USER, userResponseVo.getData());
        log.info("/login sessionId={}", session.getId());
        return userResponseVo;
    }

    //session保存在内存里（很容易丢失），重启后直接调用/user就会提示 未登录
    //一般会使用redis存储  必须通过sessionId获得对象  所以改进版：使用token+redis  token其实就是session  id
    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session) {
        log.info("/user  sessionId={}", session.getId());
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success(user);

    }

    //TODO 判断登录状态，拦截器

    /**
     * {@link org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory}
     * @param session
     * @return
     */
    @PostMapping("/user/logout")
    public ResponseVo logout(HttpSession session) {
        log.info("/user/logout sessionId={}",session.getId());
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();
    }



}
