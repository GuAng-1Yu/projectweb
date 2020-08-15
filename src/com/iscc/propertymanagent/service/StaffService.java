package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Staff;

import java.util.List;
import java.util.Map;

public interface StaffService {
    List<Map> queryStaff();
    int addStaff(Staff staff);
    int updateStaff(Staff staff);
    int delStaff(int id);
    List<Dept> queryAllDept();
}
