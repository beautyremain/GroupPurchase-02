package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 19:19
*/

import lombok.Data;

import java.util.Date;

@Data
public class BuyerOrderItem {

    private Date date;
    private String cargo_name;
    private int cargo_count;
    private float price;
    private String seller;
    private String cargo_tips;
    private int flag;

}
