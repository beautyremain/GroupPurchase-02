package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 14:29
*/

import lombok.Data;

@Data
public class Seller extends User {
    private long id;
    private String name;
    private String password;

}
