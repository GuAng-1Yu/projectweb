package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.service.HouseholdService;
import com.iscc.propertymanagent.service.impl.HouseholdServiceImpl;

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

@WebServlet({"/query_all_household.do"})
public class HouseholdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();


        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/")+1);
        HouseholdService householdService = new HouseholdServiceImpl();
        Map<String,Object> resultMap = new HashMap<>();
        if("query_all_household.do".equals(action)){
            String name = request.getParameter("holdid");
            List<Household> households = householdService.queryAllTypeByCondition(name);
            if(households.size()>0){
                resultMap.put("code",200);
                resultMap.put("msg","查询数据成功");
                resultMap.put("result",households);
            }else {
                resultMap.put("code",201);
                resultMap.put("msg","暂无类别数据");
            }
        }

        String resultStr = gson.toJson(resultMap);
        out.print(resultStr);
    }
}
