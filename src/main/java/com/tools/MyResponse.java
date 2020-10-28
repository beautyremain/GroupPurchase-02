package com.tools;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/23 23:56
*/

import com.alibaba.fastjson.JSONObject;
import com.entity.Buyer;

import java.util.List;

public class MyResponse {
    public static JSONObject JSONResponse(Object content, String state){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("content",content);
        jsonObject.put("state",state);
        return  jsonObject;
    }
    public static JSONObject buyerTableResponse(Object info,String name){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("user",name);
        jsonObject.put("info",info);
        return  jsonObject;

    }
}
