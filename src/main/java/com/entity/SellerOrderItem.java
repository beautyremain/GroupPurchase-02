package com.entity;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/28 11:24
*/

import lombok.Data;

import java.util.Date;
@Data
public class SellerOrderItem {
    private int id;
    private Date date;
    private String buyer;
    private String cargo_name;
    private int cargo_count;
    private float price;

    private String buyer_address;
    private int flag;
        /*"select deal_stream.id,
    date,
    deal_stream.buyer,
    name,
    count,
    price,
    address,
    flag from deal_stream inner join buyer_info where deal_stream.buyer=buyer_info.buyer and seller='{$name}'ORDER BY flag,id desc";
    */
}
