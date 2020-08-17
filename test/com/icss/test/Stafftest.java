package com.icss.test;

import com.iscc.propertymanagent.dao.impl.StaffDaoImpl;
import com.iscc.propertymanagent.domain.Staff;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stafftest {
    public static void main(String[] args) {
        StaffDaoImpl staffDao = new StaffDaoImpl();
        Staff staff = new Staff(4,"员工4","10000",1,1);
        int temp = staffDao.addStaff(staff);
        System.out.println(temp);
    }
}
