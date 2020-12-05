package com.dao;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:31
*/

import com.entity.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface SellerDao {
    public List<Seller> checkUser(@Param("name") String seller, @Param("password") String password);
    public void update(Seller seller);
    public void save(Seller seller);
    public List<Seller> findByName(String name);
}
