package com.demo.controller;

import com.demo.dao.JXYUserDAO;
import com.demo.model.JXYUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class JXYLoginServlet extends HttpServlet {

  private final JXYUserDAO dao = new JXYUserDAO();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    JXYUser user = dao.login(username, password);

    if (user != null) {
      HttpSession session = req.getSession();
      session.setAttribute("user", user);

      // 根据身份跳转到不同首页
      if ("teacher".equals(user.getUserType())) {
        resp.sendRedirect("JXYindex.jsp");
      } else if ("student".equals(user.getUserType())) {
        resp.sendRedirect("grade?op=list&studentId=" + user.getId());
      } else {
        resp.sendRedirect("JXYindex.jsp"); // 默认跳转页
      }
    }
  }
}
