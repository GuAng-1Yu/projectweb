package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.NoticeDAO;
import com.iscc.propertymanagent.domain.Notice;
import com.iscc.propertymanagent.util.DataSourceUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZHOUB
 * @create 2020-08-10-23:35
 */
public class NoticeDaoImpl implements NoticeDAO {
    @Override
    public int addnotice(Notice notice) {
        String sql = " INSERT INTO notice(noticecon,noticetime,holdid)VALUES(?,?,?) ";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DataSourceUtil.getConnection();
            //获取Connection对象
            //获取预处理对象 PreparedStatement
            psmt = conn.prepareStatement(sql);
            //如果SQL中有占位符，给占位符赋值，没有过。
            psmt.setString(1, notice.getNoticecon());
            psmt.setString(2, notice.getNoticetime());
            psmt.setInt(3, notice.getHoldid());
//            psmt.setString(3, user.getPhone());
//            psmt.setString(4, user.getAccount());
            //执行SQL: 查询- executeQuery, 增删改- executeUpdate() : int 影响行数;
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;

    }

    @Override
    public int add(Object o) {
        return 0;
    }

    @Override
    public int update(Object o) {
        return 0;
    }

    @Override
    public int del(int id) {
        Connection conn = null;
        PreparedStatement prst = null;
        int rs = -1;
        String sql = " DELETE FROM notice WHERE noticeid = ? ";

        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setInt(1, id);
            rs = prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(prst, conn);

        }


        return rs;
    }

    @Override
    public Notice serch(int noticeid) {
        Notice notice = null;

        String sql = " SELECT noticeid,noticecon,DATE_FORMAT(noticetime,'%Y-%m-%d %H:%i:%s') noticetime, holdid FROM notice where noticeid = ?      GROUP BY noticetime DESC   ";
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setInt(1, noticeid);
            rs = prst.executeQuery();
            while (rs.next()) {
                notice = new Notice();
                notice.setNoticeid(rs.getInt(1));
                notice.setNoticecon(rs.getString(2));
                notice.setNoticetime(rs.getString(3));
                notice.setHoldid(rs.getInt(4));

            }
//            System.out.println("noticesql" + notice);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, prst, conn);
        }
        return notice;
    }

    @Override
    public int edit(Notice notice) {
        Connection conn = null;
        PreparedStatement prst = null;
        int rs = -1;
        String spl = " UPDATE notice SET noticecon= ? ,holdid= ? WHERE noticeid=? ";

        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(spl);


            prst.setString(1, notice.getNoticecon());
            prst.setInt(2, notice.getHoldid());
            prst.setInt(3, notice.getNoticeid());
            rs = prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(prst, conn);
        }


        return rs;
    }

    @Override
    public List serchbyholdid(int id) {
//        Notice notice = null;
        List<Notice> notices = new ArrayList<>();

        String sql = "  SELECT noticeid,noticecon,DATE_FORMAT(noticetime,'%Y-%m-%d %H:%i:%s'),holdid  FROM notice where holdid = ?   GROUP BY noticetime DESC       ";
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setInt(1, id);
            rs = prst.executeQuery();
            while (rs.next()) {
                Notice notice = new Notice();
                notice.setNoticeid(rs.getInt(1));
                notice.setNoticecon(rs.getString(2));
                notice.setNoticetime(rs.getString(3));
                notice.setHoldid(rs.getInt(4));
                notices.add(notice);

            }
//            System.out.println("noticesql"+notice);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, prst, conn);
        }
        return notices;

    }

    @Override
    public List queryAll() {

        List<Notice> notices = new ArrayList<>();
        String sql = " SELECT noticeid,noticecon,DATE_FORMAT(noticetime,'%Y-%m-%d %H:%i:%s'),holdid FROM notice GROUP BY noticetime DESC ";
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            conn = DataSourceUtil.getConnection();
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            while (rs.next()) {
                Notice notice = new Notice();
                notice.setNoticeid(rs.getInt(1));
                notice.setNoticecon(rs.getString(2));
                notice.setNoticetime(rs.getString(3));
                notice.setHoldid(rs.getInt(4));
                notices.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, prst, conn);
        }

//        conn.get
        return notices;
    }

    @Override
    public Object queryById(int id) {


        return null;
    }
}
