package com.iscc.propertymanagent.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.service.HouseholdService;
import com.iscc.propertymanagent.service.impl.HouseholdServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Equals;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/searchHousehold.do","/searchHouseholdById.do","/addHousehold.do","/deleteHousehold.do","/updateHousehold.do","/query_page.do"})
public class HouseholdServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        HouseholdService HouseholdService = new HouseholdServiceImpl();
        HashMap<Object, Object> resultMap = new HashMap<>();
        if ("searchHousehold.do".equals(action)) {

            List<Household> resultSet = HouseholdService.searchHouseholdAll();

            if (resultSet.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询成功");
                resultMap.put("result", resultSet);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "暂无数据");
            }

            String resultStr = gson.toJson(resultMap);
            out.print(resultStr);

        } else if ("searchHouseholdById.do".equals(action)) {

            List<Household> resultSet = HouseholdService.searchHouseholdById(Integer.parseInt(request.getParameter("householdid")));

            if (resultSet.size() > 0) {
                resultMap.put("householdInfoById", resultSet);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "暂无数据");
            }

            String resultStr = gson.toJson(resultMap);
            out.print(resultStr);

        } else if ("addHousehold.do".equals(action)) {

            int houseId = Integer.parseInt(request.getParameter("houseId"));
            String holdTel = request.getParameter("holdTel");
            int holdNum = Integer.parseInt(request.getParameter("holdNum"));
            String holdPwd = request.getParameter("holdPwd");

            Household household = new Household(houseId, holdTel, holdNum, holdPwd);
            int result = HouseholdService.addHousehold(household);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "添加成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "添加失败");
            }
        } else if ("deleteHousehold.do".equals(action)) {
            int holdid = Integer.parseInt(request.getParameter("holdid"));
            int result = HouseholdService.deleteHouseholdById(holdid);
            //int result = HouseholdService.deleteHouseholdById(Integer.parseInt(request.getParameter("holdid")));
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "删除成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "删除失败");
            }
        } else if ("query_page.do".equals(action)) {
            int currPage = 1;
            int pageNum = 5;
            currPage = Integer.parseInt(request.getParameter("currPage"));
            List<Household> resultSet = HouseholdService.searchHouseholdAll();
            Pager<Household> pager = new Pager<>(currPage, pageNum, resultSet);
            List<Household> households = HouseholdService.searchHouseholdAll(pager);
            try {
                currPage = Integer.parseInt(request.getParameter("currPage"));
            } catch (Exception e) {

            }
            try {
                currPage = Integer.parseInt(request.getParameter("pageNum"));
            } catch (Exception e) {

            }
            Map<String, Object> map = new HashMap<>();
            map.put("pager", pager);
            map.put("list", households);
            resultMap.put("code", 200);
            resultMap.put("msg", "查询类别成功");
            resultMap.put("result", map);

        } else if ("updateHousehold.do".equals(action)) {
            int holdId = Integer.parseInt(request.getParameter("holdId"));
            int houseId = Integer.parseInt(request.getParameter("houseId"));
            String holdTel = request.getParameter("holdTel");
            int holdNum = Integer.parseInt(request.getParameter("holdNum"));
            String holdPwd = request.getParameter("holdPwd");
            Household household = new Household(holdId,houseId,holdTel,holdNum,holdPwd);
            int result = HouseholdService.updateHousehold(household);
            if (result != -1){
                resultMap.put("code",200);
                resultMap.put("msg","信息修改成功");
            }

        }
            String resultStr = gson.toJson(resultMap);
            out.print(resultStr);

        }

}

