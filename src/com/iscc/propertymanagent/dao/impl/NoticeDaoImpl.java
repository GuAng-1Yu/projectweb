package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.NoticeDAO;
import com.iscc.propertymanagent.domain.Notice;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return 0;
    }

    @Override
    public List queryAll() {
        return null;
    }

    @Override
    public Object queryById(int id) {
        return null;
    }
}
