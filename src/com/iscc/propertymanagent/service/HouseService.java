package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.House;

import java.util.List;

public interface HouseService {

    public void deleteHouseById(int houseid);
    public void addHouse(House house);
    public void updateHouse(House house);
    List<House> queryAllHouse();
    public void queryHouseById(int houseid);

}
