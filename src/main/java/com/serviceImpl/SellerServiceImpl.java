package com.serviceImpl;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:35
*/

import com.dao.CargoDao;
import com.dao.OrderDao;
import com.dao.SellerDao;
import com.entity.Cargo;
import com.entity.CheckCargoItem;
import com.entity.Seller;
import com.entity.SellerOrderItem;
import com.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerDao sellerDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    CargoDao cargoDao;
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

    @Override
    public List<Cargo> getSellerCargo(String seller) {
        return cargoDao.getSellerCargo(seller);
    }

    @Override
    public boolean checkLast(CheckCargoItem checkCargoItem) {
        return !cargoDao.checkLast(checkCargoItem).isEmpty();
    }

    @Override
    public void coverOldEdit(CheckCargoItem checkCargoItem) {
        cargoDao.coverOldEdit(checkCargoItem);
    }

    @Override
    public void newEditRequest(CheckCargoItem checkCargoItem) {
        cargoDao.newEditRequest(checkCargoItem);
    }

    @Override
    public List<Cargo> findOneCargo(Cargo cargo) {
        return cargoDao.getOneCargo(cargo);
    }

    @Override
    public List<CheckCargoItem> getCheckHistory(String seller) {
        return cargoDao.oneSellerCheckList(seller);
    }

    @Override
    public void update(Seller seller) {
        sellerDao.update(seller);
    }
}
