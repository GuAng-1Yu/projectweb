package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.House;

import java.util.List;

public interface HouseDAO {

    public void searchHouseById(int houseid);
    public List<House> searchHouseAll();
    public void deleteHouseById(int houseid);
    int addHouse(House house);
    public void updateHouse(House house);

}
