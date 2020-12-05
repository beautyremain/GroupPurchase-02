package com.controller;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/21 17:31
*/

import com.serviceImpl.BuyerServiceImpl;
import com.serviceImpl.SellerServiceImpl;
import com.tools.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/log")
public class LoginController {
    @Autowired
    BuyerServiceImpl buyerService;
    @Autowired
    SellerServiceImpl sellerService;

    @RequestMapping("/login/{identity}")
    public String LogIn(@PathVariable String identity, @RequestParam("user_name") String name, @RequestParam String password, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println("login:"+name);
        if(name.equals("")||password.equals("")){
            return "<script> window.onload=function(){alert('EMPTY PASSWORD OR USER NAME');window.location.href='/pp3/log/log_in.html'}</script>";
        }
        if(buyerService.checkUser(name,password)==false&&identity.equals("buyer")){
            return "<script> window.onload=function(){alert('WRONG PASSWORD OR USER NOT EXIST');window.location.href='/pp3/log/log_in.html'}</script>";
        }
        else if(sellerService.checkUser(name,password)==false&&identity.equals("seller")){
            return "<script> window.onload=function(){alert('WRONG PASSWORD OR USER NOT EXIST');window.location.href='/pp3/log/seller_in.html'}</script>";
        }
        else{
            if(name.equals("root")&&identity.equals("buyer"))
            {
                session.setAttribute("identity","root");
                session.setAttribute("user_name",name);
                response.sendRedirect("/pp3/main/root/deal_stream.html");//edit to personal page root
            }
            else {
                session.setAttribute("identity", identity);
                session.setAttribute("user_name", name);
                if(identity.equals("seller")) {
                    response.sendRedirect("/pp3/main/seller/deal_stream.html");//edit to personal page seller
                }else{
                    response.sendRedirect("/pp3/main/buyer/info_page.html");//edit to personal page buyer
                }
            }
        }
        return null;
        //response.sendRedirect("/pp3/log/seller_in.html");
    }
    @RequestMapping("/page")
    public Object Page( HttpServletResponse response) throws IOException {
        System.out.println("page");
        //response.sendRedirect("/pp3/log/seller_in.html");
        return MyResponse.JSONResponse("/pp3/log/log_in.html","fail");
    }
    @RequestMapping("/logout")
    public void logOut(HttpSession session,HttpServletResponse response) throws IOException {
        session.removeAttribute("user_name");
        session.removeAttribute("identity");
        response.sendRedirect("/pp3/log/log_in.html");
    }

}
