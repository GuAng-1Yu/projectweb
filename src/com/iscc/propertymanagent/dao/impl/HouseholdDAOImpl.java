package com.iscc.propertymanagent.dao.impl;

import com.iscc.propertymanagent.dao.HouseholdDAO;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.domain.Pager;
import com.iscc.propertymanagent.util.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HouseholdDAOImpl implements HouseholdDAO {

    @Override
    public int addHousehold(Household household) {

        String sql = "INSERT INTO household_info VALUES (NULL,?,?,?,?);";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;

        try {

            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, household.getHouseid());
            psmt.setString(2, household.getHoldtel());
            psmt.setInt(3, household.getHoldnum());
            psmt.setString(4, household.getHoldpwd());
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;

    }

    @Override
    public int updateHousehold(Household household) {
        String sql = "UPDATE household_info SET houseid= ?, holdtel = ?, holdnum = ?, holdpwd = ? WHERE holdid = ?";
        int result = -1;
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, household.getHouseid());
            psmt.setString(2, household.getHoldtel());
            psmt.setInt(3, household.getHoldnum());
            psmt.setString(4, household.getHoldpwd());
            psmt.setInt(5, household.getHoldid());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Household> searchHouseholdAll(Pager pager) {
        ArrayList<Household> holds = new ArrayList<>();
        String sql = "SELECT * FROM household_info LIMIT ?,?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, pager.getStart());
            psmt.setInt(2, pager.getPageNum());
            rs = psmt.executeQuery();
            while (rs.next()) {
                Household household = new Household();
                household.setHoldid(rs.getInt(1));
                household.setHouseid(rs.getInt(2));
                household.setHoldtel(rs.getString(3));
                household.setHoldnum(rs.getInt(4));
                household.setHoldpwd(rs.getString(5));
                holds.add(household);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, psmt, conn);
        }
        return holds;
    }

    @Override
    public List<Household> searchHouseholdById(int holdid) {
        String sql = "select * from household_info where holdid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Household> householdById = null;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, holdid);
            rs = psmt.executeQuery();
            if (rs.next()) {
                Household household = new Household();
                household.setHoldid(rs.getInt(1));
                household.setHouseid(rs.getInt(2));
                household.setHoldtel(rs.getString(3));
                household.setHoldnum(rs.getInt(4));
                household.setHoldpwd(rs.getString(5));
                householdById.add(household);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, psmt, conn);
        }
        return householdById;
    }

    @Override
    public List<Household> searchHouseholdAll() {
        String sql = "select * from household_info";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Household> householdAll = new ArrayList<>();
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Household household = new Household();
                household.setHoldid(rs.getInt(1));
                household.setHouseid(rs.getInt(2));
                household.setHoldtel(rs.getString(3));
                household.setHoldnum(rs.getInt(4));
                household.setHoldpwd(rs.getString(5));
                householdAll.add(household);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(rs, psmt, conn);
        }
        return householdAll;
    }

    @Override
    public int deleteHouseholdById(int id) {

        String sql = "delete from household_info where holdid = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        int result = -1;
        try {
            conn = DataSourceUtil.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.releaseResource(psmt, conn);
        }
        return result;
    }
}
