package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.util.DataSourceUtil;
import lombok.experimental.var;
import sun.security.util.Password;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO login(account,password) VALUES(?,?)";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
//            System.out.println("1232245");
            conn = DataSourceUtil.getConnection();
//            System.out.println("1232241");
            //获取Connection对象
//            System.out.println(conn);
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
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, account);
            rs = pre.executeQuery();
//
            if (rs.next()) {
                user = new User();
                user.setAccount(rs.getString(1));
                user.setPassword(rs.getString(2));

                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, pre, conn);
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
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, holdid);
            rs = pre.executeQuery();
            if (rs.next()) {
                Household = new Household();
                Household.setHoldid(rs.getInt(1));
                Household.setHouseid(rs.getInt(2));
                Household.setHoldtel(rs.getString(3));
                Household.setHoldnum(rs.getInt(4));
                Household.setHoldpwd(rs.getString(5));
                System.out.println(Household);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, pre, conn);
        }
        return Household;
    }

    @Override
    public Staff stafflogin(int staffid) {
        String sql = " select * from staff_info where staffid = ? ";
//        SELECT * FROM login WHERE account='zzz'
        Staff staff = null;
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, staffid);
            rs = pre.executeQuery();
            if (rs.next()) {
                staff = new Staff();
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
            DataSourceUtil.releaseResource(rs, pre, conn);
        }
        return staff;
    }

    @Override
    public List<Map<String, Object>> holdinfoQuery(int holdid) {

        return null;
    }

    @Override
    public Map<String, Object> detailQuery(int holdid) {
        String sql = " SELECT * FROM household_info h , house_info m WHERE h.houseid = m.houseid AND holdid = ? ";
        Map<String, Object> resultmap = null;
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        System.out.println("*****");
//        System.out.println();


        System.out.println("*****");

        try {
            conn = DataSourceUtil.getConnection();
//            System.out.println("123");
//            System.out.println(holdid);
            prst = conn.prepareStatement(sql);
            prst.setInt(1, holdid);
            rs = prst.executeQuery();
            if (rs.next()) {
                System.out.println("servlet");
                resultmap = new HashMap<>();
                resultmap.put("holdid", rs.getInt(1));
                resultmap.put("houseid", rs.getInt(2));
                resultmap.put("holdtel", rs.getString(3));
                resultmap.put("holdnum", rs.getInt(4));
                resultmap.put("holdpwb", rs.getString(5));
                resultmap.put("buildingid", rs.getInt(7));
                resultmap.put("unitid", rs.getInt(8));
                resultmap.put("numberid", rs.getInt(9));
                String housesta = "str";
                if (rs.getInt(10) == 0) {
                    housesta = "无人";
                } else if (rs.getInt(10) == 1) {
                    housesta = "正常";
                } else if (rs.getInt(10) == 2) {
                    housesta = "出租";
                } else {
                    housesta = "-";
                }

                resultmap.put("housesta", housesta);
                System.out.println(resultmap);
//                System.out.println("servlet");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, prst, conn);
        }


        return resultmap;
    }

    @Override
    public List<Map<String, Object>> houseidcostQuery(int houseid) {
        System.out.println("houseidcostQuery1");
        List<Map<String, Object>> costlist = new ArrayList<>();
        String sql = " SELECT * FROM cost c ,costtype t WHERE c.typeid = t.typeid AND c.houseid = ? ";
//        if (name != null && !"".equals(name.trim())) {
//            sql += " and tname like concat('%',?,'%')";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
//        }
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            if (houseid != 0 && !"".equals(houseid)) {
                psmt.setInt(1, houseid);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> resultmap = new HashMap<>();
                resultmap.put("costid", rs.getInt(1));
                resultmap.put("houseid", rs.getInt(2));
                resultmap.put("costprice", rs.getString(3));
                resultmap.put("typeid", rs.getInt(4));
                resultmap.put("typename", rs.getString(6));
                costlist.add(resultmap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, psmt, conn);
        }
        return costlist;
    }

    @Override
    public List<Map<String, Object>> houseidcostQuery(Map<String, Object> params) {
//        System.out.println("houseidcostQuery2");

        List<Map<String, Object>> costlist = new ArrayList<>();
//        String sql = "SELECT * FROM tab_type where 1=1";
//        if (params == null || params.isEmpty()) {
//            return null;
//        }
//        String name = params.get("tname") == null ? null : params.get("tname").toString();
//        if (name != null && !"".equals(name.trim())) {
//            sql += " and tname like concat('%',?,'%')";
//        }
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM cost c ,costtype t WHERE c.typeid = t.typeid AND c.houseid = ? limit ?, ?  ";
//        sql += " limit ?,?";
        int houseid = Integer.parseInt( params.get("houseid").toString());
        System.out.println(houseid);
//                params.get("houseid") == null ? null : params.get("houseid").toString();

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            if (houseid != 0) {

//                System.out.println(params.get("page"));
//                System.out.println(params.get("page"));

                psmt.setInt(1, houseid);
            psmt.setInt(2, ((Pager) params.get("page")).getStart());
            psmt.setInt(3, ((Pager) params.get("page")).getPageNum());
            }
//            else {
//                psmt.setInt(1, ((Pager) params.get("page")).getStart());
//                psmt.setInt(2, ((Pager) params.get("page")).getPageNum());
//            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> resultmap = new HashMap<>();
                resultmap.put("costid", rs.getInt(1));
                resultmap.put("houseid", rs.getInt(2));
                resultmap.put("costprice", rs.getString(3));
                resultmap.put("typeid", rs.getInt(4));
                resultmap.put("typename", rs.getString(6));
                costlist.add(resultmap);
//                System.out.println("resultmap");
//                System.out.println(resultmap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 DataSourceUtil.releaseResource(rs,psmt,conn);
        }
        return costlist;


    }

    @Override
    public int ueserEditPassword(Household household) {

        Connection conn = null;
        PreparedStatement prst = null;
        int rs = -1;
        String spl = " UPDATE household_info SET holdpwd =? WHERE holdid= ? ";
        System.out.println("ueserEditPassword");
        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(spl);
            prst.setString(1,household.getHoldpwd());
            prst.setInt(2, household.getHoldid());

            rs = prst.executeUpdate();
            System.out.println("ueserEditPassword");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(prst, conn);
        }
        System.out.println("rs"+rs);
        return rs;

    }

}
