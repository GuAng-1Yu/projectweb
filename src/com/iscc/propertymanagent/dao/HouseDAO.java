package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.House;

public interface HouseDAO {
    public void searchHouseById(int houseid);
    public void searchHouseAll();
    public void deleteHouseById(int houseid);
    int addHouse(House house);
    public void updateHouse(House house);
}
