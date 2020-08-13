package com.iscc.propertymanagent.dao.impl;


import com.iscc.propertymanagent.dao.StaffDAO;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.util.DataSourceUtil;

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
    public List<Staff> queryStaff() {
//        String sql = "SELECT * FROM staff_info s ,dept d WHERE s.`deptid` = d.`deptid`";
        String sql = "SELECT * FROM staff_info";
        List<Staff> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Map<String,Object> resultMap = new HashMap<>();
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffid(rs.getInt(1));
                staff.setStaffname(rs.getString(2));
                staff.setStafftel(rs.getString(3));
                staff.setDeptid(rs.getInt(4));
                staff.setStafflev(rs.getInt(5));
                results.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
        return result;
    }
}
