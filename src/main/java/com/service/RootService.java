package com.service;/*
    author: BeautyRemain(程季康)
    create time: 2020/12/5 14:43
*/

import com.dao.DealDao;
import com.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RootService {
    public List<Deal> getDealStream();
    public List<Deal> search(String s);
    public List<CheckCargoItem> getCheckList();
    public void checkList(CheckCargoItem item);
    public List<Cargo> getOneCargo(Cargo cargo);
    public void insertCargo(Cargo cargo);
    public void updateCargo(Cargo cargo);
    public boolean haveCargo(Cargo cargo);
    public void initUser(User user,String identity);
    public void newSeller(Seller seller);
    public boolean haveUserName(String name, String identity);
}
