<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.model.JXYUser" %>

<%
    JXYUser user = (JXYUser) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>高校教务信息管理系统</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="p-5 mb-4 bg-white rounded-3 shadow-sm">
        <div class="container-fluid py-3">
            <h1 class="display-5 fw-bold">高校教务信息管理系统</h1>
            <p class="col-md-8 fs-5">欢迎使用本系统，支持学生成绩管理与评教功能。</p>

            <% if (user == null) { %>
            <a href="JXYlogin.jsp" class="btn btn-success btn-lg me-2">登录系统</a>
            <a href="JXYregister.jsp" class="btn btn-outline-primary btn-lg">注册账户</a>
            <% } else { %>
            <p class="mt-4">
                当前登录用户：<strong><%= user.getUsername() %></strong>
            <p>调试：当前用户ID为：<%= user.getId() %></p>
                （<%= "teacher".equals(user.getUserType()) ? "教师" : "学生" %>）
            </p>

            <% if ("teacher".equals(user.getUserType())) { %>
            <a href="JXYmanageGrades.jsp" class="btn btn-primary me-2">进入教师管理中心</a>
            <a href="evaluate?op=stats&teacherId=<%= user.getId() %>" class="btn btn-info me-2">查看评教统计</a>
            <% } else if ("student".equals(user.getUserType())) { %>
            <a href="grade?op=list&studentId=<%= user.getId() %>" class="btn btn-primary me-2">查看成绩</a>
            <a href="JXYevaluate.jsp" class="btn btn-warning me-2">我要评教</a>
            <a href="evaluate?op=my&studentId=<%= user.getId() %>" class="btn btn-outline-secondary me-2">我的评教记录</a>
            <% } %>

            <a href="JXYlogout.jsp" class="btn btn-danger">退出登录</a>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>