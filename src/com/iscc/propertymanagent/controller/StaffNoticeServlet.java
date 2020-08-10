package com.iscc.propertymanagent.controller;

import com.google.gson.Gson;
import com.iscc.propertymanagent.domain.Notice;
import com.iscc.propertymanagent.service.UserService;
import com.iscc.propertymanagent.service.impl.NoticeServiceImpl;
import com.iscc.propertymanagent.service.impl.UserServiceImpl;

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
import java.util.Map;

/**
 * @author ZHOUB
 * @create 2020-08-10-22:00
 */
@WebServlet({"/Noticeadd.do"})
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
        if("Noticeadd.do".equals(action)){
            int holdid=0;
            if (request.getParameter("holdid")=="") {
            }else {
                holdid= Integer.parseInt(request.getParameter("holdid"));}
            String noticecon=request.getParameter("noticecon");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String noticetime=formatter.format(date);
            Notice notice = new Notice(noticecon,noticetime,holdid);
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

        }
    }
}
