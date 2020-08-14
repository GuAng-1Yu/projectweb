package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Costtype;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.service.CosttypeService;
import com.iscc.propertymanagent.service.impl.CosttypeServiceImpl;

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

@WebServlet({"/query_all_costtype.do","/add_costtype.do","/del_costtype.do","/update_costtype.do", "/query_costtype_id.do","/query_costtypepage.do"})
public class CosttypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        CosttypeService costtypeService=new CosttypeServiceImpl();
        Map<String, Object> resultMap = new HashMap<>();
        if ("query_all_costtype.do".equals(action)) {
            String name = request.getParameter("typename");
            List<Costtype> types = costtypeService.queryAllTypeByCondition(name);
            if (types.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询数据成功");
                resultMap.put("result", types);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "暂无类别数据");
            }
        }else if ("add_costtype.do".equals(action)) {
            String name = request.getParameter("typename");
            Costtype type = new Costtype(name);
            int result = costtypeService.addCosttype(type);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "添加类别成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "添加类别失败");
            }
        }else if ("del_costtype.do".equals(action)) {
            int typeid = Integer.parseInt(request.getParameter("typeid"));
            int result = costtypeService.delCosttype(typeid);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "删除类别成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "删除类别失败");
            }
        }else if ("query_costtype_id.do".equals(action)) {
            int typeid = Integer.parseInt(request.getParameter("typeid"));
            Costtype type = costtypeService.queryTypeById(typeid);
            if (type != null) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询类别成功");
                resultMap.put("result", type);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "没有该类别信息");
            }
        }else if ("update_costtype.do".equals(action)) {
            int typeid = Integer.parseInt(request.getParameter("typeid"));
            String typename = request.getParameter("typename");
            Costtype type = new Costtype(typeid, typename);
            int result = costtypeService.updateCosttype(type);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "更新类别成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "更新类别失败");
            }
        } else if ("query_costtypepage.do".equals(action)) {
            String name = request.getParameter("typename");
            List<Costtype> types = costtypeService.queryAllTypeByCondition(name);
            int currPage = 1;
            int pageNum = 4;
            try {
                currPage = Integer.parseInt(request.getParameter("currPage"));
            } catch (Exception e) {
            }
            try {
                currPage = Integer.parseInt(request.getParameter("pageNum"));
            } catch (Exception e) {

            }
            Pager<Costtype> page = new Pager<>(currPage, pageNum, types);

            Map<String, Object> map = new HashMap<>();
            map.put("typename", name);
            map.put("page", page);
            // map.put("offset",pageNum);

            List<Costtype> typeList = costtypeService.queryAllTypeByCondition(map);


            resultMap.put("code", 200);
            resultMap.put("msg", "查询类别成功");
            resultMap.put("page", page);
            resultMap.put("result", typeList);
        }

        String resultStr = gson.toJson(resultMap);
        out.print(resultStr);



    }
}
