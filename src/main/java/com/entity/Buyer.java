package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/21 20:38
*/

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Buyer extends User {
    private long id;
    private String name;
    private String password;
    private String address;

}
