package com.iscc.propertymanagent.service.impl;


import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.dao.impl.UserDAOImpl;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.service.UserService;

import java.util.Scanner;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User login(String account) {
        return userDAO.serchUser(account);

    }

    @Override
    public Household holdlogin(int pwd) {
        return userDAO.holdlogin(pwd);
    }

    @Override
    public Staff stafflogin(int staffid) {
        return userDAO.stafflogin(staffid);
    }


}
