package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.domain.House;
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
            conn = DataSourceUtil.getConnection();
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
        User user = null;
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, account);
            rs = pre.executeQuery();
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
        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setInt(1, holdid);
            rs = prst.executeQuery();
            if (rs.next()) {
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


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, prst, conn);
        }


        return resultmap;
    }

    @Override
    public List<Map<String, Object>> houseidcostQuery(int houseid, int typeid) {
        System.out.println("houseidcostQuery1");
        List<Map<String, Object>> costlist = new ArrayList<>();
        String sql = " SELECT * ,DATE_FORMAT(createTime,'%Y-%m-%d %H:%i:%s') ftime   FROM cost c ,costtype t WHERE c.typeid = t.typeid AND c.houseid = ? ";
        if (typeid != 0) {
            sql += " and c.typeid = ? ";
        }
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            if (typeid != 0) {
                psmt.setInt(1, houseid);
                psmt.setInt(2, typeid);
            } else {
                psmt.setInt(1, houseid);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> resultmap = new HashMap<>();
                resultmap.put("costid", rs.getInt(1));
                resultmap.put("houseid", rs.getInt(2));
                resultmap.put("costprice", rs.getString(3));
                resultmap.put("typeid", rs.getInt(4));
                resultmap.put("createTime", rs.getString(8));
                resultmap.put("typename", rs.getString(7));
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
    public List<Map<String, Object>> houseidcostQuery(Map<String, Object> params, int typeid) {


        List<Map<String, Object>> costlist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = " SELECT * , DATE_FORMAT(createTime,'%Y-%m-%d %H:%i:%s') ftime  FROM cost c ,costtype t WHERE c.typeid = t.typeid AND c.houseid = ? ";
        if (typeid != 0) {
            sql += " and c.typeid = ? ";
        }
        sql += " limit ?,?";
        int houseid = Integer.parseInt(params.get("houseid").toString());
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            if (typeid != 0) {
                psmt.setInt(1, houseid);
                psmt.setInt(2, typeid);
                psmt.setInt(3, ((Pager) params.get("page")).getStart());
                psmt.setInt(4, ((Pager) params.get("page")).getPageNum());
            } else {
                psmt.setInt(1, houseid);
                psmt.setInt(2, ((Pager) params.get("page")).getStart());
                psmt.setInt(3, ((Pager) params.get("page")).getPageNum());
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> resultmap = new HashMap<>();
                resultmap.put("costid", rs.getInt(1));
                resultmap.put("houseid", rs.getInt(2));
                resultmap.put("costprice", rs.getString(3));
                resultmap.put("typeid", rs.getInt(4));
                resultmap.put("creatTime", rs.getString(8));
                resultmap.put("typename", rs.getString(7));
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
    public int ueserEditPassword(Household household) {
        Connection conn = null;
        PreparedStatement prst = null;
        int rs = -1;
        String spl = " UPDATE household_info SET holdpwd =? WHERE holdid= ? ";
        System.out.println("ueserEditPassword");
        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(spl);
            prst.setString(1, household.getHoldpwd());
            prst.setInt(2, household.getHoldid());
            rs = prst.executeUpdate();
            System.out.println("ueserEditPassword");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(prst, conn);
        }
        return rs;

    }

    //zz
    @Override
    public int editHouse(Connection conn, House house) {
        PreparedStatement prst = null;
        int rs = -1;
        String spl = " UPDATE house_info SET housesta= ? WHERE houseid= ?";
        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(spl);
            prst.setInt(1, house.getHousesta());
            prst.setInt(2, house.getHouseid());
            rs = prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return rs;

    }

    @Override
    public int editHousehold(Connection conn, Household household) {

        PreparedStatement prst = null;
        int rs = -1;
        String spl = " UPDATE household_info SET holdtel = ? ,holdnum= ? WHERE holdid= ?";
        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(spl);
            prst.setString(1, household.getHoldtel());
            prst.setInt(2, household.getHoldnum());
            prst.setInt(3, household.getHoldid());
            rs = prst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return rs;

    }

    @Override
    public List<Map<String, Object>> holdnoticeQuery(int holdid, int typename, int timeNum) {
        List<Map<String, Object>> noticelist = new ArrayList<>();

        String sql = " SELECT * ,DATE_FORMAT(noticetime,'%Y-%m-%d %H:%i:%s') ftime    from   notice    WHERE   ";
        if (typename == 1) {
            sql += " ( holdid = ? OR holdid = ? )";
        } else if (typename == 2 || typename == 3) {
            sql += "holdid = ?";
        }
        if (timeNum != 0) {
            sql += " AND noticetime>DATE_SUB(NOW(),INTERVAL ? DAY)";
        }
        sql += " GROUP BY noticetime DESC";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            if ((typename == 1) & (timeNum != 0)) {
                psmt.setInt(1, holdid);
                psmt.setInt(2, 0);
                psmt.setInt(3, timeNum);
            } else if ((typename == 1) & (timeNum == 0)) {
                psmt.setInt(1, holdid);
                psmt.setInt(2, 0);
            } else if (typename == 2 & (timeNum != 0)) {
                psmt.setInt(1, 0);
                psmt.setInt(2, timeNum);
            } else if (typename == 3 & (timeNum != 0)) {
                psmt.setInt(1, holdid);
                psmt.setInt(2, timeNum);

            } else if (typename == 3 & (timeNum == 0)) {
                psmt.setInt(1, holdid);
            } else if (typename == 2 & (timeNum == 0)) {
                psmt.setInt(1, 0);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> resultmap = new HashMap<>();
                resultmap.put("noticeid", rs.getInt(1));
                resultmap.put("noticecon", rs.getString(2));
                resultmap.put("holdid", rs.getInt(4));
                resultmap.put("noticetime", rs.getString(5));
                noticelist.add(resultmap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, psmt, conn);
        }
        return noticelist;
    }

    @Override
    public List<Map<String, Object>> holdnoticeQuery(Map<String, Object> params, int holdid, int typename, int timeNum) {
        List<Map<String, Object>> noticelist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = " SELECT * ,DATE_FORMAT(noticetime,'%Y-%m-%d %H:%i:%s') ftime    from   notice  WHERE  ";
        if (typename == 0 || typename == 1) {
            sql += " ( holdid =? OR holdid =?) ";
        } else if (typename == 2 || typename == 3) {
            sql += " holdid =?";
        }
        if (timeNum != 0) {
            sql += " AND   noticetime>DATE_SUB(NOW(),INTERVAL ? DAY) ";
        }
        sql += " GROUP BY noticetime DESC ";
        sql += " limit ?,?";
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            if ((typename == 1) & (timeNum != 0)) {
                psmt.setInt(1, holdid);
                psmt.setInt(2, 0);
                psmt.setInt(3, timeNum);
                psmt.setInt(4, ((Pager) params.get("page")).getStart());
                psmt.setInt(5, ((Pager) params.get("page")).getPageNum());

            } else if ((typename == 1) & (timeNum == 0)) {
                psmt.setInt(1, holdid);
                psmt.setInt(2, 0);
                psmt.setInt(3, ((Pager) params.get("page")).getStart());
                psmt.setInt(4, ((Pager) params.get("page")).getPageNum());
            } else if (typename == 2 & (timeNum != 0)) {
                psmt.setInt(1, 0);
                psmt.setInt(2, timeNum);
                psmt.setInt(3, ((Pager) params.get("page")).getStart());
                psmt.setInt(4, ((Pager) params.get("page")).getPageNum());
            } else if (typename == 3 & (timeNum != 0)) {
                psmt.setInt(1, holdid);
                psmt.setInt(2, timeNum);
                psmt.setInt(3, ((Pager) params.get("page")).getStart());
                psmt.setInt(4, ((Pager) params.get("page")).getPageNum());
            } else if (typename == 3 & (timeNum == 0)) {
                psmt.setInt(1, holdid);
                psmt.setInt(2, ((Pager) params.get("page")).getStart());
                psmt.setInt(3, ((Pager) params.get("page")).getPageNum());

            } else if (typename == 2 & (timeNum == 0)) {
                psmt.setInt(1, 0);
                psmt.setInt(2, ((Pager) params.get("page")).getStart());
                psmt.setInt(3, ((Pager) params.get("page")).getPageNum());
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> resultmap = new HashMap<>();
                resultmap.put("noticeid", rs.getInt(1));
                resultmap.put("noticecon", rs.getString(2));
                resultmap.put("holdid", rs.getInt(4));
                resultmap.put("noticetime", rs.getString(5));
                noticelist.add(resultmap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, psmt, conn);
        }
        return noticelist;

    }

}
