package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;

import java.util.List;
import java.util.Map;

public interface HouseholdService {

    public List<Household> searchHouseholdById(int holdid);
    public List<Household> searchHouseholdAll();
    public int deleteHouseholdById(int id);
    int addHousehold(Household household);
    int updateHousehold(Household household);

    List<Household> searchHouseholdAll(Pager pager);

}
