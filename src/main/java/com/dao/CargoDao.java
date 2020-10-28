package com.dao;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/23 16:51
*/

import com.entity.Cargo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CargoDao {
    public List<Cargo> getAllCargo();
    public void remainChange(@Param("remain") int remain,@Param("seller") String seller,@Param("name") String name);
}
