package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:44
*/

import lombok.Data;

import java.util.Date;

@Data
public class Deal {
    private long id;
    private Date date;
    private String buyer;
    private String seller;

    private String name;
    private int count;
    private float price;
    private int flag;

}
