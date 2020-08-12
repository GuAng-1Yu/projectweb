package com.iscc.propertymanagent.dao;


import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    int addUser(User user);

    public User serchUser(String account);

    public Household holdlogin(int holdid);
    public Staff stafflogin(int staffid);
    List<Map<String,Object>>  holdinfoQuery(int holdid);
    Map<String,Object>    detailQuery(int holdid);


}
