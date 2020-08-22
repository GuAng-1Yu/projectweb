package com.iscc.propertymanagent.service.impl;


import com.iscc.propertymanagent.dao.CostDAO;
import com.iscc.propertymanagent.dao.CosttypeDAO;
import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.dao.impl.CosttypeDAOImpl;
import com.iscc.propertymanagent.dao.impl.UserDAOImpl;
import com.iscc.propertymanagent.domain.Costtype;
import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.service.UserService;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();
    private CosttypeDAO costtypeDAO =new CosttypeDAOImpl();

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
    public List<Map<String,Object>> queryAllTypeByCondition(Map<String, Object> params ,int typeid) {
        return userDAO.houseidcostQuery(params , typeid);
    }

    @Override
    public List<Map<String,Object>> queryAllTypeByCondition(int houseid ,int typeid) {
        return userDAO.houseidcostQuery(houseid,typeid);
    }

    @Override
    public int userPasswordedit(Household household) {
        return  userDAO.ueserEditPassword(household);
    }

    @Override
    public List<Costtype> getcostbycostid(String typename) {
        return costtypeDAO.queryAllTypeByCondition( typename);
    }

    @Override
    public int uesredit(Household household, House house) {

        Connection conn = null;
        int result = -1;
        try {
            conn = DataSourceUtil.getConnection();
            conn.setAutoCommit(false);

            int houseRs=userDAO.editHouse(conn,house);
            int householdRs=userDAO.editHousehold(conn,household);


            if (houseRs != -1 && householdRs != -1) {
                conn.commit();
                result = 1;
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public List<Map<String, Object>> holdnoticeQuery(Map<String, Object> params, int holdid, int typename, int timeNum) {
        return userDAO.holdnoticeQuery(params,holdid,typename,timeNum);
    }

    @Override
    public List<Map<String, Object>> holdnoticeQuery(int holdid, int typename, int timeNum) {
        return userDAO.holdnoticeQuery(holdid,typename,timeNum);
    }

    @Override
    public List<Costtype> getcostbycostid(int typeid) {
        return null;
    }


}
