package com.iscc.propertymanagent.controller;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.domain.Staff;
import com.iscc.propertymanagent.domain.User;
import com.iscc.propertymanagent.service.UserService;
import com.iscc.propertymanagent.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/register.do","/login.do","/stafflogin.do","/detailquery.do","/query_page1.do"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        PrintWriter out = response.getWriter();
        // /MaoKe/register.do
        String uri = request.getRequestURI();
//        System.out.println("1");
        Gson gosn = new Gson();
//        System.out.println("2");
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        Map<String, Object> resultMap = new HashMap<>();
        if ("register.do".equals(action)) {
            //执行注册操作
            String account = request.getParameter("account");
            String password = request.getParameter("password");
//            String phone = request.getParameter("phone");
//            System.out.println(account + "   " + password);
            User user = new User(account, password);
            int result = userService.addUser(user);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "注册成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "注册失败");
            }
//            System.out.println("resultMap = " + resultMap);
            String str = gosn.toJson(resultMap);
//            System.out.println("str = " + str);
            out.print(str);
        }
  else  if ("login.do".equals(action)){
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            Household houser =null;
            int holdid = Integer.parseInt(account);
            houser=userService.holdlogin(holdid);
            if (houser!=null){
                if (houser.getHoldpwd().equals(password)){
                    resultMap.put("code",200);
                    resultMap.put("msg","登录成功");
                    resultMap.put("result",houser);
                    session.setAttribute("holder",houser);
                }else {
                    resultMap.put("code",202);
                    resultMap.put("msg","密码错误！");
                }
            }else {
                resultMap.put("code",203);
                resultMap.put("msg","账号或密码错误");
            }

            String str = gosn.toJson(resultMap);
            out.print(str);
        }
        else  if ("stafflogin.do".equals(action)){
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            Staff  staff=null;
            int staffid = Integer.parseInt(account);
            staff = userService.stafflogin(staffid);
//            System.out.println(staff);
            if (staff!=null){
                if (staff.getStafftel().equals(password)){
                    resultMap.put("code",200);
                    resultMap.put("msg","登录成功");
                    resultMap.put("result",staff);
                    session.setAttribute("staff",staff);
                    //取ssesion???..
//                    System.out.println(((Staff)session.getAttribute("staff")).getDeptid());
                }else {
                    resultMap.put("code",202);
                    resultMap.put("msg","密码错误！");
                }
            }else {
                resultMap.put("code",203);
                resultMap.put("msg","账号或密码错误");

            }

            String str = gosn.toJson(resultMap);
            out.print(str);
        }
        else if ("detailquery.do".equals(action)){

            int holdid= Integer.parseInt(request.getParameter("holdid"));

            Map<String, Object> detailmap = userService.detailQuery(holdid);

            if (detailmap!=null){
                resultMap.put("code",200);
                resultMap.put("data",detailmap);

            }else {
                resultMap.put("code",202);

            }
            String str = gosn.toJson(resultMap);
            out.print(str);
        }else  if ("query_page1.do".equals(action)){
            System.out.println(1233);
           int houseid = Integer.parseInt( request.getParameter("houseid"));
            List<Map<String, Object>> costlist = userService.queryAllTypeByCondition(houseid);
//            System.out.println(costlist);
            int currPage = 1;
            int pageNum = 2;
            try {
                currPage = Integer.parseInt(request.getParameter("currPage"));
            } catch (Exception e) {
            }
//            try {
//                currPage = Integer.parseInt(request.getParameter("pageNum"));
//            } catch (Exception e) {
//
//            }
            Pager<Map<String, Object>> mapPager = new Pager<>(currPage, pageNum, costlist);
            System.out.println(mapPager);
            Map<String, Object> map = new HashMap<>();
            map.put("houseid", houseid);
            map.put("page", mapPager);
            // map.put("offset",pageNum);
//            System.out.println(map);
            List<Map<String, Object>> maps = userService.queryAllTypeByCondition(map);

            resultMap.put("code", 200);
            resultMap.put("msg", "查询类别成功");
            resultMap.put("page", mapPager);
            resultMap.put("result", maps);
            String str = gosn.toJson(resultMap);
            out.print(str);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
