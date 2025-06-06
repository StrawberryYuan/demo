package com.demo.dao;

import com.demo.model.JXYGrade;
import com.demo.util.JXYDBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JXYGradeDAO {

  public boolean insert(JXYGrade g) {
    String sql = "INSERT INTO grades (student_id, course_name, semester, daily_score, midterm_score, final_score) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, g.getStudentId());
      ps.setString(2, g.getCourseName());
      ps.setString(3, g.getSemester());
      ps.setDouble(4, g.getDailyScore());
      ps.setDouble(5, g.getMidtermScore());
      ps.setDouble(6, g.getFinalScore());
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean update(JXYGrade g) {
    String sql = "UPDATE grades SET course_name=?, semester=?, daily_score=?, midterm_score=?, final_score=? WHERE id=?";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, g.getCourseName());
      ps.setString(2, g.getSemester());
      ps.setDouble(3, g.getDailyScore());
      ps.setDouble(4, g.getMidtermScore());
      ps.setDouble(5, g.getFinalScore());
      ps.setInt(6, g.getId());
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean delete(int id) {
    String sql = "DELETE FROM grades WHERE id=?";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public List<JXYGrade> findByStudentId(int studentId) {
    List<JXYGrade> list = new ArrayList<>();
    String sql = "SELECT * FROM grades WHERE student_id=?";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, studentId);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        JXYGrade g = new JXYGrade();
        g.setId(rs.getInt("id"));
        g.setStudentId(rs.getInt("student_id"));
        g.setCourseName(rs.getString("course_name"));
        g.setSemester(rs.getString("semester"));
        g.setDailyScore(rs.getDouble("daily_score"));
        g.setMidtermScore(rs.getDouble("midterm_score"));
        g.setFinalScore(rs.getDouble("final_score"));
        list.add(g);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public JXYGrade findById(int id) {
    String sql = "SELECT * FROM grades WHERE id=?";
    try (Connection conn = JXYDBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        JXYGrade g = new JXYGrade();
        g.setId(rs.getInt("id"));
        g.setStudentId(rs.getInt("student_id"));
        g.setCourseName(rs.getString("course_name"));
        g.setSemester(rs.getString("semester"));
        g.setDailyScore(rs.getDouble("daily_score"));
        g.setMidtermScore(rs.getDouble("midterm_score"));
        g.setFinalScore(rs.getDouble("final_score"));
        return g;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  public List<JXYGrade> findAll() {
    List<JXYGrade> list = new ArrayList<>();
    String sql = "SELECT * FROM grades";
    try (Connection conn = JXYDBUtil.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
      while (rs.next()) {
        JXYGrade g = new JXYGrade();
        g.setId(rs.getInt("id"));
        g.setStudentId(rs.getInt("student_id"));
        g.setCourseName(rs.getString("course_name"));
        g.setSemester(rs.getString("semester"));
        g.setDailyScore(rs.getDouble("daily_score"));
        g.setMidtermScore(rs.getDouble("midterm_score"));
        g.setFinalScore(rs.getDouble("final_score"));
        list.add(g);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}