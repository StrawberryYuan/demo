package com.demo.util;


import java.sql.*;

public class JXYDBUtil {
  private static final String URL = "jdbc:mysql://localhost:3306/school_system?useSSL=false&characterEncoding=utf8";
  private static final String USER = "root";
  private static final String PASSWORD = "ZCbm147258";
  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
