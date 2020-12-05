package com.service;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/21 20:46
*/

import com.entity.Buyer;
import com.entity.BuyerOrderItem;
import com.entity.Cargo;
import com.entity.Deal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuyerService {
    public List<com.entity.Buyer> findAll();
    public List<com.entity.Buyer> findByName(String name);
    public void save(com.entity.Buyer student);
    public void updateInfo(com.entity.Buyer student);
    public void deleteById(Long id);
    public Boolean checkUser(String buyer,String password);
    public Boolean checkName(String buyer);
    public List<BuyerOrderItem>  getAllOrderByName(@Param("buyer") String buyer);
    public List<Cargo> getAllCargo();
    public void insertNewDeal(Deal deal);
    public void cargoRemainChange(int remain,String seller,String name);
}
