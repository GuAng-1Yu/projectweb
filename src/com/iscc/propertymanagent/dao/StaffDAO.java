package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Staff;

import java.sql.ResultSet;
import java.util.List;

public interface StaffDAO {
    List<Staff> queryStaff();
    List<Staff> queryStaff(Staff staff);
    int addStaff(Staff staff);
}
