package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.service.StaffService;
import com.iscc.propertymanagent.service.impl.StaffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet({"/query_staff.do","/add_Staff.do","/update_Staff.do","/del_Staff.do"})
public class StaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter writer = response.getWriter();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        StaffService staffService = new StaffServiceImpl();
        HashMap<Object, Object> resultMap = new HashMap<>();
        if ("query_staff.do".equals(action)) {
            List<Staff> resultSet = staffService.queryStaff();
            if (resultSet.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询成功");
                resultMap.put("result", resultSet);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "数据走丢了，请稍后重试");
            }
        }else if ("add_Staff.do".equals(action)) {
            System.out.println("add_staff.do");
        }else if ("update_Staff.do".equals(action)) {
            System.out.println("update_Staff.do");
        }else if ("del_Staff.do".equals(action)) {
            System.out.println("del_Staff.do");
        }
        String resultStr = gson.toJson(resultMap);
        writer.print(resultStr);
    }
}
