package com.iscc.propertymanagent.service;

import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Pager;

import java.util.List;
import java.util.Map;

public interface HouseService {

    public int deleteHouseById(int houseid);
    public int addHouse(House house);
    public int updateHouse(House house);
    public List<House> queryAllHouse();
    public List<Map> queryAllHouseMap();
    public List<House> queryHouseById(int houseid);
    List<Map> queryHouseWithPage(Pager pager);

}
