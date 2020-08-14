package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.Costtype;

import java.util.List;
import java.util.Map;

public interface CosttypeService {
    List<Costtype> queryAllTypeByCondition(String name);

    int addCosttype(Costtype costtype);

    int updateCosttype(Costtype costtype);

    int delCosttype(int costid);

    Costtype queryTypeById(int id);
    List<Costtype> queryAllTypeByCondition(Map<String, Object> params);
}
