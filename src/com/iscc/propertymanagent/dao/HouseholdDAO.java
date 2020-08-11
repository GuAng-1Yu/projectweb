package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Household;

import java.util.List;

public interface HouseholdDAO {

    public List<Household> searchHouseholdById(int holdid);
    public List<Household> searchHouseholdAll();
    public int deleteHouseholdById(int holdid);
    int addHousehold(Household household);

}
