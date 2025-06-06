<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">用户登录</h2>

    <form action="login" method="post">
        <input type="hidden" name="op" value="login">
        <div class="mb-3">
            <label class="form-label">用户名</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">密码</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger">用户名或密码错误</div>
        <% } %>

        <button type="submit" class="btn btn-success">登录</button>
        <a href="JXYregister.jsp" class="btn btn-link">没有账号？注册</a>
    </form>
</div>
</body>
</html>