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
public class House implements Serializable {
    private int houseid;
    private int buildingid ;
    private int  unitid;
    private String  numberid;
    private int  housesta;
}
