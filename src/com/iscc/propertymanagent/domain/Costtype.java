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
public class Costtype implements Serializable {
    private  int typeid ;
    private  String typename;

    public Costtype(String name) {
        this.typename=name;
    }
}
