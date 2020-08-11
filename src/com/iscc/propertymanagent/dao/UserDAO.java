package com.iscc.propertymanagent.dao;


import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;

public interface UserDAO {

    int addUser(User user);

    public User serchUser(String account);

    public Household holdlogin(int holdid);
    public Staff stafflogin(int staffid);


}
