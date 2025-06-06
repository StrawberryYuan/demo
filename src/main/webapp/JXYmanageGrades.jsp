<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.model.JXYGrade" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.model.JXYUser" %>
<%
  JXYUser user = (JXYUser) session.getAttribute("user");
  if (user == null || !"teacher".equals(user.getUserType())) {
    response.sendRedirect("/JXYlogin.jsp");
    return;
  }

  List<JXYGrade> gradeList = (List<JXYGrade>) request.getAttribute("grades");
  String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>教师成绩管理</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-4">
  <h2>欢迎您，<%= user.getUsername() %>，教师成绩管理</h2>

  <% if (msg != null) { %>
  <div class="alert alert-info mt-3"><%= msg %></div>
  <% } %>

  <!-- 成绩新增表单 -->
  <form class="row g-3 bg-white p-4 rounded shadow-sm mt-4" action="../grade" method="post">
    <input type="hidden" name="op" value="add" />
    <h5>添加学生成绩</h5>

    <div class="col-md-2">
      <label class="form-label">学生ID</label>
      <input type="number" name="studentId" class="form-control" required>
    </div>
    <div class="col-md-2">
      <label class="form-label">课程名称</label>
      <input type="text" name="courseName" class="form-control" required>
    </div>
    <div class="col-md-2">
      <label class="form-label">学期</label>
      <input type="text" name="semester" class="form-control" required>
    </div>
    <div class="col-md-2">
      <label class="form-label">平时成绩</label>
      <input type="number" step="0.01" name="dailyScore" class="form-control" required>
    </div>
    <div class="col-md-2">
      <label class="form-label">阶段成绩</label>
      <input type="number" step="0.01" name="midtermScore" class="form-control" required>
    </div>
    <div class="col-md-2">
      <label class="form-label">期末成绩</label>
      <input type="number" step="0.01" name="finalScore" class="form-control" required>
    </div>

    <div class="col-12 text-end">
      <button type="submit" class="btn btn-primary">添加成绩</button>
    </div>
  </form>

  <!-- 成绩列表 -->
  <div class="mt-5">
    <h5>学生成绩列表</h5>
    <table class="table table-bordered table-hover bg-white">
      <thead class="table-light">
      <tr>
        <th>ID</th>
        <th>学生ID</th>
        <th>课程</th>
        <th>学期</th>
        <th>平时</th>
        <th>阶段</th>
        <th>期末</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <% if (gradeList != null && !gradeList.isEmpty()) {
        for (JXYGrade g : gradeList) { %>
      <tr>
        <td><%= g.getId() %></td>
        <td><%= g.getStudentId() %></td>
        <td><%= g.getCourseName() %></td>
        <td><%= g.getSemester() %></td>
        <td><%= g.getDailyScore() %></td>
        <td><%= g.getMidtermScore() %></td>
        <td><%= g.getFinalScore() %></td>
        <td>
          <a href="../grade?op=edit&id=<%= g.getId() %>" class="btn btn-sm btn-warning me-1">编辑</a>
          <a href="../grade?op=delete&id=<%= g.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('确认删除？')">删除</a>
        </td>
      </tr>
      <%  }
      } else { %>
      <tr><td colspan="8" class="text-center">暂无成绩数据</td></tr>
      <% } %>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>