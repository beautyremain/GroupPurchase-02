package com.controller;/*
    author: BeautyRemain(程季康)
    create time: 2020/12/5 14:38
*/

import com.entity.*;
import com.serviceImpl.RootServiceImpl;
import com.tools.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/root")
public class RootPageController {
    @Autowired
    RootServiceImpl rootService;
    @RequestMapping("/deal_stream")
    public Object getDealStream(HttpSession session){
        String root_name=(String) session.getAttribute("user_name");
        return MyResponse.buyerTableResponse(rootService.getDealStream(),root_name);
    }
    @RequestMapping("/search")
    public Object search(@RequestParam("condition") String c){
        System.out.println("print string:"+c);
        return MyResponse.buyerTableResponse(rootService.search(c),"root");
    }
    @RequestMapping("/history_stream")
    public Object getCargoCheckList(){
        return MyResponse.buyerTableResponse(rootService.getCheckList(),"root");
    }
    @RequestMapping("/edit_check")
    public Object editCheckStatus(HttpServletRequest request){
        int count=Integer.parseInt(request.getParameter("count"));
        String name=request.getParameter("name");
        String seller=request.getParameter("seller");
        String tips=request.getParameter("tips");
        int condition=Integer.parseInt(request.getParameter("condition"));

        CheckCargoItem checkCargoItem=new CheckCargoItem();
        checkCargoItem.setC_flag(condition);
        checkCargoItem.setName(name);
        checkCargoItem.setSeller(seller);

        Cargo cargo=new Cargo();
        cargo.setSeller(seller);
        cargo.setRemain(count);
        cargo.setTips(tips);
        cargo.setName(name);
        cargo.setPrice(Float.parseFloat(request.getParameter("price")));

        rootService.checkList(checkCargoItem);
        if(checkCargoItem.getC_flag()==2){
            return "deny success";
        }
        if(rootService.haveCargo(cargo)){
            rootService.updateCargo(cargo);
            return "update success";
        }
        else {
            rootService.insertCargo(cargo);
            return "insert success";
        }
    }

    //采用父类进行操作
    @RequestMapping("/init_user")
    public Object initUser(@RequestParam String identity,@RequestParam("user") String user_name){
        if(!rootService.haveUserName(user_name,identity)){
            return "no such user";
        }
        User user;
        if(identity.equals("seller")){
            user=new Seller();
        }
        else {
            user=new Buyer();
        }
        user.setName(user_name);
        user.setPassword("123456");
        rootService.initUser(user,identity);
        return "success";
    }
    @RequestMapping("/new_seller")
    public Object newSeller(@RequestParam("seller") String seller_name){
        if(rootService.haveUserName(seller_name,"seller")){
            return "seller "+seller_name+" is already exist";
        }
        Seller seller=new Seller();
        seller.setName(seller_name);
        seller.setPassword("123456");
        rootService.newSeller(seller);
        return "success";
    }
    @RequestMapping("/head")
    public Object getHead(HttpSession session){
        return MyResponse.buyerTableResponse("success",(String)session.getAttribute("identity"));
    }
}
