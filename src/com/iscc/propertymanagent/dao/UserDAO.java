package com.iscc.propertymanagent.dao;


import com.iscc.propertymanagent.domain.User;

public interface UserDAO {

    int addUser(User user) ;
    public User serchUser(String account) ;

}
