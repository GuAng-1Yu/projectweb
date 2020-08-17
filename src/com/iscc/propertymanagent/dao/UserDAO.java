package com.iscc.propertymanagent.dao;


import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface UserDAO {

    int addUser(User user);

    public User serchUser(String account);

    public Household holdlogin(int holdid);
    public Staff stafflogin(int staffid);
    List<Map<String,Object>>  holdinfoQuery(int holdid);
    Map<String,Object>    detailQuery(int holdid);
    List<Map<String,Object>>  houseidcostQuery(int houseid,int typeid );
    List<Map<String,Object>>  houseidcostQuery(Map<String,Object>  costlist ,int typeid );
    int ueserEditPassword(Household household) ;

       int editHouse(Connection conn, House house);
       int editHousehold(Connection conn, Household household);
    List<Map<String,Object>>  holdnoticeQuery(int holdid,int typename,int timeNum );
    List<Map<String,Object>>  holdnoticeQuery(Map<String,Object>  costlist ,int holdid,int typename,int timeNum);

}
