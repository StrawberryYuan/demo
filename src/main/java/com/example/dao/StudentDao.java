package com.example.dao;

import com.example.entity.Student;
import com.example.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDao学生数据访问层<br>
 * 负责对student表执行增删改查（CRUD）操作。
 * <p>
 * 说明：
 * ‑ 逻辑删除：通过deleted字段标记，0 表示有效，1 表示已删除。
 * ‑ 所有数据库连接均使用 try‑with‑resources 自动关闭，避免资源泄漏。
 * </p>
 */
public class StudentDao {

  /**
   * 查询所有未被逻辑删除的学生记录
   *
   * @return 学生列表
   * @throws Exception 数据库访问异常
   */
  public List<Student> findAll() throws Exception {
    List<Student> list = new ArrayList<>();

    // SQL：仅查询 deleted = 0 的记录
    String sql = "SELECT * FROM student WHERE deleted = 0";

    // try‑with‑resources 会在块结束后自动关闭连接、语句及结果集
    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

      // 遍历结果集并封装为 Student 对象
      while (rs.next()) {
        list.add(new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("gender"),
            rs.getInt("age"),
            rs.getString("score"),   // 注意：此处假设 score 字段类型为 VARCHAR
            rs.getInt("deleted")
        ));
      }
    }

    return list;
  }

  /**
   * 新增学生（默认 deleted = 0）
   *
   * @param student 待保存的学生对象
   * @throws Exception 数据库访问异常
   */
  public void insert(Student student) throws Exception {
    String sql = "INSERT INTO student (name, gender, age, score, deleted) VALUES (?, ?, ?, ?, 0)";

    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      // 为占位符设置参数
      stmt.setString(1, student.getName());
      stmt.setString(2, student.getGender());
      stmt.setInt(3, student.getAge());
      stmt.setString(4, student.getScore());

      stmt.executeUpdate();   // 执行插入
    }
  }

  /**
   * 更新学生信息
   *
   * @param student 包含 id 的更新对象
   * @throws Exception 数据库访问异常
   */
  public void update(Student student) throws Exception {
    String sql = "UPDATE student SET name = ?, gender = ?, age = ?, score = ? WHERE id = ?";

    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, student.getName());
      stmt.setString(2, student.getGender());
      stmt.setInt(3, student.getAge());
      stmt.setString(4, student.getScore());
      stmt.setInt(5, student.getId());

      stmt.executeUpdate();   // 执行更新
    }
  }

  /**
   * 逻辑删除：把对应记录的 deleted 置为 1
   *
   * @param id 学生主键
   * @throws Exception 数据库访问异常
   */
  public void deleteById(int id) throws Exception {
    String sql = "UPDATE student SET deleted = 1 WHERE id = ?";

    try (Connection conn = JdbcUtil.getDataSource().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setInt(1, id);
      stmt.executeUpdate();   // 执行逻辑删除
    }
  }
}