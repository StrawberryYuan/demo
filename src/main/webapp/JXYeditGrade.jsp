<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.model.JXYGrade" %>
<%
    JXYGrade grade = (JXYGrade) request.getAttribute("grade");
    if (grade == null) {
        response.sendRedirect("grade?op=listAll&msg=未找到成绩记录");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑成绩</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h3 class="mb-4">编辑成绩</h3>

    <form action="grade?op=update" method="post">
        <input type="hidden" name="id" value="<%= grade.getId() %>">

        <div class="mb-3">
            <label class="form-label">学生ID</label>
            <input type="text" class="form-control" value="<%= grade.getStudentId() %>" disabled>
        </div>

        <div class="mb-3">
            <label class="form-label">课程名称</label>
            <input type="text" class="form-control" name="courseName" value="<%= grade.getCourseName() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">学期</label>
            <input type="text" class="form-control" name="semester" value="<%= grade.getSemester() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">平时成绩</label>
            <input type="number" class="form-control" name="dailyScore" value="<%= grade.getDailyScore() %>" step="0.1" required>
        </div>

        <div class="mb-3">
            <label class="form-label">期中成绩</label>
            <input type="number" class="form-control" name="midtermScore" value="<%= grade.getMidtermScore() %>" step="0.1" required>
        </div>

        <div class="mb-3">
            <label class="form-label">期末成绩</label>
            <input type="number" class="form-control" name="finalScore" value="<%= grade.getFinalScore() %>" step="0.1" required>
        </div>

        <button type="submit" class="btn btn-primary">提交修改</button>
        <a href="grade?op=listAll" class="btn btn-secondary ms-2">返回</a>
    </form>
</div>
</body>
</html>