package com.demo.dao;

import com.demo.model.JXYEvaluate;
import com.demo.util.JXYDBUtil;

import java.sql.*;
import java.util.*;

public class JXYEvaluateDAO {

  public boolean insert(JXYEvaluate e) {
    String sql = "INSERT INTO evaluates (student_id, course_name, teacher_id, score, content) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, e.getStudentId());
      ps.setString(2, e.getCourseName());
      ps.setInt(3, e.getTeacherId());
      ps.setDouble(4, e.getScore());
      ps.setString(5, e.getContent());
      return ps.executeUpdate() == 1;
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public List<JXYEvaluate> findByStudent(int studentId) {
    List<JXYEvaluate> list = new ArrayList<>();
    String sql = "SELECT * FROM evaluates WHERE student_id = ?";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, studentId);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        JXYEvaluate e = new JXYEvaluate();
        e.setId(rs.getInt("id"));
        e.setStudentId(rs.getInt("student_id"));
        e.setCourseName(rs.getString("course_name"));
        e.setTeacherId(rs.getInt("teacher_id"));
        e.setScore((int) rs.getDouble("score"));
        e.setContent(rs.getString("content"));
        list.add(e);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return list;
  }

  public Map<String, Double> getAvgScoreByTeacher(int teacherId) {
    Map<String, Double> map = new HashMap<>();
    String sql = "SELECT course_name, AVG(score) as avg_score FROM evaluates WHERE teacher_id = ? GROUP BY course_name";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, teacherId);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        map.put(rs.getString("course_name"), rs.getDouble("avg_score"));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return map;
  }
}