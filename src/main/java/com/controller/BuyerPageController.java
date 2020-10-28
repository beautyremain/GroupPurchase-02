package com.controller;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/24 19:09
*/

import com.entity.Buyer;
import com.entity.BuyerOrderItem;
import com.entity.Cargo;
import com.entity.Deal;
import com.service.BuyerService;
import com.tools.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/buyer")
public class BuyerPageController {
    @Autowired
    BuyerService buyerService;
    @RequestMapping("/selfinfo")
    public Object selfOrderInfo(HttpSession session){
        String buyer_name=(String) session.getAttribute("user_name");
        if(!session.getAttribute("identity").equals("buyer")){
            return MyResponse.buyerTableResponse("dont try anything illegal",buyer_name);
        }
         List<BuyerOrderItem> result=buyerService.getAllOrderByName(buyer_name);
         //System.out.println("self info processing:"+result.isEmpty());
         return MyResponse.buyerTableResponse(result,buyer_name);
    }
    @RequestMapping("/bookinfo")
    public Object bookInfo(HttpSession session){
        String buyer_name=(String) session.getAttribute("user_name");
        if(!session.getAttribute("identity").equals("buyer")){
            return MyResponse.buyerTableResponse("dont try anything illegal",buyer_name);
        }
        List<Cargo> result=buyerService.getAllCargo();
        return MyResponse.buyerTableResponse(result,buyer_name);

    }
    @RequestMapping("/book_request")
    public Object bookRequest(HttpSession session, HttpServletRequest request) throws ParseException {

        String buyer_name=(String) session.getAttribute("user_name");
        if(!session.getAttribute("identity").equals("buyer")){
            return MyResponse.buyerTableResponse("dont try anything illegal",buyer_name);
        }
        String dateStr=request.getParameter("date").replaceAll("/","-");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr.toString());
        int remain=Integer.parseInt(request.getParameter("剩余数量/斤"));
        int count=Integer.parseInt(request.getParameter("count"));
        if(remain-count<0){
            return "too much";
        }
        remain=remain-count;
        String name=request.getParameter("物资名");
        String seller=request.getParameter("卖家");
        Deal deal=new Deal();
        deal.setBuyer(buyer_name);
        deal.setDate(date);
        deal.setSeller(seller);
        deal.setCount(count);
        deal.setName(name);
        deal.setPrice(Float.parseFloat(request.getParameter("价格 元/斤")));
        buyerService.insertNewDeal(deal);
        buyerService.cargoRemainChange(remain,seller,name);
        return "购买请求提交成功！请尽快联系商家付款";
    }
    @RequestMapping("/changeInfo")
    public Object changeBuyerInfo(HttpServletRequest request,HttpSession session){
        String user_name=(String) session.getAttribute("user_name");
        String new_pwd=request.getParameter("npwd");
        String new_address=request.getParameter("addr");
        Buyer buyer=new Buyer();
        buyer.setName(user_name);
        buyer.setPassword(new_pwd);
        buyer.setAddress(new_address);
        if(new_pwd==null && new_address==null){
            return MyResponse.buyerTableResponse(null,user_name);
        }
        else{
            buyerService.updateInfo(buyer);
            return "success";
        }
    }

}
