package com.lxy.mall.listener;

import com.google.gson.Gson;
import com.lxy.mall.pojo.PayInfo;
import com.lxy.mall.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 *@Description
 *@Author:lxy
 *@Date:2020/10/5
 */
@Component
@RabbitListener(queues="payNotify")
@Slf4j
public class PayMsgListener {

    @Autowired
    private IOrderService orderService;

    @RabbitHandler
    public void process(String msg) {
        log.info("【接收到消息】=>{}",msg);

        //关于PayInfo 正确姿势 ： pay项目提供client.jar  mall项目引入Jar包
        PayInfo payInfo = new Gson().fromJson(msg, PayInfo.class);
        if (payInfo.getPlatformStatus().equals("SUCCESS")) {
            //修改订单里的状态
            orderService.paid(payInfo.getOrderNo());
        }


    }
}
