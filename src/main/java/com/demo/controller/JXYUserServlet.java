package com.demo.controller;

import com.demo.model.JXYUser;
import com.demo.service.JXYUserService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user")
public class JXYUserServlet extends HttpServlet {
  private final JXYUserService service = new JXYUserService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String op = req.getParameter("op");

    if ("register".equals(op)) {
      JXYUser user = new JXYUser();
      user.setUsername(req.getParameter("username"));
      user.setPassword(req.getParameter("password"));
      user.setGender(req.getParameter("gender"));
      user.setAge(Integer.parseInt(req.getParameter("age")));
      user.setPhone(req.getParameter("phone"));

      String userType = req.getParameter("userType");
      if (!"student".equals(userType) && !"teacher".equals(userType)) {
        System.out.println("非法 userType: " + userType);
        resp.sendRedirect("JXYregister.jsp?error=invalidUserType");
        return;
      }
      user.setUserType(userType);

      System.out.println("注册用户: " + user.getUsername() + ", 类型: " + userType);
      boolean success = service.register(user);
      System.out.println("注册结果: " + success);

      resp.sendRedirect(success ? "JXYlogin.jsp" : "JXYregister.jsp?error=registerFailed");

    } else if ("login".equals(op)) {
      String username = req.getParameter("username");
      String password = req.getParameter("password");
      JXYUser user = service.login(username, password);
      if (user != null) {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        if ("teacher".equals(user.getUserType())) {
          resp.sendRedirect("JXYmanageGrades.jsp");
        } else {
          resp.sendRedirect("JXYviewGrades.jsp");
        }
      } else {
        resp.sendRedirect("JXYlogin.jsp?error=1");
      }
    }
  }
}