package com.service;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:35
*/

import com.entity.Cargo;
import com.entity.CheckCargoItem;
import com.entity.Seller;
import com.entity.SellerOrderItem;

import java.util.List;

public interface SellerService {
    public Boolean checkUser(String seller,String password);
    public List<SellerOrderItem> getAllSellerOrderByName(String seller);
    public void orderConfirm(String id);
    public List<Cargo> getSellerCargo(String seller);
    public boolean checkLast(CheckCargoItem checkCargoItem);
    public void coverOldEdit(CheckCargoItem checkCargoItem);
    public void newEditRequest(CheckCargoItem checkCargoItem);
    public List<Cargo> findOneCargo(Cargo cargo);
    public List<CheckCargoItem> getCheckHistory(String seller);
    public void update(Seller seller);
}
