package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.NoticeDAO;
import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.dao.impl.NoticeDaoImpl;
import com.iscc.propertymanagent.dao.impl.UserDAOImpl;
import com.iscc.propertymanagent.domain.Notice;
import com.iscc.propertymanagent.service.NoticeService;

import java.util.List;

/**
 * @author ZHOUB
 * @create 2020-08-10-23:30
 */
public class NoticeServiceImpl implements NoticeService {



    private NoticeDAO noticeDAO=new NoticeDaoImpl() ;
    @Override
    public int add(Notice notice) {

        return noticeDAO.addnotice(notice);
    }

    @Override
    public List<Notice> queryAll() {
        return noticeDAO.queryAll();
    }

    @Override
    public int del(int id) {
        return noticeDAO.del(id);
    }

    @Override
    public Notice serchbyid(int noticeid) {
        return noticeDAO.serch(noticeid);
    }
}
