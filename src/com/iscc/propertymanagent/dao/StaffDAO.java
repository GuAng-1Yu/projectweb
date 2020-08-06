package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Staff;

import java.sql.ResultSet;

public interface StaffDAO {
    int addStaff(Staff staff);
    ResultSet queryStaff();
    int updateStaff(Staff staff);
}
