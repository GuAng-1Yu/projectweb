package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Notice;

/**
 * @author ZHOUB
 * @create 2020-08-10-23:33
 */
public interface NoticeDAO extends BaseDAO  {
    public int addnotice(Notice notice);
}
