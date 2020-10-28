package com.service;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:35
*/

import com.entity.SellerOrderItem;

import java.util.List;

public interface SellerService {
    public Boolean checkUser(String seller,String password);
    public List<SellerOrderItem> getAllSellerOrderByName(String seller);
    public void orderConfirm(String id);
}
