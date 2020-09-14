package com.lyoyang.utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * @Description:
 * @Author: wuzhuang
 * @Date: 2020/8/18 16:53
 **/
public class JDBCUtils {

    private JDBCUtils() {
    }

    private static Connection con;


    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    static {

        try {
            url = "jdbc:mysql://10.10.220.120:3306/ejavashop";
            username = "root";
            password = "urcs@2018";
            driver = "com.mysql.jdbc.Driver";
            //4.注册驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     *
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        return con;
    }


    /**
     * @param con
     * @param st
     * @param rs  select 查询关闭发方法：关闭结果集,SQL执行对象，连接对象三个的资源
     */
    public static void close(Connection con, Statement st, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


    /**
     * @param con
     * @param st  关闭资源的重载方法，增删改的时候没有结果集对象，使用该方法关闭
     */
    public static void close(Connection con, Statement st) {

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


    /**
     * version 1.0
     * 根据输入的SQL返回对象
     *
     * @param sql
     * @param cls
     * @return
     */
    public static <T> List<T> query(String sql, Class<T> cls) {
        Map<String, Object> map = new HashMap<>();
        List<T> list = new ArrayList<>();
        Field[] fields = cls.getDeclaredFields();
        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            if (rs != null) {

            }


            ResultSetMetaData data = rs.getMetaData();
            while (rs.next()) {
                T obj = cls.newInstance();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);
                    map.put(columnName, rs.getObject(i));
                }
                for (Field field : fields) {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = map.get(name);
                    if (!Modifier.isFinal(field.getModifiers())) {
                        if (value instanceof Boolean) {
                            int intValue = (boolean) value ? 1 : 0;
                            field.set(obj, intValue);
                        } else {
                            field.set(obj, value);
                        }
                    }
                }
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, statement, rs);
        }
        return Collections.emptyList();
    }

//
//    //定义一个方法，查询user表的数据将其封装为对象，然后装载集合，返回。
//    public static Map<String, Object> getRow() {
//        Connection conn = null;
//        Statement st = null;
//        ResultSet rs = null;
//        try {
//            conn = com.ejavashop.web.util.JDBCUtils.getConnection();
//            //定义sql
//            String sql = "SELECT * FROM user";
//            //获取执行sql的对象
//            st = conn.createStatement();
//            //执行sql
//            rs = st.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close(conn, st, rs);
//        }
//        return null;
//    }



    public static void insert(String sql) {
        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, statement, rs);
        }
    }


    public static ResultSet query(String sql) {
        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, statement, rs);
        }
        return rs;
    }


}
