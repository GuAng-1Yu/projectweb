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



}
