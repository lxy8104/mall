package com.lxy.mall.controller;
/*
 *@Description
 *@Author:lxy
 *@Date:2020/10/4
 */

import com.github.pagehelper.PageInfo;
import com.lxy.mall.consts.MallConst;
import com.lxy.mall.form.OrderCreateForm;
import com.lxy.mall.pojo.User;
import com.lxy.mall.service.IOrderService;
import com.lxy.mall.vo.OrderVo;
import com.lxy.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/orders")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form,
                                      HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.create(user.getId(), form.getShippingId());

    }

    @GetMapping("/orders")
    public ResponseVo<PageInfo> list(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.list(user.getId(), pageNum, pageSize);
    }

    @GetMapping("/orders/{orderNo}")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo,
                                       HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        return orderService.detail(user.getId(), orderNo);
    }

    @PutMapping("/orders/{orderNo}")
    public ResponseVo cancel(@PathVariable Long orderNo,
                             HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        return orderService.cancel(user.getId(), orderNo);
    }
}
