package com.hp.dao;

import com.hp.bean.Master;
import com.hp.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MasterDao {
    //登录
    public Master login(String name, String password) {
        Master master = null;
        //创建链接
        Connection conn = DBHelper.getConnection();
        //创建sql 语句
        String sql = "select * from master where name=? and password=?";
        //获取预编译对象
        PreparedStatement ps = null;
        //执行预编译对象
        ResultSet rs = null;
        try {
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            //执行预编译对象
            rs = ps.executeQuery();
            if (rs.next()) {
                master = new Master();
                master.setId(rs.getInt("id"));
                master.setName(rs.getString("name"));
                master.setSex(rs.getString("sex"));
                master.setAge(rs.getString("age"));
                master.setAccount(rs.getString("account"));
                master.setPassword(rs.getString("password"));
                master.setDid(rs.getString("did"));
                master.setPhone(rs.getString("phone"));
                master.setDel(rs.getString("del"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return master;
    }

    //全查
    public List<Master> selectAll() {
        ArrayList<Master> masters = new ArrayList<>();
        //1.创建出 连接对象
        Connection connection = DBHelper.getConnection();
        //2.创建出SQL语句
        String sql = "select * from master";
        //3.使用连接对象 获取 预编译对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            //4.执行预编译，得到结果集
            rs = ps.executeQuery();
            //5.遍历结果集
            while (rs.next()) {
                Master master = new Master();
                master.setId(rs.getInt("id"));
                master.setName(rs.getString("name"));
                master.setSex(rs.getString("sex"));
                master.setAge(rs.getString("age"));
                master.setAccount(rs.getString("account"));
                master.setPassword(rs.getString("password"));
                master.setDid(rs.getString("did"));
                master.setPhone(rs.getString("phone"));
                master.setDel(rs.getString("del"));
                masters.add(master);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return masters;
    }

    //添加
    public int addMaster(Master master) {
        //1、步骤1、创建链接对象
        Connection con = DBHelper.getConnection();
        //2、sql语句因为添加的数据是变量 ，所以要用?代替
        String sql = "insert into master values (null,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        int i = 0;
        try {
            //3、预编译
            ps = con.prepareStatement(sql);
            ps.setString(1, master.getName());
            ps.setString(2, master.getSex());
            ps.setString(3, master.getAge());
            ps.setString(4, master.getAccount());
            ps.setString(5, master.getPassword());
            ps.setString(6, master.getDid());
            ps.setString(7, master.getPhone());
            ps.setString(8, master.getDel());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;

    }

    //修改
    public int update(Master master) {
        //1.创建出 连接对象
        Connection conn = DBHelper.getConnection();
        //2.创建出SQL语句
        String sql = "update master set name=?,sex=?,age=?,account=?,password=?,did=?,phone=?,del=? where id=?";
        //3.创建preparedStatement,执行sql
        PreparedStatement ps = null;
        int i = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, master.getName());
            ps.setString(2, master.getSex());
            ps.setString(3, master.getAge());
            ps.setString(4, master.getAccount());
            ps.setString(5, master.getPassword());
            ps.setString(6, master.getDid());
            ps.setString(7, master.getPhone());
            ps.setString(8, master.getDel());
            ps.setInt(9, master.getId());
            i = ps.executeUpdate();
            System.out.println("成功修改" + i + "条数据");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    //删除
    public int del(int id){
        //1.创建出 连接对象
        Connection conn = DBHelper.getConnection();
        //2.创建出SQL语句
        String sql = "delete from master where id=?";
        //3.创建preparedStatement,执行sql
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    //搜索
    public List<Master> selectAllParam(Map map){
        System.out.println(" 进入dao");
        System.out.println("map dao = " + map);
        for (Object o : map.keySet()) {
            System.out.println("o = " + o);
        }
        String name = (String) map.get("name");
        String age = (String) map.get("age");
        String account = (String) map.get("account");
        //如果说real_name 不为空
        //sql = select * from t_user where real_name like '%张%' limit ?,?
        //如果说real_name 不为空 type 不为空
        //sql = select * from t_user where real_name like '%张%' and type=1 limit ?,?
        //如果说都不为空
        //sql = select * from t_user where real_name like '%张%' and type=1 and username='%李%' limit ?,?


        List<Master> lists = new ArrayList<>();
        //1.加载连接
        Connection connection = DBHelper.getConnection();
        //2.书写sql语句
        String  sql ="  select  *  from master  where 1=1  "; // where 1=1  因为 有多余的 and
        if (null!=name&&name.length()>0){
            sql = sql + " and name   like  '%"+name+"%'   ";
        }
        if (null!=age&&age.length()>0){
            sql = sql + " and age   =  "+age+"   ";
        }
        if (null!=account&&account.length()>0){
            sql = sql + " and username   like  '%"+account+"%'   ";
        }
        sql = sql + " limit  ? ,  ?";
        System.out.println(" dao de limit sql = " + sql);

        PreparedStatement ps = null;
        ResultSet rs = null;
        //3.预编译
        try {
            ps = connection.prepareStatement(sql);
            //4.执行sql
            rs = ps.executeQuery();
            //5.遍历结果集
            while (rs.next()){
                System.out.println("username = " + rs.getString("username"));
                Master master = new Master();
                master = new Master();
                master.setId(rs.getInt("id"));
                master.setName(rs.getString("name"));
                master.setSex(rs.getString("sex"));
                master.setAge(rs.getString("age"));
                master.setAccount(rs.getString("account"));
                master.setPassword(rs.getString("password"));
                master.setDid(rs.getString("did"));
                master.setPhone(rs.getString("phone"));
                master.setDel(rs.getString("del"));
                lists.add(master);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }

    //测试
    public static void main(String[] args) {
        MasterDao masterDao = new MasterDao();
        //登录
//        Master master = masterDao.login("张三", "123456");
//        System.out.println("master = " + master);
        //全查
//        List<Master> masters = masterDao.selectAll();
//        for (Master master1 : masters) {
//            System.out.println("master1 = " + master1);
//        }
        //添加
//        Master master = new Master();
//        master.setName("小乔");
//        master.setSex("女");
//        master.setAge("18");
//        master.setAccount("xiaoqiao");
//        master.setPassword("123");
//        master.setDid("");
//        master.setPhone("15269753648");
//        master.setDel("0");
//        int i = masterDao.addMaster(master);
//        System.out.println("i = " + i);

        //修改
//        Master master = new Master();
//        master.setName("小乔");
//        master.setSex("男");
//        master.setAge("18");
//        master.setAccount("xiaoqiao");
//        master.setPassword("123");
//        master.setDid("");
//        master.setPhone("15269753648");
//        master.setDel("0");
//        master.setId(9);
//        int i = masterDao.update(master);
//        System.out.println("i = " + i);

        int del = masterDao.del(10);
        System.out.println("del = " + del);

    }
}
