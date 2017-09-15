package com.ycl.DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.zzf.DBUtils.DBUtils;

public class DbUtil {

    private static String driverName = "com.mysql.jdbc.Driver";
 
    
    private static String dbURL = "jdbc:mysql://rdsbuefubbuefub.mysql.rds.aliyuncs.com:3306/jinggangshan?useUnicode=true&characterEncoding=UTF-8";
    private static String userName = "insurance";
    private static String userPass = "123456";
    //public static ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

    public static Connection getConnection() {
        Connection con = null;    //创建用于连接数据库的Connection对象
        try {
            Class.forName(driverName);// 加载Mysql数据驱动
            con = DriverManager.getConnection(dbURL, userName, userPass);// 创建数据连接
        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.getMessage());
        }
        return con;    //返回所建立的数据库连接
    }

    /**
     * 开始事务
     *
     * @param cnn
     */
    public static void beginTransaction(Connection cnn) {
        if (cnn != null) {
            try {
                if (cnn.getAutoCommit()) {
                    cnn.setAutoCommit(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提交事务
     *
     * @param cnn
     */
    public static void commitTransaction(Connection cnn) {
        if (cnn != null) {
            try {
                if (!cnn.getAutoCommit()) {
                    cnn.commit();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 回滚事务
     *
     * @param cnn
     */
    public static void rollBackTransaction(Connection cnn) {
        if (cnn != null) {
            try {
                if (!cnn.getAutoCommit()) {
                    cnn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

