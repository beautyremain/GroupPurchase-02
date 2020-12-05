package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/12/5 16:37
*/

import lombok.Data;

import java.util.Date;

@Data
public class CheckCargoItem {
    private long id;
    private Date date;
    private String seller;
    private String  name;
    private int remain;
    private float price;
    private String tips;
    private int c_flag;
}
