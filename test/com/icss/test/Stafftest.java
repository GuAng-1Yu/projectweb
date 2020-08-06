package com.icss.test;

import com.iscc.propertymanagent.domain.Staff;

public class Stafftest {
    public static void main(String[] args) {
        Staff staff = new Staff();
        staff.setDeptid(11);
        System.out.println(staff.getDeptid());
    }
}
