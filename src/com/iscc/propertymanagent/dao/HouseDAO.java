package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.House;

import java.util.List;

public interface HouseDAO {

    public List<House> searchHouseById(int houseid);
    public List<House> searchHouseAll();
    public int deleteHouseById(int houseid);
    int addHouse(House house);
    public int updateHouse(House house);

}
