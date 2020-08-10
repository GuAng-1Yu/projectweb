package com.icss.test;

import com.iscc.propertymanagent.domain.Staff;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stafftest {
    public static void main(String[] args) {
        Staff staff = new Staff();
        staff.setDeptid(11);
        System.out.println(staff.getDeptid());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        System.out.println("===========");
        System.out.println(date.getClass());
        String format = formatter.format(date);

    }
}
