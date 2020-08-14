package com.iscc.propertymanagent.service.impl;


import com.iscc.propertymanagent.dao.CostDAO;
import com.iscc.propertymanagent.dao.impl.CostDAOImpl;
import com.iscc.propertymanagent.domain.Cost;

import com.iscc.propertymanagent.service.CostService;

import java.util.List;
import java.util.Map;

public class CostServiceImpl implements CostService {
private CostDAO costDAO=new CostDAOImpl();
    @Override
    public List<Cost> queryAllTypeByCondition(String name) {
        return costDAO.queryAllTypeByCondition(name) ;
    }

    @Override
    public int addCost(Cost cost) {
        return costDAO.add(cost);
    }

    @Override
    public int updateCost(Cost cost) {
        return costDAO.update(cost);
    }

    @Override
    public int delCost(int costid) {
        return costDAO.del(costid);
    }

    @Override
    public Cost queryTypeById(int id) {
        return (Cost) costDAO.queryById(id);
    }

    @Override
    public List<Cost> queryAllTypeByCondition(Map<String, Object> params) {
        return costDAO.queryAllTypeByCondition(params);
    }


}
