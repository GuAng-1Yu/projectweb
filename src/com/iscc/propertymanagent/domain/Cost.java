package com.iscc.propertymanagent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.PrintWriter;
import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cost implements Serializable {
    private int costid;
    private int houseid;
    private double costprice;
    private int typeid;

    public Cost(int houseid, double costprice, int typeid) {
        this.houseid = houseid;
        this.costprice = costprice;
        this.typeid = typeid;
    }

}
