package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JXYUser {
  private int id;
  private String username;
  private String password;
  private String gender;
  private int age;
  private String phone;
  private String userType; // student / teacher

  // Getter & Setter
}
