package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.util.DataSourceUtil;
import sun.security.util.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO login(account,password) VALUES(?,?)";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            System.out.println("1232245");
            conn = DataSourceUtil.getConnection();
            System.out.println("1232241");
            //获取Connection对象
            System.out.println(conn);
            //获取预处理对象 PreparedStatement
            psmt = conn.prepareStatement(sql);
            //如果SQL中有占位符，给占位符赋值，没有过。
            psmt.setString(1, user.getAccount());
            psmt.setString(2, user.getPassword());
//            psmt.setString(3, user.getPhone());
//            psmt.setString(4, user.getAccount());
            //执行SQL: 查询- executeQuery, 增删改- executeUpdate() : int 影响行数;
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }

    @Override
    public User serchUser(String account) {
        String sql = "select * from login where account = ?";
//        SELECT * FROM login WHERE account='zzz'
        User user = null;
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs=null;
        try {
            conn=DataSourceUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, account);
             rs = pre.executeQuery();
//
            if (rs.next()) {
                user=new User();
                user.setAccount(rs.getString(1));
                user.setPassword(rs.getString(2));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs,pre,conn);
        }

        return user;
    }

    @Override
    public Household holdlogin(int holdid) {
        String sql = "select * from household_info where holdid = ? ";
//        SELECT * FROM login WHERE account='zzz'
        Household Household = null;
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs=null;
        try {
            conn=DataSourceUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, holdid);
            rs = pre.executeQuery();
            if (rs.next()) {
                Household=new Household();
                Household.setHoldid(rs.getInt(1));
                Household.setHouseid(rs.getInt(2));
                Household.setHoldpwd(rs.getString(5));
                System.out.println(Household);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs,pre,conn);
        }
        return Household;
    }

    @Override
    public Staff stafflogin(int staffid) {
        String sql = " select * from staff_info where staffid = ? ";
//        SELECT * FROM login WHERE account='zzz'
        Staff staff=null;
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs=null;
        try {
            conn=DataSourceUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, staffid);
            rs = pre.executeQuery();
            if (rs.next()) {
                staff=new Staff();
                staff.setStaffid(rs.getInt(1));
                staff.setStaffname(rs.getString(2));
                staff.setStafftel(rs.getString(3));
                staff.setDeptid(rs.getInt(4));
                staff.setStafflev(rs.getInt(5));
                System.out.println(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs,pre,conn);
        }
        return staff;
    }


}

