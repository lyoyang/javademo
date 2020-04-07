package com.lyoyang.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMySQL {

    public static void main(String[] args) {
        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String host = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
        String user = "root";
        String passwd = "root";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(host, user, passwd);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
