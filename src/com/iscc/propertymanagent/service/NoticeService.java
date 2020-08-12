package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.Notice;

import java.util.List;

/**
 * @author ZHOUB
 * @create 2020-08-10-23:28
 */
public interface NoticeService {
    int add(Notice notice);
    List<Notice>   queryAll();
    int del(int id);
    Notice serchbyid(int noticeid);

}
