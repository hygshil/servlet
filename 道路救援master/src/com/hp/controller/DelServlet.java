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

@WebServlet(name = "DelServlet",urlPatterns = "/DelServlet")
public class DelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.修正编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        //重点：  servlet 收取数组的参数
        String id = req.getParameter("id");
        MasterService service=new MasterService();

            int i= service.del(Integer.parseInt(id));
            System.out.println("i = " + i);

        Map codeMap=new HashMap<>();
        codeMap.put("code",0);
        codeMap.put("msg","ok");

        String s = JSONObject.toJSONString(codeMap);
        System.out.println("s = " + s);
        //5.使用 流输出
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
