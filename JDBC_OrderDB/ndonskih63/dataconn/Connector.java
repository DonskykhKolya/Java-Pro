package com.gmail.ndonskih63.dataconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static final String DB_CONN = "jdbc:mysql://localhost:3306/order_db?serverTimezone=Europe/Kiev";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "ninetyonenikolya";

    public static Connection getConnection() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(DB_CONN, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return cn;
    }
}
