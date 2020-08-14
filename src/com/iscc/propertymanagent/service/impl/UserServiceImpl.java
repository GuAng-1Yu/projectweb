package com.iscc.propertymanagent.service.impl;


import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.dao.impl.UserDAOImpl;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.service.UserService;

import java.util.List;
import java.util.Map;
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
    public Household holdlogin(int holdid) {
        return userDAO.holdlogin(holdid);
    }

    @Override
    public Staff stafflogin(int staffid) {
        return userDAO.stafflogin(staffid);
    }

    @Override
    public List<Map<String, Object>> holdinfoQuery(int holdid) {
        return userDAO.holdinfoQuery(holdid);
    }

    @Override
    public Map<String, Object> detailQuery(int holdid) {
        return userDAO.detailQuery(holdid);
    }

    @Override
    public List<Map<String,Object>> queryAllTypeByCondition(Map<String, Object> params) {
        return userDAO.houseidcostQuery(params);
    }

    @Override
    public List<Map<String,Object>> queryAllTypeByCondition(int houseid) {
        return userDAO.houseidcostQuery(houseid);
    }

    @Override
    public int userPasswordedit(Household household) {
        return  userDAO.ueserEditPassword(household);
    }


}
