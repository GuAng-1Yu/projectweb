package com.iscc.propertymanagent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Staff implements Serializable {

    private int staffid;
    private String staffname;
    private String stafftel;
    private int deptid;
    private int stafflev;

    public Staff(String staffname, String stafftel, int deptid, int stafflev) {
        this.staffname = staffname;
        this.stafftel = stafftel;
        this.deptid = deptid;
        this.stafflev = stafflev;
    }

    /*public Staff(int staffid, String staffname, String stafftel, int deptid, int stafflev) {
        this.staffid = staffid;
        this.staffname = staffname;
        this.stafftel = stafftel;
        this.deptid = deptid;
        this.stafflev = stafflev;
    }*/
}
