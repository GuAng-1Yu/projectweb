package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Household;

import java.util.List;

public interface HouseholdDAO {

    public void searchHouseholdById(int holdid);
    public List<Household> searchHouseholdAll();
    public void deleteHouseholdById(int holdid);
    int addHousehold(Household household);

}
