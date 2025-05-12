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

/**
 * StudentServlet —— 统一处理学生信息的 CRUD 接口
 * <p>
 * URL 映射：/students
 * 约定：
 * • GET  → 查询全部学生（返回 Layui 表格标准 JSON）
 * • POST → 新增学生（请求体为 JSON，字段同 Student）
 * • PUT  → 更新学生（请求体为 JSON，必须包含 id）
 * • DELETE → 逻辑删除学生（通过 ?id=xxx 传参）
 * <p>
 * 返回格式：统一设置为 application/json;charset=UTF‑8，便于前后端分离。
 */
@WebServlet("/students")
public class StudentServlet extends HttpServlet {

  /** 数据访问对象，封装所有数据库操作 */
  private final StudentDao dao = new StudentDao();
  /** Jackson 对象，用于 JSON ↔ Java 对象转换 */
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * 处理 GET 请求 —— 查询学生列表
   * 返回格式满足 Layui table 组件的规范：{code, msg, count, data}
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");

    try {
      // 1. 查询数据库
      List<Student> students = dao.findAll();

      // 2. 组装返回结果（Layui 期待的字段）
      Map<String, Object> result = new HashMap<>();
      result.put("code", 0);               // 0 表示成功
      result.put("msg", "");               // 留空即可
      result.put("count", students.size()); // 数据总量
      result.put("data", students);         // 真实数据

      // 3. 序列化为 JSON 输出
      mapper.writeValue(resp.getWriter(), result);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      mapper.writeValue(resp.getWriter(),
          Map.of("code", 500, "msg", "查询失败"));
    }
  }

  /**
   * 处理 POST 请求 —— 新增学生
   * 前端发送的请求体应为 JSON，例如：
   * {"name":"张三","gender":"男","age":18,"score":"90"}
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");

    try {
      // 1. 反序列化请求体为 Student 对象
      Student student = mapper.readValue(req.getReader(), Student.class);

      // 2. 插入数据库
      dao.insert(student);

      // 3. 返回成功信息
      resp.getWriter().write("{\"msg\":\"添加成功\"}");
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      resp.getWriter().write("{\"msg\":\"添加失败\"}");
    }
  }

  /**
   * 处理 PUT 请求 —— 更新学生
   * 请求体需包含 id，用于确定更新目标
   */
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

  /**
   * 处理 DELETE 请求 —— 逻辑删除学生
   * 通过 URL ?id=xxx 传递主键
   */
  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");

    try {
      // 1. 解析 id 参数
      int id = Integer.parseInt(req.getParameter("id"));

      // 2. 逻辑删除（deleted = 1）
      dao.deleteById(id);

      // 3. 返回结果
      resp.getWriter().write("{\"msg\":\"删除成功\"}");
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(500);
      resp.getWriter().write("{\"msg\":\"删除失败\"}");
    }
  }
}