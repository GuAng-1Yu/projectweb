package com.iscc.propertymanagent.dao.impl;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.iscc.propertymanagent.dao.HouseholdDAO;
import com.iscc.propertymanagent.domain.Household;
import com.iscc.propertymanagent.util.DataSourceUtil;

import javax.naming.Name;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseholdDAOImpl implements HouseholdDAO {
    @Override
    public List<Household> queryAllTypeByCondition(String name) {
        List<Household> households = new ArrayList<>();
        String sql = "SELECT * FROM household_info where 1=1";
        if (name != null && !"".equals(name.trim())){
            sql += " and holdid like concat('%',?'%')";
        }

            try {
                Connection conn = DataSourceUtil.getConnection();
                PreparedStatement psmt = conn.prepareStatement(sql);
                if (name != null && !"".equals(name.trim())){
                    psmt.setString(1,name);
                }
                ResultSet rs = psmt.executeQuery();
                while (rs.next()){
                    Household household = new Household();
                    household.setHoldid(rs.getInt(1));
                    household.setHouseid(rs.getInt(2));
                    household.setHoldtel(rs.getString(3));
                    household.setHoldnum(rs.getInt(4));
                    household.setHoldpwd(rs.getString(5));
                    households.add(household);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

            }
        return households;
    }

    @Override
    public int add(Type type) {
        return 0;
    }

    @Override
    public int update(Type type) {
        return 0;
    }

    @Override
    public int del(int id) {
        return 0;
    }

    @Override
    public List<Type> queryAll() {


        return null;
    }

    @Override
    public Type queryById(int id) {



        return null;
    }
}
