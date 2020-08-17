package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;

import java.util.List;
import java.util.Map;

public interface DeptDao {
    List<Dept> queryDept();
    List<Dept> queryDeptWithPage(Pager pager);
    int addStaff(Dept dept);
    int updateDept(Dept dept);
    int delDept(int id);
}
