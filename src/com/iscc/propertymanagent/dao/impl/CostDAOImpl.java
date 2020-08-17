package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.CostDAO;
import com.iscc.propertymanagent.domain.Cost;
import com.iscc.propertymanagent.domain.Costtype;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CostDAOImpl implements CostDAO {
    @Override
    public int add(Connection conn, Cost cost) {
        int result = -1;
        String sql = "INSERT INTO cost(houseid,costprice,typename) VALUES(?,?,?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, cost.getHouseid());
            psmt.setDouble(2, cost.getCostprice());
            psmt.setString(3, cost.getTypename());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getNewId(Connection conn) {
        int id = 0;
        String sql = "SELECT MAX(costid) FROM cost";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                if (String.valueOf(rs.getInt(1)) != null) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id + 1;
    }

    @Override
    public List<Cost> queryAllTypeByCondition(String name) {
        List<Cost> list = new ArrayList<>();
        String sql = " SELECT c.costid,c.houseid,c.costprice,c.typename,c.createTime FROM cost c";
        if (name != null && !"".equals(name)) {
            sql += " where c.houseid like concat('%',?,'%')";
        }
        sql += " ORDER BY c.costid asc";

        System.out.println("sql = " + sql);

        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            if (name != null && !"".equals(name)) {
                psmt.setString(1, name);
            }

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Cost cost = new Cost();

                cost.setCostid(rs.getInt(1));
                cost.setHouseid(rs.getInt(2));
                cost.setCostprice(rs.getDouble(3));
                cost.setTypename(rs.getString(4));
                cost.setCreateTime(rs.getString(5));
                list.add(cost);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return list;
    }

    @Override
    public List<Cost> queryAllTypeByCondition(Map<String, Object> params) {
        List<Cost> types = new ArrayList<>();
        String sql = "SELECT * FROM cost where 1=1";
        if (params == null || params.isEmpty()) {
            return null;
        }
        String name = params.get("houseid") == null ? null : params.get("houseid").toString();
        if (name != null && !"".equals(name.trim())) {
            sql += " and houseid like concat('%',?,'%')";
        }
        sql += " limit ?,?";

        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            if (name != null && !"".equals(name.trim())) {
                psmt.setString(1, name);
                psmt.setInt(2, ((Pager) params.get("page")).getStart());
                psmt.setInt(3, ((Pager) params.get("page")).getPageNum());
            } else {
                psmt.setInt(1, ((Pager) params.get("page")).getStart());
                psmt.setInt(2, ((Pager) params.get("page")).getPageNum());
            }
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Cost cost = new Cost();
                cost.setCostid(rs.getInt(1));
                cost.setHouseid(rs.getInt(2));
                cost.setCostprice(rs.getDouble(3));
                cost.setTypename(rs.getString(4));
                cost.setCreateTime(rs.getString(5));

                types.add(cost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return types;
    }


    @Override
    public int add(Cost cost) {
        String sql = "INSERT INTO cost(houseid,costprice,typename) VALUES(?,?,?)";

        int result = -1;
        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, cost.getHouseid());
            psmt.setDouble(2,cost.getCostprice());
            psmt.setString(3,cost.getTypename());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;

    }

    @Override
    public int update(Cost cost) {
        String sql = "UPDATE cost SET houseid = ?,costprice=?,typename=?WHERE costid =?";
        int result = -1;
        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, cost.getHouseid());
            psmt.setDouble(2, cost.getCostprice());
            psmt.setString(3,cost.getTypename());
            psmt.setInt(4,cost.getCostid());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    @Override
    public int del(int id) {
        String sql = "DELETE FROM cost WHERE costid = ?";
        int result = -1;
        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Cost> queryAll() {
        return null;
    }

    @Override
    public Cost queryById(int id) {
        Cost cost = null;
        String sql = " SELECT * FROM cost WHERE costid =?";

        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                cost = new Cost();
                cost.setCostid(rs.getInt(1));
                cost.setHouseid(rs.getInt(2));
                cost.setCostprice(rs.getDouble(3));
                cost.setTypename(rs.getString(4));
                cost.setCreateTime(rs.getString(5));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return cost;
    }
}
