package com.iscc.propertymanagent.service.impl;



import com.iscc.propertymanagent.dao.HouseholdDAO;
import com.iscc.propertymanagent.dao.impl.HouseholdDAOImpl;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.service.HouseholdService;

import java.util.List;


public class HouseholdServiceImpl implements HouseholdService {

    private HouseholdDAO householdDAO = new HouseholdDAOImpl();

    @Override
    public List<Household> queryAllTypeByCondition(String name) {
        return householdDAO.queryAllTypeByCondition(name);
    }
}
