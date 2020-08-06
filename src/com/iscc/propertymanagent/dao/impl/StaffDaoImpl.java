package com.iscc.propertymanagent.dao.impl;


import com.iscc.propertymanagent.dao.StaffDAO;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDaoImpl implements StaffDAO {

    @Override
    public int addStaff(Staff staff) {
        return 0;
    }

    @Override
    public ResultSet queryStaff() {
        String sql = "SELECT * FROM staff_info";
        ResultSet result = null;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            result = psmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return  result;
    }

    @Override
    public int updateStaff(Staff staff) {
        return 0;
    }
}
