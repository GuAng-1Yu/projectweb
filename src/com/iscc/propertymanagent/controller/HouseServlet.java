package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Costtype;
import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.service.HouseService;
import com.iscc.propertymanagent.service.impl.HouseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/queryHouseAll.do","/queryHouseById.do","/addHouse.do","/deleteHouse.do","/updateHouse.do","/getAllHouseWithPage.do","/queryHouseBySta.do"})
public class HouseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        PrintWriter writer = response.getWriter();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        HouseService houseService = new HouseServiceImpl();
        HashMap<Object, Object> resultMap = new HashMap<>();
        if ("queryHouseAll.do".equals(action)) {

            List<House> resultSet = houseService.queryAllHouse();

              if (resultSet.size() > 0) {
                  resultMap.put("code", 999);
                  resultMap.put("msg", "查询成功");
                  resultMap.put("houseAllInfo", resultSet);
                  System.out.println(resultSet);
              } else {
                  resultMap.put("code", 998);
                  resultMap.put("msg", "暂无数据");
              }
            String resultStr = gson.toJson(resultMap);
            writer.print(resultStr);

        }else if("queryHouseById.do".equals(action)){
            System.out.println(Integer.parseInt(request.getParameter("houseid")));
            List<House> resultSet = houseService.queryHouseById(Integer.parseInt(request.getParameter("houseid")));

              if (resultSet.size() > 0) {
                  resultMap.put("code", 999);
                  resultMap.put("msg", "查询成功");
                  resultMap.put("houseInfoById", resultSet);
              } else {
                  resultMap.put("code", 998);
                  resultMap.put("msg", "暂无数据");
              }

            String resultStr = gson.toJson(resultMap);
            writer.print(resultStr);

        }else if("queryHouseBySta.do".equals(action)){
            System.out.println(Integer.parseInt(request.getParameter("housesta")));
            List<House> resultSet = houseService.queryHouseBySta(Integer.parseInt(request.getParameter("housesta")));

            if (resultSet.size() > 0) {
                resultMap.put("code", 999);
                resultMap.put("msg", "查询成功");
                resultMap.put("houseSta", resultSet);
                System.out.println(resultSet);
            } else {
                resultMap.put("code", 998);
                resultMap.put("msg", "暂无数据");
            }
            String resultStr = gson.toJson(resultMap);
            writer.print(resultStr);

        }else if("addHouse.do".equals(action)){

                int buildingid = Integer.parseInt(request.getParameter("buildingid"));
                int unitid = Integer.parseInt(request.getParameter("unitid"));
                String numberid = request.getParameter("numberid");
                int housesta = Integer.parseInt(request.getParameter("housesta"));

                House house = new House(buildingid,unitid,numberid,housesta);
                int result = houseService.addHouse(house);
                if (result != -1) {
                    resultMap.put("code", 997);
                    resultMap.put("msg", "添加成功");
                } else {
                    resultMap.put("code", 996);
                    resultMap.put("msg", "添加失败");
                }
            String resultStr = gson.toJson(resultMap);
            writer.print(resultStr);

        }else if("deleteHouse.do".equals(action)){

            System.out.println(request.getParameter("houseid"));
            int houseid = Integer.parseInt(request.getParameter("houseid"));
            int result = houseService.deleteHouseById(houseid);
            if (result != -1) {
                resultMap.put("code", 995);
                resultMap.put("msg", "删除成功");
            } else {
                resultMap.put("code", 994);
                resultMap.put("msg", "删除失败");
            }
            String resultStr = gson.toJson(resultMap);
            writer.print(resultStr);
        }else if("updateHouse.do".equals(action)){

            int houseid = Integer.parseInt(request.getParameter("houseid"));
            int housesta = Integer.parseInt(request.getParameter("housesta"));

            System.out.println(houseid);
            System.out.println(housesta);

            House house = new House(houseid,housesta);
            int result = houseService.updateHouse(house);

            if (result != -1) {
                resultMap.put("code", 993);
                resultMap.put("msg", "更新成功");
            } else {
                resultMap.put("code", 992);
                resultMap.put("msg", "更新失败");
            }
            String resultStr = gson.toJson(resultMap);
            writer.print(resultStr);
        } else if ("getAllHouseWithPage.do".equals(action)) {
            int currPage = 1;
            int pageNum = 5;

            currPage = Integer.parseInt(request.getParameter("currPage"));

            List<Map> resultSet = houseService.queryAllHouseMap();
            Pager<Map> pager = new Pager<>(currPage, pageNum, resultSet);
            List<Map> maps = houseService.queryHouseWithPage(pager);
            if (resultSet.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "PageHouse查询成功");
                resultMap.put("result", maps);
                resultMap.put("pager", pager);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "数据走丢了，请稍后重试");
            }
            String resultStr = gson.toJson(resultMap);
            writer.print(resultStr);
        }


    }
}
