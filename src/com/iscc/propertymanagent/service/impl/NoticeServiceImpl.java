package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.NoticeDAO;
import com.iscc.propertymanagent.dao.UserDAO;
import com.iscc.propertymanagent.dao.impl.NoticeDaoImpl;
import com.iscc.propertymanagent.dao.impl.UserDAOImpl;
import com.iscc.propertymanagent.domain.Notice;
import com.iscc.propertymanagent.service.NoticeService;

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
}
