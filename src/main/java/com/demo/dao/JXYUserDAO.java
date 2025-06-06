package com.demo.dao;

import com.demo.model.JXYUser;
import com.demo.util.JXYDBUtil;

import java.sql.*;

public class JXYUserDAO {

  // 登录方法（已调试）
  public JXYUser login(String username, String password) {
    String sql = "SELECT * FROM users WHERE username = ?";
    try (Connection conn = JXYDBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      System.out.println("【登录尝试】用户名=" + username + "，密码=" + password);
      ps.setString(1, username);

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          String dbPassword = rs.getString("password");
          System.out.println("【数据库中密码】" + dbPassword);

          if (dbPassword.equals(password)) {
            System.out.println("【登录成功】");
            JXYUser user = new JXYUser();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setUserType(rs.getString("user_type"));
            return user;
          } else {
            System.out.println("【密码不匹配】输入=" + password + "，数据库=" + dbPassword);
          }
        } else {
          System.out.println("【用户名不存在】");
        }
      }

    } catch (Exception e) {
      System.out.println("【登录异常】");
      e.printStackTrace();
    }
    return null;
  }

  // ✅ 重新补上注册方法
  public boolean register(JXYUser user) {
    String sql = "INSERT INTO users (username, password, gender, age, phone, user_type) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = JXYDBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      System.out.println("【注册尝试】用户名=" + user.getUsername() + "，类型=" + user.getUserType());

      ps.setString(1, user.getUsername());
      ps.setString(2, user.getPassword());
      ps.setString(3, user.getGender());
      ps.setInt(4, user.getAge());
      ps.setString(5, user.getPhone());
      ps.setString(6, user.getUserType());

      int rows = ps.executeUpdate();
      System.out.println("【注册执行结果】插入行数 = " + rows);
      return rows > 0;

    } catch (Exception e) {
      System.out.println("【注册异常】");
      e.printStackTrace();
      return false;
    }
  }
}