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
    private  String createTime;
    private String typename;

    public Cost(int houseid, double costprice, int typeid, String createTimeTime) {
        this.houseid = houseid;
        this.costprice = costprice;
        this.typeid = typeid;
        this.createTime=createTime;
    }
    public Cost(int houseid, double costprice, int typeid) {
        this.houseid = houseid;
        this.costprice = costprice;
        this.typeid = typeid;

    }
    public Cost(int houseid, double costprice,String typename) {
        this.houseid = houseid;
        this.costprice = costprice;
        this.typename=typename;
    }
    public Cost(int costid,  int houseid , double costprice, int typeid) {
        this.houseid = houseid;
        this.costprice = costprice;
        this.typeid = typeid;
        this.costid=costid;}

    public Cost(int costid, int houseid, double costprice, String createTime, String typename) {
        this.costid = costid;
        this.houseid = houseid;
        this.costprice = costprice;
        this.createTime = createTime;
        this.typename = typename;
    }
}


