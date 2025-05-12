package com.example.dao;

import com.example.entity.Student;
import com.example.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

  public List<Student> findAll() throws Exception {
    List<Student> list = new ArrayList<>();
    String sql = "SELECT * FROM student WHERE deleted = 0";

    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        list.add(new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("gender"),
            rs.getInt("age"),
            rs.getString("score"),
            rs.getInt("deleted")
        ));
      }
    }

    return list;
  }

  public void insert(Student student) throws Exception {
    String sql = "INSERT INTO student (name, gender, age, score, deleted) VALUES (?, ?, ?, ?, 0)";
    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, student.getName());
      stmt.setString(2, student.getGender());
      stmt.setInt(3, student.getAge());
      stmt.setString(4, student.getScore());
      stmt.executeUpdate();
    }
  }

  public void update(Student student) throws Exception {
    String sql = "UPDATE student SET name=?, gender=?, age=?, score=? WHERE id=?";
    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, student.getName());
      stmt.setString(2, student.getGender());
      stmt.setInt(3, student.getAge());
      stmt.setString(4, student.getScore());
      stmt.setInt(5, student.getId());
      stmt.executeUpdate();
    }
  }

  public void deleteById(int id) throws Exception {
    String sql = "UPDATE student SET deleted = 1 WHERE id = ?";
    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    }
  }
}
