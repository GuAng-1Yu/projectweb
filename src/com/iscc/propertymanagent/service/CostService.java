package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.Cost;



import java.util.List;
import java.util.Map;

public interface CostService {
    List<Cost> queryAllTypeByCondition(String name);
    int addCost(Cost cost);

    int updateCost(Cost cost);

    int delCost(int costid);

    Cost queryTypeById(int id);
    List<Cost> queryAllTypeByCondition(Map<String, Object> params);


}
