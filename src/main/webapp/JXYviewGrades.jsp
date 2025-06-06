<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.model.JXYUser" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.model.JXYGrade" %>
<%
  JXYUser user = (JXYUser) session.getAttribute("user");
  if (user == null || !"student".equals(user.getUserType())) {
    response.sendRedirect("/JXYlogin.jsp");
    return;
  }

  List<JXYGrade> grades = (List<JXYGrade>) request.getAttribute("grades");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>我的成绩</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2>欢迎你，<%= user.getUsername() %>，以下是你的成绩：</h2>

  <table class="table table-bordered table-hover bg-white mt-4">
    <thead class="table-light">
    <tr>
      <th>课程名称</th>
      <th>学期</th>
      <th>平时成绩</th>
      <th>阶段成绩</th>
      <th>期末成绩</th>
      <th>总评成绩</th>
    </tr>
    </thead>
    <tbody>
    <% if (grades != null && !grades.isEmpty()) {
      for (JXYGrade g : grades) {
        double total = g.getDailyScore() * 0.3 + g.getMidtermScore() * 0.3 + g.getFinalScore() * 0.4;
    %>
    <tr>
      <td><%= g.getCourseName() %></td>
      <td><%= g.getSemester() %></td>
      <td><%= g.getDailyScore() %></td>
      <td><%= g.getMidtermScore() %></td>
      <td><%= g.getFinalScore() %></td>
      <td><%= String.format("%.2f", total) %></td>
    </tr>
    <%  }
    } else { %>
    <tr>
      <td colspan="6" class="text-center">暂无成绩记录</td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <a href="<%= request.getContextPath() %>/JXYindex.jsp" class="btn btn-secondary mt-3">返回首页</a>
</div>
</body>
</html>
