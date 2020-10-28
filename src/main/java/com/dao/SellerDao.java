package com.dao;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:31
*/

import com.entity.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface SellerDao {
    public List<Seller> checkUser(@Param("seller") String seller, @Param("password") String password);
}
