package com.demo.controller;

import com.demo.model.JXYEvaluate;
import com.demo.service.JXYEvaluateService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.net.URLEncoder;

@WebServlet("/evaluate")
public class JXYEvaluateServlet extends HttpServlet {
  private final JXYEvaluateService service = new JXYEvaluateService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html;charset=UTF-8");

    String op = req.getParameter("op");

    if ("submit".equals(op)) {
      try {
        JXYEvaluate e = new JXYEvaluate();
        e.setStudentId(Integer.parseInt(req.getParameter("studentId")));
        e.setCourseName(req.getParameter("courseName"));
        e.setTeacherId(Integer.parseInt(req.getParameter("teacherId")));
        e.setScore((int) Double.parseDouble(req.getParameter("score")));
        e.setContent(req.getParameter("content"));

        boolean result = service.submitEvaluation(e);

        System.out.println("【提交评教】studentId=" + e.getStudentId() + "，课程=" + e.getCourseName() + "，结果=" + result);
        if (result) {
          resp.sendRedirect("JXYindex.jsp");
        } else {
          resp.sendRedirect("JXYevaluate.jsp");
        }
      } catch (Exception e) {
        e.printStackTrace();
        resp.sendRedirect("JXYevaluate.jsp?msg=" + URLEncoder.encode("提交失败", "UTF-8"));
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html;charset=UTF-8");

    String op = req.getParameter("op");

    if ("my".equals(op)) {
      try {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        List<JXYEvaluate> list = service.getStudentEvaluations(studentId);
        req.setAttribute("list", list);
        req.getRequestDispatcher("JXYmyEvaluations.jsp").forward(req, resp);
      } catch (Exception e) {
        e.printStackTrace();
        resp.sendRedirect("JXYindex.jsp?msg=查询评教记录失败");
      }

    } else if ("stats".equals(op)) {
      try {
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        Map<String, Double> stats = service.getStatsByTeacher(teacherId);
        req.setAttribute("stats", stats);
        req.getRequestDispatcher("JXYevaluateStats.jsp").forward(req, resp);
      } catch (Exception e) {
        e.printStackTrace();
        resp.sendRedirect("JXYindex.jsp?msg=统计失败");
      }
    }
  }
}