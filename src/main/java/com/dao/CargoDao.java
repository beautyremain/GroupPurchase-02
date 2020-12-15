package com.dao;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/23 16:51
*/

import com.entity.Cargo;
import com.entity.CheckCargoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CargoDao {
    public List<Cargo> getAllCargo();
    public void remainChange(@Param("remain") int remain,@Param("seller") String seller,@Param("name") String name);
    public List<CheckCargoItem> getCheckList();
    public void checkList(CheckCargoItem item);
    public List<Cargo> getOneCargo(Cargo cargo);
    public void insertCargo(Cargo cargo);
    public void updateCargo(Cargo cargo);
    public List<Cargo> getSellerCargo(String seller);
    public List<CheckCargoItem> checkLast(CheckCargoItem checkCargoItem);
    public void coverOldEdit(CheckCargoItem checkCargoItem);
    public void newEditRequest(CheckCargoItem checkCargoItem);
    public List<CheckCargoItem> oneSellerCheckList(String seller);
}
