package com.controller;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 16:57
*/

import com.entity.Buyer;
import com.serviceImpl.BuyerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    BuyerServiceImpl buyerService;
    @RequestMapping("/register")
    public Object registerIn(@RequestParam String user_name,@RequestParam String password,@RequestParam String address){
        if(registerCheck(user_name)==true){
            System.out.println("cant use");
            return "<script> window.onload=function(){alert('用户名重复');window.location.href='/pp3/log/register.html'}</script>";
        }
        System.out.println("can");
        Buyer buyer=new Buyer();
        buyer.setAddress(address);
        buyer.setName(user_name);
        buyer.setPassword(password);
        buyerService.save(buyer);
        return "<script> window.onload=function(){alert('注册成功！');window.location.href='/pp3/log/log_in.html'}</script>";
    }

    @RequestMapping("/check")
    public Boolean registerCheck(@RequestParam String name){

        return buyerService.checkName(name);
    }
}
