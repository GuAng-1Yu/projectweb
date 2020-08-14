package com.iscc.propertymanagent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Household implements Serializable {
    private int holdid;
    private int houseid;
    private String holdtel;
    private int holdnum;
    private String holdpwd;

    public Household(int houseid, String holdtel, int holdnum, String holdpwd) {
        this.houseid = houseid;
        this.holdtel = holdtel;
        this.holdnum = holdnum;
        this.holdpwd = holdpwd;
    }

    public Household(int holdid, String holdpwd) {
        this.holdid = holdid;
        this.holdpwd = holdpwd;
    }
}
