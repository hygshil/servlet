package com.hp.service;

import com.hp.bean.Master;
import com.hp.dao.MasterDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterService {
    //登录
    public Map login(String name, String password, HttpServletRequest request) {
        Map map = new HashMap();
        //service 层要调用dao层
        MasterDao MasterDao = new MasterDao();
        Master MasterFromDB = MasterDao.login(name, password);
        if (null == MasterFromDB) {
            //没查出来，即：账户名或密码不正确
            map.put("code", 4001);
            map.put("msg", "账户或密码不正确");
            return map;
        } else {
            //当登陆成功后，把信息放入到session作用域中
            HttpSession session = request.getSession();
            session.setAttribute("user", MasterFromDB);
            map.put("code", 0);
            map.put("msg", "登陆成功");
            //给一个账户  给前端渲染
            map.put("name", name);
            return map;
        }
    }

    //全查
    public Map selectAll() {
        MasterDao dao = new MasterDao();
        List<Master> masters = dao.selectAll();
        Map codeMap = new HashMap();
        codeMap.put("code", 0);
        codeMap.put("msg", "ok");
        codeMap.put("data", masters);

        return codeMap;
    }

    //增加
    public Map addMaster(Master master) {
        System.out.println("进入到 service 层了---");
        Map map = new HashMap();
        MasterDao masterDao = new MasterDao();
        System.out.println("masterDao = " + masterDao);
        int i = masterDao.addMaster(master);
        System.out.println("i = " + i);
        if (i == 1) {
            map.put("code", 0);
            map.put("msg", "添加成功");
        } else {
            map.put("code", 400);
            map.put("msg", "添加不成功");
        }
        return map;
    }

    //修改
    public Map update(Master master){
        Map codeMap = new HashMap();
        MasterDao dao = new MasterDao();
        int i = dao.update(master);
        if(i==1){
            codeMap.put("code",0);
            codeMap.put("msg","修改成功");
        }else{
            codeMap.put("code",400);
            codeMap.put("msg","修改不成功");
        }
        return codeMap;
    }

    //删除
    public int del(Integer id){
        MasterDao masterDao=new MasterDao();
        int i=masterDao.del(id);
        return i;
    }

    public Map selectAllByParam(Map map1){
        MasterDao masterDao = new MasterDao();
        List<Master> masters = masterDao.selectAllParam(map1);
        Map map2 = new HashMap();
        map2.put("code",0);
        map2.put("msg","数据查询成功");
        map2.put("data",masters);
        return map2;
    }

}
