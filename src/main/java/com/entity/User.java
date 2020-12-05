package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/12/5 18:42
*/


import lombok.Data;

@Data
public class User {
    protected long id;
    protected String name;
    protected String password;
}
