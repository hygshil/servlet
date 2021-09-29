package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.bean.Master;
import com.hp.service.MasterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "AddMasterServlet", urlPatterns = "/AddMasterServlet")
public class AddMasterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.修正编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        //2.收参数
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String did = req.getParameter("did");
        String phone = req.getParameter("phone");
        String del = req.getParameter("del");

        System.out.println("del = " + del);

        //重新赋值
        Master master = new Master();
        master.setName(name);
        master.setSex(sex);
        master.setAge(age);
        master.setAccount(account);
        master.setPassword(password);
        master.setDid(did);
        master.setPhone(phone);
        master.setDel(del);

        //调用 service 层
        MasterService masterService = new MasterService();
        Map map = masterService.addMaster(master);
        System.out.println("map = " + map);
        //4.把map 变成json
        String s = JSONObject.toJSONString(map);
        System.out.println("s = " + s);
        //5.使用 流输出
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
