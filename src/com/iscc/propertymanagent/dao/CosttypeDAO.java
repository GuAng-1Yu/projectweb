package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Costtype;

import java.util.List;
import java.util.Map;

public interface CosttypeDAO extends BaseDAO<Costtype> {
    List<Costtype> queryAllTypeByCondition(String name);
    List<Costtype> queryAllTypeByCondition(Map<String, Object> params);
}
