package com.iscc.propertymanagent.dao;

import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Pager;

import java.util.List;
import java.util.Map;

public interface HouseDAO {

    public List<House> searchHouseById(int houseid);
    public List<Map> searchAllHouseMap();
    public List<House> searchHouseAll();
    public int deleteHouseById(int houseid);
    int addHouse(House house);
    public int updateHouse(House house);
    List<Map> queryHouseWithPage(Pager pager);

}
