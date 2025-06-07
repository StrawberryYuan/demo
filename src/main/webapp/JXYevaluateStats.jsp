<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%
  Map<String, Double> stats = (Map<String, Double>) request.getAttribute("stats");
%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>课程评教统计</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="bg-white p-4 rounded shadow-sm">
    <h2 class="mb-4 text-center text-primary">课程评教统计</h2>

    <table class="table table-bordered table-striped">
      <thead class="table-light">
      <tr>
        <th>课程名称</th>
        <th>平均评分</th>
      </tr>
      </thead>
      <tbody>
      <% if (stats != null && !stats.isEmpty()) {
        for (Map.Entry<String, Double> entry : stats.entrySet()) { %>
      <tr>
        <td><%= entry.getKey() %></td>
        <td><%= String.format("%.2f", entry.getValue()) %></td>
      </tr>
      <%   }
      } else { %>
      <tr>
        <td colspan="2" class="text-center text-muted">暂无统计数据</td>
      </tr>
      <% } %>
      </tbody>
    </table>

    <div class="text-end mt-3">
      <a href="JXYindex.jsp" class="btn btn-secondary">返回首页</a>
    </div>
  </div>
</div>
</body>
</html>