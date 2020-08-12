package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.impl.StaffDaoImpl;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.service.StaffService;

import java.sql.ResultSet;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    StaffDaoImpl staffDao = new StaffDaoImpl();

    @Override
    public List<Staff> queryStaff() {
        return staffDao.queryStaff();
    }

    @Override
    public int addStaff(Staff staff) {
        return staffDao.addStaff(staff);
    }

    @Override
    public int updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
    }

    @Override
    public int delStaff(int id) {
        return staffDao.delStaff(id);
    }
}
