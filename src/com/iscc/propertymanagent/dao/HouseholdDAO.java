package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;

import java.util.List;
import java.util.Map;

public interface HouseholdDAO {

    public List<Household> searchHouseholdById(int holdid);
    public List<Household> searchHouseholdAll();
    int deleteHouseholdById(int id);
    int addHousehold(Household household);
    int updateHousehold(Household household);

    List<Household> searchHouseholdAll(Pager pager);

}
