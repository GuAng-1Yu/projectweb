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

public class HouseholdServlet {
 @WebServlet({"/searchHousehold.do","/searchHouseholdById.do","/addHousehold.do","/deleteHousehold.do","/updateHousehold.do"})
    public class HouseServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Gson gson = new Gson();
            PrintWriter writer = response.getWriter();
            String uri = request.getRequestURI();
            String action = uri.substring(uri.lastIndexOf("/") + 1);
            HouseholdService HouseholdService = new HouseholdServiceImpl();
            HashMap<Object, Object> resultMap = new HashMap<>();
            if ("queryHouseholdAll.do".equals(action)) {

                List<Household> resultSet = HouseholdService.searchHouseholdAll();

                if (resultSet.size() > 0) {
                    resultMap.put("householdAllInfo", resultSet);
                } else {
                    resultMap.put("code", 999);
                    resultMap.put("msg", "暂无数据");
                }

                String resultStr = gson.toJson(resultMap);
                writer.print(resultStr);

            }else if("searchHouseholdById.do".equals(action)){

                List<Household> resultSet = HouseholdService.searchHouseholdById(Integer.parseInt(request.getParameter("householdid")));

                if (resultSet.size() > 0) {
                    resultMap.put("householdInfoById", resultSet);
                } else {
                    resultMap.put("code", 999);
                    resultMap.put("msg", "暂无数据");
                }

                String resultStr = gson.toJson(resultMap);
                writer.print(resultStr);

            }else if("addHousehold.do".equals(action)){

                int holdid = Integer.parseInt(request.getParameter("houseid"));
                int houseid = Integer.parseInt(request.getParameter("buildingid"));
                String holdtel = request.getParameter("unitid");
                int holdnum = Integer.parseInt(request.getParameter("numberid"));
                String holdpwd = request.getParameter("housesta");

                Household household = new Household(holdid,houseid,holdtel,holdnum,holdpwd);
                int result = HouseholdService.addHousehold(household);
                if (result != -1) {
                    resultMap.put("code", 998);
                    resultMap.put("msg", "添加成功");
                } else {
                    resultMap.put("code", 997);
                    resultMap.put("msg", "添加失败");
                }
            }else if("deleteHousehold.do".equals(action)){
                int holdid = Integer.parseInt(request.getParameter("holdid"));
                int result = HouseholdService.deleteHouseholdById(Integer.parseInt(request.getParameter("holdid")));
                if (result != -1) {
                    resultMap.put("code", 996);
                    resultMap.put("msg", "删除成功");
                } else {
                    resultMap.put("code", 995);
                    resultMap.put("msg", "删除失败");
                }
//            }else if("updateHousehold.do".equals(action)){
//
//                int holdid = Integer.parseInt(request.getParameter("houseid"));
//                int houseid = Integer.parseInt(request.getParameter("buildingid"));
//                String holdtel = request.getParameter("unitid");
//                int holdnum = Integer.parseInt(request.getParameter("numberid"));
//                String holdpwd = request.getParameter("housesta");
//
//                Household house = new Household();
//                int result = HouseholdService.updateHousehold(household);
//                if (result != -1) {
//                    resultMap.put("code", 994);
//                    resultMap.put("msg", "更新成功");
//                } else {
//                    resultMap.put("code", 993);
//                    resultMap.put("msg", "更新失败");
                }
            }
        }
}

