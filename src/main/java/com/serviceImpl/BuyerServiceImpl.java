package com.serviceImpl;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/21 21:22
*/

import com.dao.BuyerDao;
import com.dao.CargoDao;
import com.dao.DealDao;
import com.dao.OrderDao;
import com.entity.BuyerOrderItem;
import com.entity.Cargo;
import com.entity.Deal;
import com.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private BuyerDao buyerDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private DealDao dealDao;
    @Override
    public List<com.entity.Buyer> findAll() {
        return buyerDao.findAll();
    }

    @Override
    public List<com.entity.Buyer> findByName(String name) {
        return buyerDao.findByName(name);
    }

    @Override
    public void save(com.entity.Buyer student) {
        buyerDao.save(student);
    }

    @Override
    public void updateInfo(com.entity.Buyer buyer) {
        buyerDao.update(buyer);
    }

    @Override
    public void deleteById(Long id) {
        buyerDao.deleteById(id);
    }

    @Override
    public Boolean checkUser(String buyer, String password) {
        return  !buyerDao.checkUser(buyer,password).isEmpty();
    }

    @Override
    public Boolean checkName(String buyer) {

        return !buyerDao.checkName(buyer).isEmpty();
    }

    @Override
    public List<BuyerOrderItem> getAllOrderByName(String buyer) {
        return orderDao.getAllBuyerOrderByName(buyer);
    }

    @Override
    public List<Cargo> getAllCargo() {
        return cargoDao.getAllCargo();
    }

    @Override
    public void insertNewDeal(Deal deal) {
        dealDao.insertNewDeal(deal);
    }

    @Override
    public void cargoRemainChange(int remain, String seller, String name) {
        cargoDao.remainChange(remain,seller,name);
    }
}
