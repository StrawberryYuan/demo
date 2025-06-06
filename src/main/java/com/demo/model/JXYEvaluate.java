package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JXYEvaluate {
  private int id;
  private int studentId;
  private String courseName;
  private int teacherId;
  private int score;
  private String content;

  // Getter & Setter
}
