package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.HouseholdDAO;
import com.iscc.propertymanagent.domain.House;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class HouseholdDAOImpl implements HouseholdDAO {

    @Override
    public int addHousehold(Household household) {

        String sql = "insert into household_info(holdid,houseid,hoteltel,holdnum,holdpwd) value(?,?,?,?,?)";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {

            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, household.getHoldid());
            psmt.setInt(2,household.getHouseid());
            psmt.setString(3, household.getHoldtel());
            psmt.setInt(4,household.getHoldnum());
            psmt.setString(5,household.getHoldpwd());
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;

    }

    @Override
    public List<Household> searchHouseholdById(int holdid) {
        String sql = "select * from household_info where holdid = ?";
        Household household = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Household> householdById = null;
        try{
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, holdid);
            rs = psmt.executeQuery();
            if(rs.next()){
                household = new Household();
                household.setHoldid(rs.getInt(1));
                household.setHouseid(rs.getInt(2));
                household.setHoldtel(rs.getString(3));
                household.setHoldnum(rs.getInt(4));
                household.setHoldpwd(rs.getString(5));
                householdById.add(household);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DataSourceUtil.releaseResource(rs,psmt,conn);
        }
        return householdById;
    }

    @Override
    public List<Household> searchHouseholdAll() {
        String sql = "select * from household_info";
        Household household = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Household> householdAll = null;
        try{
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                household = new Household();
                household.setHoldid(rs.getInt(1));
                household.setHouseid(rs.getInt(2));
                household.setHoldtel(rs.getString(3));
                household.setHoldnum(rs.getInt(4));
                household.setHoldpwd(rs.getString(5));
                householdAll.add(household);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DataSourceUtil.releaseResource(rs,psmt,conn);
        }
        return householdAll;
    }

    @Override
    public void deleteHouseholdById(int holdid) {

        String sql = "delete from household_info where holdid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, holdid);
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
    }
}
