package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
