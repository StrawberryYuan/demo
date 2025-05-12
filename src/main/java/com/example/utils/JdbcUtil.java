package com.example.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtil {
  private static DataSource dataSource;

  static {
    try {
      Properties props = new Properties();
      InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
      props.load(in);
      dataSource = DruidDataSourceFactory.createDataSource(props);
    } catch (Exception e) {
      throw new RuntimeException("初始化数据库连接池失败", e);
    }
  }

  public static DataSource getDataSource() {
    return dataSource;
  }
}
