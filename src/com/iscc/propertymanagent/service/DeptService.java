package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;

import java.util.List;
import java.util.Map;

public interface DeptService {
    List<Dept> queryDept();
    List<Map> queryDeptWithPage(Pager pager);
    int addStaff(Dept dept);
    int updateDept(Dept dept);
    int delDept(int id);
}
