package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/23 16:52
*/

import lombok.Data;

@Data
public class Cargo {
    private long id;
    private String seller;
    private String name;
    private int remain;
    private float price;
    private String tips;
}
