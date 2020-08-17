package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.domain.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StaffDAO{
    List<Map> queryStaff();
    List<Map> queryStaffWithPage(Pager pager);
    int addStaff(Staff staff) throws SQLException;
    int updateStaff(Staff staff);
    int delStaff(int id);
    List<Dept> queryAllDept();
}
