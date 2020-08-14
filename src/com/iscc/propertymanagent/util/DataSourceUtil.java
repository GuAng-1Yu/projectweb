package com.iscc.propertymanagent.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import java.sql.*;

public class DataSourceUtil {

    // 生命连接池对象
    private static DruidDataSource dataSource = null;

    static {
        //实例化对象
        dataSource = new DruidDataSource();
        //设置连接数据库的驱动信息
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //设置连接数据库地址
        dataSource.setUrl("jdbc:mysql://localhost:3306/property_management?useUnicode=true&characterEncoding=UTF8");
        //设置连接数据用户名
        dataSource.setUsername("root");
        //设置连接数据密码
        dataSource.setPassword("root");
        //设置初始化连接数
        dataSource.setInitialSize(10);
        //设置连接的最大数
        dataSource.setMaxActive(50);
        //设置连接的最小数
        dataSource.setMinIdle(5);
        //设置最大等待时间
        dataSource.setMaxWait(1000 * 60);
    }


    /**
     * 返回一个连接对象
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void releaseResource(ResultSet rs, PreparedStatement psmt, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void releaseResource(PreparedStatement psmt, Connection conn) {
        releaseResource(null, psmt, conn);
    }


// 数据库链接测试
    @Test
    public void test(){
        Connection conn=null;
        try {
            conn = DataSourceUtil.getConnection();
            System.out.println(conn);
            Statement st = conn.createStatement();
            String sql=" insert into login values('zzz',1234)";
            int i = st.executeUpdate(sql);
            if (i!=-1){
                System.out.println("修改成功");
            }
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DataSourceUtil.releaseResource(null,null,conn);
        }

    }
}
