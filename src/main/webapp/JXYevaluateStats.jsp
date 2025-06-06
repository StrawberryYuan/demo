<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%
  Map<String, Double> stats = (Map<String, Double>) request.getAttribute("stats");
%>
<html>
<head><title>课程评教统计</title></head>
<body>
<h2>课程评教统计</h2>
<table border="1">
  <tr><th>课程名称</th><th>平均评分</th></tr>
  <% for (Map.Entry<String, Double> entry : stats.entrySet()) { %>
  <tr>
    <td><%= entry.getKey() %></td>
    <td><%= String.format("%.2f", entry.getValue()) %></td>
  </tr>
  <% } %>
</table>
</body>
</html>