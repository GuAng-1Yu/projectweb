package com.iscc.propertymanagent.service;


import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int addUser(User user);

    User login(String account);

    Household holdlogin(int holdid);

    Staff stafflogin(int staffid);
 List<Map<String,Object>> holdinfoQuery(int holdid);

}
