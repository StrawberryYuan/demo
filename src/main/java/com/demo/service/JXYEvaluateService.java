package com.demo.service;

import com.demo.dao.JXYEvaluateDAO;
import com.demo.model.JXYEvaluate;

import java.util.List;
import java.util.Map;

public class JXYEvaluateService {
  private final JXYEvaluateDAO dao = new JXYEvaluateDAO();

  public boolean submitEvaluation(JXYEvaluate e) {
    return dao.insert(e);
  }

  public List<JXYEvaluate> getStudentEvaluations(int studentId) {
    return dao.findByStudent(studentId);
  }

  public Map<String, Double> getStatsByTeacher(int teacherId) {
    return dao.getAvgScoreByTeacher(teacherId);
  }
}
