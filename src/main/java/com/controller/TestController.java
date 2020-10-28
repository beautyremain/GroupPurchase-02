package com.controller;/*
    author: BeautyRemain(程季康)
    create time: 2020/10/21 11:20
*/

import com.dao.BuyerDao;
import com.entity.BuyerOrderItem;
import com.serviceImpl.BuyerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/test")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class TestController {
    @Autowired
    BuyerServiceImpl buyerService;
    @RequestMapping("/t")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/pp3/log/log_in.html");
    }
    @RequestMapping("/t2")
    @ResponseBody
    public List<BuyerOrderItem> index2(){
       return buyerService.getAllOrderByName("cxy");
    }

    @RequestMapping("/t3/{para}")
    public void index3(@PathVariable String para, HttpServletResponse response) throws IOException {
        System.out.println(para);
        response.sendRedirect("../../hello.html");

    }

    @Autowired
    BuyerServiceImpl studentService;
    @Autowired
    BuyerDao studentDao;
    @ResponseBody
    @RequestMapping("/sql")
    public Object index4(){

        System.out.println(studentService.findAll().size());
        System.out.println(studentDao.findAll().get(0).toString());
        return studentService.findAll();

    }
}
