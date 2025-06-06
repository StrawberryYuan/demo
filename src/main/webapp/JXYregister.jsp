<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">用户注册</h2>
    <form action="user" method="post">
        <input type="hidden" name="op" value="register">
        <div class="mb-3">
            <label class="form-label">用户名</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">密码</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">性别</label>
            <select name="gender" class="form-select">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">年龄</label>
            <input type="number" name="age" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">电话</label>
            <input type="text" name="phone" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">用户类型</label>
            <select name="userType" class="form-select">
                <option value="student">学生</option>
                <option value="teacher">教师</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">注册</button>
        <a href="JXYlogin.jsp" class="btn btn-link">已有账号？登录</a>
    </form>
</div>
</body>
</html>