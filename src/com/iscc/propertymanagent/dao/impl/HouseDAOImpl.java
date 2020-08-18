package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.HouseDAO;
import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseDAOImpl implements HouseDAO {

    @Override
    public int addHouse(House house) {

        String sql = "insert into house_info(houseid,buildingid,unitid,numberid,housesta) value(?,?,?,?,?)";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {

            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, Integer.parseInt(house.getBuildingid()+""+house.getUnitid()+""+house.getNumberid()));
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
    public List<House> searchHouseById(int houseid) {

        String sql = "select * from house_info where houseid = ?";
        House houseInfo = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<House> houseById = new ArrayList<>();

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
                houseById.add(houseInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DataSourceUtil.releaseResource(rs,psmt,conn);
        }

        return houseById;
    }

    @Override
    public List<Map> searchAllHouseMap() {
        String sql = "SELECT * FROM house_info";
        List<Map> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("houseid",rs.getInt(1));
                resultMap.put("buildingid",rs.getInt(2));
                resultMap.put("unitid",rs.getInt(3));
                resultMap.put("numberid",rs.getString(4));
                resultMap.put("housesta",rs.getInt(5));
                results.add(resultMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return results;
    }

    @Override
    public List<House> searchHouseAll() {
        String sql = "select * from house_info";
        House houseInfo = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<House> allHouse = new ArrayList<>();
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
    public int deleteHouseById(int houseid) {

        String sql = "delete from house_info where houseid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        int result = -1;

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, houseid);
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }

    @Override
    public int updateHouse(House house) {
        String sql = "update house_info set housesta = ? where houseid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        int result = -1;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, house.getHousesta());
            psmt.setInt(2, house.getHouseid());
            result = psmt.executeUpdate();
            System.out.println(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }

    @Override
    public List<Map> queryHouseWithPage(Pager pager) {

        String sql = "SELECT * FROM house_info LIMIT ?,?";
        List<Map> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,pager.getStart());
            psmt.setInt(2,pager.getPageNum());
            rs = psmt.executeQuery();
            while (rs.next()) {
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("houseid",rs.getInt(1));
                resultMap.put("buildingid",rs.getInt(2));
                resultMap.put("unitid",rs.getInt(3));
                resultMap.put("numberid",rs.getString(4));
                resultMap.put("housesta",rs.getInt(5));
                results.add(resultMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return results;
    }
}
