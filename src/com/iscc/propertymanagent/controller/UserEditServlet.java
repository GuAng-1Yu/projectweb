package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.service.UserService;
import com.iscc.propertymanagent.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/UserEdit_z.do", "/UserPassword_edit.do"})
public class UserEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
//        System.out.println("1");
        Gson gosn = new Gson();
//        System.out.println("2");
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        Map<String, Object> resultMap = new HashMap<>();
        if ("".equals(action)) {
        } else if ("UserPassword_edit.do".equals(action)) {
            int holdid = Integer.parseInt( request.getParameter("holdid"));
            String password = request.getParameter("password");
            int key = Integer.parseInt(request.getParameter("key"));

            System.out.println(key+"+++++");
            if (key!=1){
                System.out.println(key);
                resultMap.put("code", 202);
                resultMap.put("msg", "密码格式不对！");
            }else {
                Household household = new Household(holdid, password);
              int  rs = userService.userPasswordedit(household);
                System.out.println("serverletrs"+rs);
                if (rs!=-1) {
                    resultMap.put("code", 200);
                    resultMap.put("msg", "修改密码成功");
//                resultMap.put("result", holdlogin);
                } else {
                    resultMap.put("code", 202);
                    resultMap.put("msg", "修改密码失败！");
                }

            }
            String str = gosn.toJson(resultMap);
            out.print(str);

        }
    }
}