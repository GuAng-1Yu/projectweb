package com.iscc.propertymanagent.dao.impl;


import com.iscc.propertymanagent.dao.StaffDAO;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDAO {

    @Override
    public List<Staff> queryStaff() {
        String sql = "SELECT * FROM staff_info";

        List<Staff> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()){
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
    public List<Staff> queryStaff(Staff staff) {
        return null;
    }

    @Override
    public int addStaff(Staff staff) {
        return 0;
    }
}
