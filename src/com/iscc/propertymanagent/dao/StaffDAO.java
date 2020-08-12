package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StaffDAO{
    List<Staff> queryStaff();
    int addStaff(Staff staff) throws SQLException;
    int updateStaff(Staff staff);
    int delStaff(int id);
}
