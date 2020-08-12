package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.impl.HouseholdDAOImpl;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.service.HouseholdService;

import java.util.List;

public class HouseholdServiceImpl implements HouseholdService {

    HouseholdDAOImpl householdDAOImpl = new HouseholdDAOImpl();
    @Override
    public List<Household> searchHouseholdById(int holdid) {
        return householdDAOImpl.searchHouseholdById(holdid);
    }

    @Override
    public List<Household> searchHouseholdAll() {
        return householdDAOImpl.searchHouseholdAll();
    }

    @Override
    public int deleteHouseholdById(int holdid) {
        return householdDAOImpl.deleteHouseholdById(holdid);
    }

    @Override
    public int addHousehold(Household household) {
        return householdDAOImpl.addHousehold(household);
    }
}
