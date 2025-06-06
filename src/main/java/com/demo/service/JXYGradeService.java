package com.demo.service;

import com.demo.dao.JXYGradeDAO;
import com.demo.model.JXYGrade;

import java.util.List;

public class JXYGradeService {
  private final JXYGradeDAO dao = new JXYGradeDAO();

  public boolean addGrade(JXYGrade g) {
    return dao.insert(g);
  }

  public boolean updateGrade(JXYGrade g) {
    return dao.update(g);
  }

  public boolean deleteGrade(int id) {
    return dao.delete(id);
  }

  public List<JXYGrade> getGradesByStudent(int studentId) {
    return dao.findByStudentId(studentId);
  }

  public List<JXYGrade> getAllGrades() {
    return dao.findAll();
  }

  public JXYGrade getGradeById(int id) {
    return dao.findById(id); // ✔ 确保 DAO 有对应方法
  }
}