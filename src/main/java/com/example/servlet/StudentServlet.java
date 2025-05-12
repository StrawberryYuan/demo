package com.example.servlet;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

  private final StudentDao dao = new StudentDao();
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");
    try {
      List<Student> students = dao.findAll();
      // 构造 Layui 所需格式
      Map<String, Object> result = new HashMap<>();
      result.put("code", 0);
      result.put("msg", "");
      result.put("count", students.size());
      result.put("data", students);

      mapper.writeValue(resp.getWriter(), result);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      mapper.writeValue(resp.getWriter(), Map.of("code", 500, "msg", "查询失败"));
    }
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");
    try {
      Student student = mapper.readValue(req.getReader(), Student.class);
      dao.insert(student);
      resp.getWriter().write("{\"msg\":\"添加成功\"}");
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      resp.getWriter().write("{\"msg\":\"添加失败\"}");
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");
    try {
      Student student = mapper.readValue(req.getReader(), Student.class);
      dao.update(student);
      resp.getWriter().write("{\"msg\":\"更新成功\"}");
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      resp.getWriter().write("{\"msg\":\"更新失败\"}");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");
    try {
      int id = Integer.parseInt(req.getParameter("id"));
      dao.deleteById(id);
      resp.getWriter().write("{\"msg\":\"删除成功\"}");
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      resp.getWriter().write("{\"msg\":\"删除失败\"}");
    }
  }
}
