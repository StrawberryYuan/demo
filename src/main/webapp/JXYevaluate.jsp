<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>提交评教</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">提交评教</h2>
    <form method="post" action="evaluate?op=submit" class="bg-white p-4 rounded shadow-sm">
        <div class="mb-3">
            <label class="form-label">学生ID:</label>
            <input name="studentId" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">课程名称:</label>
            <input name="courseName" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">教师ID:</label>
            <input name="teacherId" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">评分（0-100）:</label>
            <input name="score" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">评教内容:</label>
            <textarea name="content" rows="5" class="form-control"></textarea>
        </div>
        <button type="submit" class="btn btn-primary w-100">提交评教</button>
    </form>

    <% if (request.getParameter("msg") != null) { %>
    <div class="alert alert-success text-center mt-3">
        <%= request.getParameter("msg") %>
    </div>
    <% } %>
</div>
</body>
</html>