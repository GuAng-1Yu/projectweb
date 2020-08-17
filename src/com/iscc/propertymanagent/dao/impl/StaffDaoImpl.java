package com.iscc.propertymanagent.dao.impl;


import com.iscc.propertymanagent.dao.StaffDAO;
import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.util.DataSourceUtil;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffDaoImpl implements StaffDAO {

    @Override
    public List<Map> queryStaff() {
        String sql = "SELECT * FROM staff_info s,dept d WHERE s.deptid = d.deptid";
        List<Map> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("staffid",rs.getInt(1));
                resultMap.put("staffname",rs.getString(2));
                resultMap.put("stafftel",rs.getString(3));
                resultMap.put("deptid0",rs.getInt(4));
                resultMap.put("stafflev",rs.getInt(5));
                resultMap.put("deptid1",rs.getInt(6));
                resultMap.put("deptname",rs.getString(7));
                results.add(resultMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return results;
    }

    @Override
    public List<Map> queryStaffWithPage(Pager pager) {

        String sql = "SELECT * FROM staff_info s,dept d WHERE s.deptid = d.deptid LIMIT ?,?";
        List<Map> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,pager.getStart());
            psmt.setInt(2,pager.getPageNum());
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("staffid",rs.getInt(1));
                resultMap.put("staffname",rs.getString(2));
                resultMap.put("stafftel",rs.getString(3));
                resultMap.put("deptid0",rs.getInt(4));
                resultMap.put("stafflev",rs.getInt(5));
                resultMap.put("deptid1",rs.getInt(6));
                resultMap.put("deptname",rs.getString(7));
                results.add(resultMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return results;
    }

    @Override
    public int addStaff(Staff staff) {
        String sql = "INSERT INTO staff_info VALUES (NULL,?,?,?,?)";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, staff.getStaffname());
            psmt.setString(2, staff.getStafftel());
            psmt.setInt(3, staff.getDeptid());
            psmt.setInt(4, staff.getStafflev());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }

    @Override
    public int updateStaff(Staff staff) {
        String sql = "UPDATE staff_info SET staffname= ?, stafftel = ?, deptid = ?, stafflev = ? WHERE staffid = ?";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,staff.getStaffname());
            psmt.setString(2,staff.getStafftel());
            psmt.setInt(3,staff.getDeptid());
            psmt.setInt(4,staff.getStafflev());
            psmt.setInt(5,staff.getStaffid());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }

    @Override
    public int delStaff(int id) {
        String sql = "DELETE FROM staff_info WHERE staffid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        int result = -1;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }

    @Override
    public List<Dept> queryAllDept() {
        String sql = "SELECT * FROM dept";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ArrayList<Dept> depts = new ArrayList<>();
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()){
                Dept dept = new Dept();
                dept.setDeptid(rs.getInt(1));
                dept.setDeptname(rs.getString(2));
                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return depts;
    }
}
