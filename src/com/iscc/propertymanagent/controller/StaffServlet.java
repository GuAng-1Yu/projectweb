package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;
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
import java.util.Map;

@WebServlet({"/query_staff.do", "/add_Staff.do", "/update_Staff.do", "/del_Staff.do", "/get_all_dept.do", "/staff_page.do"})
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
            /*List<Map> resultSet = staffService.queryStaff();
            if (resultSet.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询成功");
                resultMap.put("result", resultSet);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "数据走丢了，请稍后重试");
            }*/
        } else if ("add_Staff.do".equals(action)) {
            String staffName = request.getParameter("staffName");
            String staffTel = request.getParameter("staffTel");
            int deptId = Integer.parseInt(request.getParameter("deptId"));
            int staffLev = Integer.parseInt(request.getParameter("staffLev"));
            Staff staff = new Staff(staffName, staffTel, deptId, staffLev);
            int result = staffService.addStaff(staff);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "员工添加成功");
            }
        } else if ("update_Staff.do".equals(action)) {
            int staffId = Integer.parseInt(request.getParameter("staffId"));
            String staffName = request.getParameter("staffName");
            String staffTel = request.getParameter("staffTel");
            int deptId = Integer.parseInt(request.getParameter("deptId"));
            int staffLev = Integer.parseInt(request.getParameter("staffLev"));
            Staff staff = new Staff(staffId, staffName, staffTel, deptId, staffLev);
            int result = staffService.updateStaff(staff);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "信息修改成功");
            }
        } else if ("del_Staff.do".equals(action)) {
            int staffid = Integer.parseInt(request.getParameter("staffid"));
            int result = staffService.delStaff(staffid);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "删除成功");
            }
        } else if ("get_all_dept.do".equals(action)) {
            List<Dept> result = staffService.queryAllDept();
            if (result.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "部门查询成功");
                resultMap.put("result", result);
            }
        } else if ("staff_page.do".equals(action)) {
            int currPage = 1;
            int pageNum = 5;

            currPage = Integer.parseInt(request.getParameter("currPage"));

            List<Map> resultSet = staffService.queryStaff();
            Pager<Map> pager = new Pager<>(currPage, 5, resultSet);
            List<Map> maps = staffService.queryStaffWithPage(pager);
            if (resultSet.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "PageStaff查询成功");
                resultMap.put("result", maps);
                resultMap.put("pager", pager);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "数据走丢了，请稍后重试");
            }
        }
        String resultStr = gson.toJson(resultMap);
        writer.print(resultStr);
    }
}
