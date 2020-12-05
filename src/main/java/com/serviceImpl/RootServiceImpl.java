package com.serviceImpl;/*
    author: BeautyRemain(程季康)
    create time: 2020/12/5 14:45
*/

import com.dao.BuyerDao;
import com.dao.CargoDao;
import com.dao.DealDao;
import com.dao.SellerDao;
import com.entity.*;
import com.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootServiceImpl implements RootService {
    @Autowired
    DealDao dealDao;
    @Autowired
    CargoDao cargoDao;
    @Autowired
    SellerDao sellerDao;
    @Autowired
    BuyerDao buyerDao;
    @Override
    public List<Deal> getDealStream() {
        return dealDao.getDealStream();
    }

    @Override
    public List<Deal> search(String s) {
        return dealDao.search(s);
    }

    @Override
    public List<CheckCargoItem> getCheckList() {
        return cargoDao.getCheckList();
    }

    @Override
    public void checkList(CheckCargoItem item) {
        cargoDao.checkList(item);
    }

    @Override
    public List<Cargo> getOneCargo(Cargo cargo) {
        return cargoDao.getOneCargo(cargo);
    }

    @Override
    public void insertCargo(Cargo cargo) {
        cargoDao.insertCargo(cargo);
    }

    @Override
    public void updateCargo(Cargo cargo) {
        cargoDao.updateCargo(cargo);
    }

    @Override
    public boolean haveCargo(Cargo cargo) {
        return !getOneCargo(cargo).isEmpty();
    }

    @Override
    public void initUser(User user, String identity) {
        if(identity.equals("buyer")){
            buyerDao.update((Buyer) user);
        }
        if(identity.equals("seller")){
            sellerDao.update((Seller) user);
        }
    }

    @Override
    public void newSeller(Seller seller) {
        sellerDao.save(seller);
    }

    @Override
    public boolean haveUserName(String name, String identity) {
        if(identity.equals("buyer")){
            return !buyerDao.findByName(name).isEmpty();
        }
        else{
            return !sellerDao.findByName(name).isEmpty();
        }
    }
}
