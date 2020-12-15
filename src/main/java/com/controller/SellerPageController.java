package com.controller;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/28 10:53
*/

import com.entity.Cargo;
import com.entity.CheckCargoItem;
import com.entity.Seller;
import com.serviceImpl.SellerServiceImpl;
import com.tools.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/seller")
public class SellerPageController {
    @Autowired
    SellerServiceImpl sellerService;

    @RequestMapping("/streaminfo")
    public Object getSellerStreamInfo(HttpSession session){
        String seller=(String)session.getAttribute("user_name");
        return MyResponse.buyerTableResponse(sellerService.getAllSellerOrderByName(seller),seller);
    }

    @RequestMapping("/payCheck")
    public Object payConfirm(HttpSession session, @RequestParam String id){
        String seller=(String)session.getAttribute("user_name");
        sellerService.orderConfirm(id);
        return "已确认付款，请及时送货";
    }
    @RequestMapping("/seller_cargo")
    public Object sellerCargo(HttpSession session){
        String seller=(String)session.getAttribute("user_name");
        return MyResponse.buyerTableResponse(sellerService.getSellerCargo(seller),seller);
    }
    /*
    * 		$seller=$_SESSION['user_name'];
		$name=$_POST["商品名"];
		$date=$_POST["date"];
		$remain=$_POST["剩余数量"];
		$price=$_POST["价格"];
		$tips=$_POST["备注"];
    * */
    @RequestMapping("/edit_cargo")
    public Object editCargo(HttpSession session, HttpServletRequest request) throws ParseException {
        String seller=(String)session.getAttribute("user_name");
        String name=request.getParameter("商品名");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date=sdf.parse(request.getParameter("date"));
        String remain=request.getParameter("剩余数量");
        String price=request.getParameter("价格");
        String tips=request.getParameter("备注");
        CheckCargoItem item=new CheckCargoItem();
        item.setSeller(seller);
        item.setName(name);
        item.setDate(date);
        item.setRemain(Integer.parseInt(remain));
        item.setPrice(Float.parseFloat(price));
        item.setTips(tips);
        if(sellerService.checkLast(item)){
            sellerService.coverOldEdit(item);
            return "已覆盖上次提交的修改";
        }
        else {
            sellerService.newEditRequest(item);
            return "上传成功";
        }
    }

    @RequestMapping("/new_cargo")
    public Object setNewCargo(HttpServletResponse response,HttpSession session,HttpServletRequest request) throws IOException, ParseException {
        String seller=(String)session.getAttribute("user_name");
        String name=request.getParameter("name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date=sdf.parse(request.getParameter("date"));
        String remain=request.getParameter("remain");
        String price=request.getParameter("price");
        String tips=request.getParameter("tips");
        CheckCargoItem item=new CheckCargoItem();
        Cargo cargo=new Cargo();
        cargo.setSeller(seller);
        cargo.setName(name);
        item.setSeller(seller);
        item.setName(name);
        item.setDate(date);
        item.setRemain(Integer.parseInt(remain));
        item.setPrice(Float.parseFloat(price));
        item.setTips(tips);
        if(!sellerService.findOneCargo(cargo).isEmpty()){
            return "<script> window.onload=function(){alert('新建失败，该商品已存在');window.location.href='/pp3/main/seller/seller_cargo.html'}</script>";
        }
        else{
            sellerService.newEditRequest(item);
        }
        return "<script> window.onload=function(){alert('新建商品的请求已经上传，请等待审核！');window.location.href='/pp3/main/seller/seller_cargo.html'}</script>";
    }
    @RequestMapping("/cargo_history")
    public Object getCargoHistory(HttpSession session){
        String seller=(String)session.getAttribute("user_name");
        return MyResponse.buyerTableResponse(sellerService.getCheckHistory(seller),seller);
    }
    @RequestMapping("/head")
    public Object getHead(HttpSession session){
        return MyResponse.buyerTableResponse("success",(String)session.getAttribute("identity"));
    }
    @RequestMapping("/change")
    public Object changeInfo(@RequestParam("npwd") String pwd,HttpSession session){
        String name=(String)session.getAttribute("user_name");
        Seller seller=new Seller();
        seller.setPassword(pwd);
        seller.setName(name);
        sellerService.update(seller);
        return "success";
    }
}
