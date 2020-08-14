package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Notice;
import com.iscc.propertymanagent.service.UserService;
import com.iscc.propertymanagent.service.impl.NoticeServiceImpl;
import com.iscc.propertymanagent.service.impl.UserServiceImpl;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZHOUB
 * @create 2020-08-10-22:00
 */
@WebServlet({"/Noticeadd.do", "/queryALL.do","/noticedel.do","/notice_serchbyid.do","/notice_edit.do","/notice_serchbyholdid.do"})
public class StaffNoticeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        NoticeServiceImpl noticeService = new NoticeServiceImpl();
        PrintWriter out = response.getWriter();
        // /MaoKe/register.do
        String uri = request.getRequestURI();
        Gson gosn = new Gson();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        Map<String, Object> resultMap = new HashMap<>();
        if ("Noticeadd.do".equals(action)) {
            int holdid = 0;
            if (request.getParameter("holdid") == "") {
            } else {
                holdid = Integer.parseInt(request.getParameter("holdid"));
            }
            String noticecon = request.getParameter("noticecon");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String noticetime = formatter.format(date);
            Notice notice = new Notice(noticecon, noticetime, holdid);
            int result = noticeService.add(notice);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "发布成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "发布失败");
            }
//            System.out.println("resultMap = " + resultMap);
            String str = gosn.toJson(resultMap);
//            System.out.println("str = " + str);
            out.print(str);

        } else if ("queryALL.do".equals(action)) {
            List<Notice> notices = noticeService.queryAll();
//            System.out.println(notices);
            if (notices.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询数据成功");
                resultMap.put("result", notices);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "数据加载失败");
            }
            String str = gosn.toJson(resultMap);
            out.print(str);

        } else if ("noticedel.do".equals(action)) {
//            int holdid = 0;

            int noticeid = Integer.parseInt(request.getParameter("noticeid"));
//            System.out.println("===========");
//            System.out.println(noticeid);
            int result = noticeService.del(noticeid);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "删除成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "删除失败");
            }
//            System.out.println("resultMap = " + resultMap);
            String str = gosn.toJson(resultMap);
//            System.out.println("str = " + str);
            out.print(str);
        }
        else if ("notice_serchbyid.do".equals(action)) {
            int noticeid = Integer.parseInt(request.getParameter("noticeid"));
            Notice notice = noticeService.serchbyid(noticeid);
              if(notice!=null){
                  resultMap.put("code", 200);
                  resultMap.put("result", notice);
              }else {
                  resultMap.put("code", 201);
                  resultMap.put("msg", "不存在该数据");
              }
            String str = gosn.toJson(resultMap);
            out.print(str);
        } else if ("notice_edit.do".equals(action)){
            int noticeid = Integer.parseInt(request.getParameter("noticeid"));
            String noticecon= request.getParameter("noticecon");
            int holdid = 0;
            if (request.getParameter("holdid") == "") {
            } else {
                holdid = Integer.parseInt(request.getParameter("holdid"));
            }
            Notice notice = new Notice(noticeid,noticecon,holdid);
//            System.out.println(notice);
            int result=noticeService.edit(notice);
//            System.out.println(result);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "修改成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "修改失败");
            }
            String str= gosn.toJson(resultMap);
            out.print(str);
        }else  if("notice_serchbyholdid.do".equals(action)){

            int holdid = Integer.parseInt(request.getParameter("holdid"));

            List<Notice> notices = noticeService.serchbyholdid(holdid);

//            System.out.println(notices);
            if (notices.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询数据成功");
                resultMap.put("result", notices);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "数据加载失败");
            }
            String str = gosn.toJson(resultMap);
            out.print(str);
        }

    }}