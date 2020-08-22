package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Cost;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface CostDAO extends BaseDAO <Cost>{
    int add(Connection conn, Cost cost);

    int getNewId(Connection conn);

    List<Cost> queryAllTypeByCondition(String name);
    List<Cost> queryAllTypeByCondition(Map<String, Object> name);
    List<Cost> queryAllTypeByCondition(int name);
}
