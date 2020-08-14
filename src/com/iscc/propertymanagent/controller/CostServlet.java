package com.iscc.propertymanagent.controller;
import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Cost;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.service.CostService;
import com.iscc.propertymanagent.service.impl.CostServiceImpl;
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

@WebServlet({"/query_all_cost.do","/add_cost.do","/del_cost.do","/update_cost.do","/query_cost_id.do","/query_costpage.do"})
public class CostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        CostService costService = new CostServiceImpl();
        Map<String, Object> resultMap = new HashMap<>();
        if ("query_all_cost.do".equals(action)) {
            String name = request.getParameter("houseid");
            List<Cost> list = costService.queryAllTypeByCondition(name);
            if (list.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询成功");
                resultMap.put("result", list);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "查询成功,暂无数据");
            }
        }else if ("add_cost.do".equals(action)) {
            int id = Integer.parseInt(request.getParameter("houseid"));
            double price = Double.parseDouble(request.getParameter("costprice"));
             int  type = Integer.parseInt(request.getParameter("typeid"));
            Cost cost = new Cost(id,price,type);
            int result = costService.addCost(cost);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "添加成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "添加失败");
            }
        }else if ("del_cost.do".equals(action)) {
            int costid = Integer.parseInt(request.getParameter("costid"));
            int result = costService.delCost(costid);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "删除类别成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "删除类别失败");
            }
        }else if ("query_cost_id.do".equals(action)) {

            int houseid = Integer.parseInt(request.getParameter("houseid"));
            Cost cost = costService.queryTypeById(houseid);

            if (cost != null) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询成功");
                resultMap.put("result", cost);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "没有该信息");
            }
        }else if ("update_cost.do".equals(action)) {

            int costid = Integer.parseInt(request.getParameter("costid"));
            int houseid = Integer.parseInt(request.getParameter("houseid"));
            double costprice = Double.parseDouble(request.getParameter("costprice"));
            int typeid=Integer.parseInt(request.getParameter("typeid"));
            Cost cost = new Cost(costid, houseid,costprice,typeid);
            int result = costService.updateCost(cost);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "更新类别成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "更新类别失败");
            }
        }else if ("query_costpage.do".equals(action)) {
           String name = request.getParameter("houseid");
            List<Cost>  types = costService.queryAllTypeByCondition(name);
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
            Pager<Cost> page = new Pager<>(currPage, pageNum, types);

            Map<String, Object> map = new HashMap<>();
            map.put("houseid", name);
            map.put("page", page);
            // map.put("offset",pageNum);

            List<Cost> typeList = costService.queryAllTypeByCondition(map);
            resultMap.put("code", 200);
            resultMap.put("msg", "查询类别成功");
            resultMap.put("page", page);
            resultMap.put("result", typeList);
        }

        String resultStr = gson.toJson(resultMap);
        out.print(resultStr);
    }
}
