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

@WebServlet(name = "UpdateServlet",urlPatterns ="/UpdateServlet" )
public class UpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.解决乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        //2.接收前端参数
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String did = req.getParameter("did");
        String phone = req.getParameter("phone");
        String del = req.getParameter("del");
        //缺少一个参数  即：主键id  修改是由主键id进行修改的
        String id = req.getParameter("id");

        //在修改之前，先查询出来前端没有的参数
        //调用service层
        MasterService masterService = new MasterService();

        //把参数 赋值成对象
        Master master = new Master();
        master.setName(name);
        master.setSex(sex);
        master.setAge(age);
        master.setAccount(account);
        master.setPassword(password);
        master.setDid(did);
        master.setPhone(phone);
        master.setDel(del);
        master.setId(Integer.parseInt(id));

        Map map1 = masterService.update(master);
        //4.把map 变成json
        String s = JSONObject.toJSONString(map1);
        System.out.println("s = " + s);
        //5.使用 流输出
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
