package com.demo.service;


import com.demo.dao.JXYUserDAO;
import com.demo.model.JXYUser;

public class JXYUserService {
  private final JXYUserDAO userDAO = new JXYUserDAO();

  /**
   * 注册用户
   * @param user 用户信息
   * @return 注册是否成功
   */
  public boolean register(JXYUser user) {
    // 你可以在这里添加用户名是否重复等校验逻辑
    return userDAO.register(user);
  }

  /**
   * 用户登录
   * @param username 用户名
   * @param password 密码
   * @return 成功则返回User对象，失败返回null
   */
  public JXYUser login(String username, String password) {
    return userDAO.login(username, password);
  }

  /**
   * 判断用户是否为教师
   * @param user 当前用户
   * @return 是否为教师
   */
  public boolean isTeacher(JXYUser user) {
    return user != null && "teacher".equals(user.getUserType());
  }

  /**
   * 判断用户是否为学生
   * @param user 当前用户
   * @return 是否为学生
   */
  public boolean isStudent(JXYUser user) {
    return user != null && "student".equals(user.getUserType());
  }
}
