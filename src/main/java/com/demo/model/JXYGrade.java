package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JXYGrade {
  private int id;
  private int studentId;
  private String courseName;
  private String semester;
  private double dailyScore;
  private double midtermScore;
  private double finalScore;

  // Getter & Setter
}
