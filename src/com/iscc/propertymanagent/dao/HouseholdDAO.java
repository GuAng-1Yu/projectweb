package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Household;

import java.lang.reflect.Type;
import java.util.List;

public interface HouseholdDAO extends BaseDAO<Type> {
    List<Household> queryAllTypeByCondition(String name);
}
