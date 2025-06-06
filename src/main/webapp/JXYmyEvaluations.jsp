<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.model.JXYEvaluate" %>
<%
    List<JXYEvaluate> list = (List<JXYEvaluate>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的评教记录</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">我的评教记录</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle bg-white shadow-sm">
            <thead class="table-primary text-center">
            <tr>
                <th>课程</th>
                <th>教师ID</th>
                <th>评分</th>
                <th>内容</th>
            </tr>
            </thead>
            <tbody>
            <% for (JXYEvaluate e : list) { %>
            <tr>
                <td><%= e.getCourseName() %></td>
                <td><%= e.getTeacherId() %></td>
                <td><%= e.getScore() %></td>
                <td><%= e.getContent() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>