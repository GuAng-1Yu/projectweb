package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.HouseholdDAO;
import com.iscc.propertymanagent.dao.impl.HouseholdDAOImpl;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.service.HouseholdService;

import java.util.List;
import java.util.Map;

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
    public int deleteHouseholdById(int id) {
        return householdDAOImpl.deleteHouseholdById(id);
    }

    @Override
    public int addHousehold(Household household) {
        return householdDAOImpl.addHousehold(household);
    }

    @Override
    public int updateHousehold(Household household) { return householdDAOImpl.updateHousehold(household); }

    @Override
    public List<Household> searchHouseholdAll(Pager pager) {
        return householdDAOImpl.searchHouseholdAll(pager);
    }
}
