package com.dao;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/21 21:18
*/

import com.entity.Buyer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper
@Component
public interface BuyerDao {
    public List<Buyer> findAll();
    public Buyer findById(Long id);
    public void save(Buyer buyer);
    public void update(Buyer buyer);
    public void deleteById(Long id);
    public List<Buyer> checkUser(@Param("buyer") String buyer,@Param("password") String password);
    public List<Buyer> checkName(@Param("buyer") String buyer);
}
