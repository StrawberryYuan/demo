package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 简化getter和setter方法
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  private Integer id;
  private String name;
  private String gender;
  private Integer age;
  private String score;
  private Integer deleted;
}
