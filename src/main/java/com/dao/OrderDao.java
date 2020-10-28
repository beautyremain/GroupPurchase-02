package com.dao;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 19:55
*/

import com.entity.BuyerOrderItem;
import com.entity.Deal;
import com.entity.SellerOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderDao {
    public List<BuyerOrderItem> getAllBuyerOrderByName(@Param("buyer") String buyer);
    public void insertNewDeal(Deal deal);
    public List<SellerOrderItem> getAllSellerOrderByName(@Param("seller") String seller);
    public void orderConfirm(@Param("id") String id);
}
