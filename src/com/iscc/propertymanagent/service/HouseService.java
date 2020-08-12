package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.House;

import java.util.List;

public interface HouseService {

    public int deleteHouseById(int houseid);
    public int addHouse(House house);
    public int updateHouse(House house);
    public List<House> queryAllHouse();
    public List<House> queryHouseById(int houseid);

}
