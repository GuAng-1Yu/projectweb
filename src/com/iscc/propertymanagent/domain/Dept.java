package com.iscc.propertymanagent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {
    private int deptid;
    private String deptname;

    public Dept(String deptname) {
        this.deptname = deptname;
    }

}
