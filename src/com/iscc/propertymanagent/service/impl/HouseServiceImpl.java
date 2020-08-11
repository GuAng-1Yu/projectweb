package com.iscc.propertymanagent.service.impl;

import com.iscc.propertymanagent.dao.impl.HouseDAOImpl;
import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.service.HouseService;

import java.util.List;

public class HouseServiceImpl implements HouseService {

    HouseDAOImpl houseDAO = new HouseDAOImpl();

    @Override
    public void deleteHouseById(int houseid) {
        houseDAO.deleteHouseById(houseid);
    }

    @Override
    public void addHouse(House house) {
        houseDAO.addHouse(house);
    }

    @Override
    public void updateHouse(House house) {
        houseDAO.updateHouse(house);
    }

    @Override
    public List<House> queryAllHouse() {
        return houseDAO.searchHouseAll();
    }

    @Override
    public void queryHouseById(int houseid) {
        houseDAO.searchHouseById(houseid);
    }
}
