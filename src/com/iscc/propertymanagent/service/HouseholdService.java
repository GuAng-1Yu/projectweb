package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Household;

import java.util.List;

public interface HouseholdService {

    public List<Household> searchHouseholdById(int holdid);
    public List<Household> searchHouseholdAll();
    public void deleteHouseholdById(int holdid);
    int addHousehold(Household household);

}
