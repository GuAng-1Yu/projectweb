package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.service.UserService;
import com.iscc.propertymanagent.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/register.do","/login.do"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserServiceImpl();
        PrintWriter out = response.getWriter();
        // /MaoKe/register.do
        String uri = request.getRequestURI();
        Gson gosn = new Gson();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        Map<String, Object> resultMap = new HashMap<>();
        if ("register.do".equals(action)) {
            //执行注册操作
            String account = request.getParameter("account");
            String password = request.getParameter("password");
//            String phone = request.getParameter("phone");
            System.out.println(account + "   " + password);
            User user = new User(account, password);
            int result = userService.addUser(user);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "注册成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "注册失败");
            }
            System.out.println("resultMap = " + resultMap);
            String str = gosn.toJson(resultMap);
            System.out.println("str = " + str);
            out.print(str);
        }
  else  if ("login.do".equals(action)){
            System.out.println("????");
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            User use=null;
             use = userService.login(account);
            if (use!=null){
                if (use.getPassword().equals(password)){
                    resultMap.put("code",200);
                    resultMap.put("msg","登录成功");
                    resultMap.put("result",use);

                }else {
                    resultMap.put("code",202);
                    resultMap.put("msg","密码错误！");
                }
            }else {
                resultMap.put("code",203);
                resultMap.put("msg","账号或密码错误");
            }

            String str = gosn.toJson(resultMap);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
