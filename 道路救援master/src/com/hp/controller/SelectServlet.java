package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.service.MasterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SelectServlet",urlPatterns = "/SelectServlet")
public class SelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.修正编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        //2.接收 2 个参数 page limit

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String account = req.getParameter("account");




        //把接收到的参数封装到 map中
        Map map1 = new HashMap();
        map1.put("name",name);
        map1.put("age",age);
        map1.put("account",account);

        //3.调用service
        MasterService masterService = new MasterService();
        Map map = masterService.selectAllByParam(map1);

        //4.把map 变成json
        String s = JSONObject.toJSONString(map);
        //5.使用 流输出
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
