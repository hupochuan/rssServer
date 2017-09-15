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
        Connection con = null;    //���������������ݿ��Connection����
        try {
            Class.forName(driverName);// ����Mysql��������
            con = DriverManager.getConnection(dbURL, userName, userPass);// ������������
        } catch (Exception e) {
            System.out.println("���ݿ�����ʧ��" + e.getMessage());
        }
        return con;    //���������������ݿ�����
    }

    /**
     * ��ʼ����
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
     * �ύ����
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
     * �ع�����
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

