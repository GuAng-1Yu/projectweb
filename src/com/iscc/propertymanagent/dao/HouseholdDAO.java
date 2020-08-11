package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.Household;

public interface HouseholdDAO {

    public void searchHouseholdById(int holdId);
    public void searchHouseholdAll();
    public void deleteHouseholdById(int holdId);
    int addHousehold(Household household_Info);

}
