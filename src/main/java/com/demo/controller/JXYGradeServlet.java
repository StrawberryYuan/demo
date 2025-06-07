package com.demo.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import com.demo.model.JXYGrade;
import com.demo.service.JXYGradeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/grade")
public class JXYGradeServlet extends HttpServlet {
  private final JXYGradeService service = new JXYGradeService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String op = req.getParameter("op");

    if ("add".equals(op)) {
      JXYGrade g = new JXYGrade();
      g.setStudentId(Integer.parseInt(req.getParameter("studentId")));
      g.setCourseName(req.getParameter("courseName"));
      g.setSemester(req.getParameter("semester"));
      g.setDailyScore(Double.parseDouble(req.getParameter("dailyScore")));
      g.setMidtermScore(Double.parseDouble(req.getParameter("midtermScore")));
      g.setFinalScore(Double.parseDouble(req.getParameter("finalScore")));

      boolean result = service.addGrade(g);
      List<JXYGrade> list = service.getAllGrades();
      req.setAttribute("grades", list);
      req.setAttribute("msg", result ? "添加成功" : "添加失败");
      req.getRequestDispatcher("/JXYmanageGrades.jsp").forward(req, resp);

    } else if ("update".equals(op)) {
      JXYGrade g = new JXYGrade();
      g.setId(Integer.parseInt(req.getParameter("id")));
      g.setCourseName(req.getParameter("courseName"));
      g.setSemester(req.getParameter("semester"));
      g.setDailyScore(Double.parseDouble(req.getParameter("dailyScore")));
      g.setMidtermScore(Double.parseDouble(req.getParameter("midtermScore")));
      g.setFinalScore(Double.parseDouble(req.getParameter("finalScore")));

      boolean result = service.updateGrade(g);
      List<JXYGrade> list = service.getAllGrades();
      req.setAttribute("grades", list);
      req.setAttribute("msg", result ? "修改成功" : "修改失败");
      req.getRequestDispatcher("/JXYmanageGrades.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String op = req.getParameter("op");

    if ("list".equals(op)) {
      int studentId = Integer.parseInt(req.getParameter("studentId"));
      List<JXYGrade> list = service.getGradesByStudent(studentId);
      req.setAttribute("grades", list);
      req.getRequestDispatcher("/JXYviewGrades.jsp").forward(req, resp);

    } else if ("listAll".equals(op)) {
      List<JXYGrade> list = service.getAllGrades();
      req.setAttribute("grades", list);
      req.getRequestDispatcher("/JXYmanageGrades.jsp").forward(req, resp);

    } else if ("delete".equals(op)) {
      int id = Integer.parseInt(req.getParameter("id"));
      boolean result = service.deleteGrade(id);
      List<JXYGrade> list = service.getAllGrades();
      req.setAttribute("grades", list);
      req.setAttribute("msg", result ? "删除成功" : "删除失败");
      req.getRequestDispatcher("/JXYmanageGrades.jsp").forward(req, resp);

    } else if ("edit".equals(op)) {
      int id = Integer.parseInt(req.getParameter("id"));
      JXYGrade grade = service.getGradeById(id);
      req.setAttribute("grade", grade);
      req.getRequestDispatcher("/JXYeditGrade.jsp").forward(req, resp);
    }
  }
}