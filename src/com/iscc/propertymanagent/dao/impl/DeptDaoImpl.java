package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.DeptDao;
import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.awt.print.Paper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl implements DeptDao {
    @Override
    public List<Dept> queryDept() {
        String sql = "SELECT * FROM dept";
        List<Dept> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Dept dept = new Dept();
                dept.setDeptid(rs.getInt(1));
                dept.setDeptname(rs.getString(1));
                results.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return results;
    }

    @Override
    public List<Dept> queryDeptWithPage(Pager pager) {
        String sql = "SELECT * FROM dept LIMIT ?,?";
        List<Dept> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, pager.getStart());
            psmt.setInt(2, pager.getPageNum());
            rs = psmt.executeQuery();
            while (rs.next()) {
                Dept dept = new Dept();
                dept.setDeptid(rs.getInt(1));
                dept.setDeptname(rs.getString(2));
                results.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return results;
    }

    @Override
    public int addStaff(Dept dept) {
        String sql = "INSERT INTO dept VALUES (NULL,?)";
        int result = -1;
        PreparedStatement psmt = null;
        Connection conn = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dept.getDeptname());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }

    @Override
    public int updateDept(Dept dept) {
        String sql = "UPDATE dept SET deptname=? WHERE deptid=?";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dept.getDeptname());
            psmt.setInt(2, dept.getDeptid());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }

        return result;
    }


    @Override
    public int delDept(int id) {
        String sql = "DELETE FROM dept WHERE deptid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        int result = -1;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }
}
