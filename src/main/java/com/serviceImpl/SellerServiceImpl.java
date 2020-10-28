package com.serviceImpl;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:35
*/

import com.dao.OrderDao;
import com.dao.SellerDao;
import com.entity.SellerOrderItem;
import com.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerDao sellerDao;
    @Autowired
    OrderDao orderDao;
    @Override
    public Boolean checkUser(String seller, String password) {
        return !sellerDao.checkUser(seller, password).isEmpty();
    }

    @Override
    public List<SellerOrderItem> getAllSellerOrderByName(String seller) {
        return orderDao.getAllSellerOrderByName(seller);
    }

    @Override
    public void orderConfirm(String id) {
        orderDao.orderConfirm(id);
    }
}
