package com.controller;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/28 10:53
*/

import com.serviceImpl.SellerServiceImpl;
import com.tools.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/seller")
public class SellerPageController {
    @Autowired
    SellerServiceImpl sellerService;

    @RequestMapping("/streaminfo")
    public Object getSellerStreamInfo(HttpSession session){
        String seller=(String)session.getAttribute("user_name");
        return MyResponse.buyerTableResponse(sellerService.getAllSellerOrderByName(seller),seller);
    }

    @RequestMapping("/payCheck")
    public Object payConfirm(HttpSession session, @RequestParam String id){
        String seller=(String)session.getAttribute("user_name");
        sellerService.orderConfirm(id);
        return "已确认付款，请及时送货";
    }
}
