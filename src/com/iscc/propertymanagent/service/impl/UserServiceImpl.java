package com.iscc.propertymanagent.service.impl;


import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.dao.impl.UserDAOImpl;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.service.UserService;

import java.util.Scanner;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public int addUser(User user)  {
        return userDAO.addUser(user);
    }

//    @Override
//    public User login(User user) {
//
//
//
//         return userDAO.serchUser(user);
//    }

//    @Override
//    public boolean login() {
//        Scanner sc= new Scanner(System.in);
//        System.out.println("请输入账户:");
//        String account = sc.next();
//        System.out.println("请输入密码:");
//        String password = sc.next();
//        User user = new User(account, password);
//        try {
//            if(userDAO.serchUser(user)){
//                System.out.println("登陆成功");
//                return true; }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

//    public static void main(String[] args) {
//        UserServiceImpl xx= new UserServiceImpl();
//        xx.login();
//
//    }
}
