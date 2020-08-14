package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.CosttypeDAO;
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


public class CosttypeDAOImpl implements CosttypeDAO {
    @Override
    public int add(Costtype costtype) {
        String sql = "INSERT INTO costtype(typename) VALUES(?)";

        int result = -1;
        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, costtype.getTypename());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public int update(Costtype costtype) {
        String sql = "UPDATE costtype SET typename = ? WHERE typeid =?";
        int result = -1;
        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, costtype.getTypename());
            psmt.setInt(2, costtype.getTypeid());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int del(int id) {
        String sql = "DELETE FROM costtype WHERE typeid = ?";
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
    public List<Costtype> queryAll() {
        return null;
    }

    @Override
    public Costtype queryById(int id) {
        Costtype costtype = null;
        String sql = "SELECT * FROM costtype where typeid =?";

        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                costtype = new Costtype();
                costtype.setTypeid(rs.getInt(1));
                costtype.setTypename(rs.getString(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return costtype;
    }

    @Override
    public List<Costtype> queryAllTypeByCondition(String name) {
        List<Costtype> types = new ArrayList<>();
        String sql = "SELECT * FROM costtype where 1=1";
        if (name != null && !"".equals(name.trim())) {
            sql += " and typename like concat('%',?,'%')";
        }

        try {
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            if (name != null && !"".equals(name.trim())) {
                psmt.setString(1, name);
            }
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Costtype type = new Costtype();
                type.setTypeid(rs.getInt(1));
                type.setTypename(rs.getString(2));

                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return types;
    }

    @Override
    public List<Costtype> queryAllTypeByCondition(Map<String, Object> params) {
        List<Costtype> types = new ArrayList<>();
        String sql = "SELECT * FROM costtype where 1=1";
        if (params == null || params.isEmpty()) {
            return null;
        }
        String name = params.get("typename") == null ? null : params.get("typename").toString();
        if (name != null && !"".equals(name.trim())) {
            sql += " and typename like concat('%',?,'%')";
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
                Costtype costtype = new Costtype();
                costtype.setTypeid(rs.getInt(1));
                costtype.setTypename(rs.getString(2));

                types.add(costtype);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return types;
    }

}
