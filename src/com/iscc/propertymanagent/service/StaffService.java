package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> queryStaff();
    int addStaff(Staff staff);
    int updateStaff(Staff staff);
    int delStaff(int id);
}
