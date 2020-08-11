package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.HouseDAO;
import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class HouseDAOImpl implements HouseDAO {

    @Override
    public int addHouse(House house) {

        String sql = "insert into staff_info(houseid,buildingid,unitid,numberid,housesta) value(?,?,?,?,?)";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {

            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, house.getHouseid());
            psmt.setInt(2,house.getBuildingid());
            psmt.setInt(3, house.getUnitid());
            psmt.setString(4,house.getNumberid());
            psmt.setInt(5,house.getHousesta());
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;

    }

    @Override
    public void searchHouseById(int houseid) {
        String sql = "select * from house_info where houseid = ?";
        House houseInfo = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try{
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, houseid);
            rs = psmt.executeQuery();
            if(rs.next()){
                houseInfo = new House();
                houseInfo.setHouseid(rs.getInt(1));
                houseInfo.setBuildingid(rs.getInt(2));
                houseInfo.setUnitid(rs.getInt(3));
                houseInfo.setNumberid(rs.getString(4));
                houseInfo.setHousesta(rs.getInt(5));
                System.out.println(houseInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DataSourceUtil.releaseResource(rs,psmt,conn);
        }
    }

    @Override
    public List<House> searchHouseAll() {
        String sql = "select * from house_info";
        House houseInfo = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<House> allHouse = null;
        try{
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                houseInfo = new House();
                houseInfo.setHouseid(rs.getInt(1));
                houseInfo.setBuildingid(rs.getInt(2));
                houseInfo.setUnitid(rs.getInt(3));
                houseInfo.setNumberid(rs.getString(4));
                houseInfo.setHousesta(rs.getInt(5));
                allHouse.add(houseInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DataSourceUtil.releaseResource(rs,psmt,conn);
        }
        return allHouse;
    }

    @Override
    public void deleteHouseById(int houseid) {

        String sql = "delete from house_info where houseid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, houseid);
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
    }

    @Override
    public void updateHouse(House house) {
        String sql = "update house_info set housesta = ? where houseid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, house.getHousesta());
            psmt.setInt(2, house.getHouseid());
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
    }
}
