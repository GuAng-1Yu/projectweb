package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Notice;

import java.util.List;

/**
 * @author ZHOUB
 * @create 2020-08-10-23:33
 */
public interface NoticeDAO extends BaseDAO  {
    public int addnotice(Notice notice);
    int del(int id);
    Notice serch(int id);
    int edit(Notice notice);
    List serchbyholdid(int id);

}
