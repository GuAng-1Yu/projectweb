package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.Household;


import java.util.List;

public interface HouseholdService {
    List<Household> queryAllTypeByCondition(String name);



}
