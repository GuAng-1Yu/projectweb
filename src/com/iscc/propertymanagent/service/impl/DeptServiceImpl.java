package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.DeptDao;
import com.iscc.propertymanagent.dao.impl.DeptDaoImpl;
import com.iscc.propertymanagent.domain.Dept;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.service.DeptService;

import java.util.List;
import java.util.Map;

public class DeptServiceImpl implements DeptService {
    DeptDao deptDao = new DeptDaoImpl();
    @Override
    public List<Dept> queryDept() {
        return deptDao.queryDept();
    }

    @Override
    public List<Dept> queryDeptWithPage(Pager pager) {
        return deptDao.queryDeptWithPage(pager);

    }

    @Override
    public int addStaff(Dept dept) {
        return deptDao.addStaff(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public int delDept(int id) {
        return deptDao.delDept(id);
    }
}
