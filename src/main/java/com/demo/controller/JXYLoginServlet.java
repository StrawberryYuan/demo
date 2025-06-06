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
      // ✅ 登录成功：写入 session
      HttpSession session = req.getSession();
      session.setAttribute("user", user);

      resp.sendRedirect("JXYindex.jsp");
    } else {
      // ❌ 登录失败：跳回登录页
      resp.sendRedirect("JXYlogin.jsp?msg=用户名或密码错误");
    }
  }
}
