package com.dao;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:47
*/

import com.entity.Deal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DealDao {
    public List<Deal> getDealStream();
    public void insertNewDeal(Deal deal);
    public List<Deal> search(@Param("condition") String condition);

}
