package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.impl.HouseDAOImpl;
import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.service.HouseService;

import java.util.List;

public class HouseServiceImpl implements HouseService {

    HouseDAOImpl houseDAO = new HouseDAOImpl();

    @Override
    public int deleteHouseById(int houseid) {
        return houseDAO.deleteHouseById(houseid);
    }

    @Override
    public int addHouse(House house) {
        return houseDAO.addHouse(house);
    }

    @Override
    public int updateHouse(House house) {
        return houseDAO.updateHouse(house);
    }

    @Override
    public List<House> queryAllHouse() {
        return houseDAO.searchHouseAll();
    }

    @Override
    public List<House> queryHouseById(int houseid) { return houseDAO.searchHouseById(houseid); }
}