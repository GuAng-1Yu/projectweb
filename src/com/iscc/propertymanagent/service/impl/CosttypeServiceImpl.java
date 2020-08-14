package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.CosttypeDAO;
import com.iscc.propertymanagent.dao.impl.CosttypeDAOImpl;
import com.iscc.propertymanagent.domain.Costtype;
import com.iscc.propertymanagent.service.CosttypeService;

import java.util.List;
import java.util.Map;

public class CosttypeServiceImpl implements CosttypeService {
    private CosttypeDAO costtypeDAO =new CosttypeDAOImpl();
    @Override
    public List<Costtype> queryAllTypeByCondition(String name) {
        return costtypeDAO.queryAllTypeByCondition(name);
    }

    @Override
    public int addCosttype(Costtype costtype) {
        return costtypeDAO.add(costtype);
    }

    @Override
    public int updateCosttype(Costtype costtype) {
        return costtypeDAO.update(costtype);
    }

    @Override
    public int delCosttype(int typeid) {
        return costtypeDAO.del(typeid);
    }

    @Override
    public Costtype queryTypeById(int id) {
        return costtypeDAO.queryById(id);
    }

    @Override
    public List<Costtype> queryAllTypeByCondition(Map<String, Object> params) {
        return costtypeDAO.queryAllTypeByCondition(params);
    }
}
