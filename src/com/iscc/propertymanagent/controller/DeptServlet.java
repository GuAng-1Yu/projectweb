package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.service.impl.DeptServiceImpl;

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

@WebServlet({"/query_dept.do", "/add_dept.do", "/update_dept.do", "/del_dept.do", "/dept_page.do"})
public class DeptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        DeptServiceImpl deptService = new DeptServiceImpl();
        HashMap<String, Object> resultMap = new HashMap<>();
        if ("dept_page.do".equals(action)) {
            int currPage = 1;
            int pageNum = 5;
            currPage = Integer.parseInt(request.getParameter("currPage"));
            List<Dept> depts = deptService.queryDept();
            Pager<Dept> pager = new Pager<>(currPage, 5, depts);
            List<Dept> maps = deptService.queryDeptWithPage(pager);
            if (depts.size() > 0) {
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
        out.print(resultStr);
    }
}
