package com.iscc.propertymanagent.domain;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private int uno;
    private String account;
    private String password;
    private String phone;
    private int ano;
    private String uimg;
    private int states;
    private int integral;
    private String birth;
    private String uname;
    private String temp1;
    private int roleid;
    private int aa;

    public User(String account, String password, String phone) {
        this.account = account;
        this.password = password;
        this.phone = phone;
    }
    public User(String account, String password) {
        this.account = account;
        this.password = password;

    }
}
