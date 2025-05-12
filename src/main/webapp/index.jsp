<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>StudentGrades</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/layui@2.8.15/dist/css/layui.css">
</head>
<body>
<table class="layui-hide" id="studentTable" lay-filter="studentFilter"></table>

<!-- 工具条模板 -->
<script type="text/html" id="toolbarDemo">
    <button class="layui-btn layui-btn-sm" lay-event="add">Add</button>
</script>

<!-- 表单模板 -->
<script type="text/html" id="studentFormTpl">
    <form class="layui-form" lay-filter="studentForm" style="padding: 20px;">
        <input type="hidden" name="id">
        <div class="layui-form-item">
            <label class="layui-form-label">Name</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required"
                       placeholder="Enter name" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Gender</label>
            <div class="layui-input-block">
                <select name="gender" required>
                    <option value="">Please select</option>
                    <option value="Male">男</option>
                    <option value="Female">女</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Age</label>
            <div class="layui-input-block">
                <input type="number" name="age" required lay-verify="number" placeholder="Enter age"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Score</label>
            <div class="layui-input-block">
                <input type="text" name="score" required lay-verify="required"
                       placeholder="Enter score" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submitStudentForm">Submit</button>
            </div>
        </div>
    </form>
</script>

<script src="https://cdn.jsdelivr.net/npm/layui@2.8.15/dist/layui.js"></script>
<script>
  layui.use(['table', 'layer', 'form'], function () {
    const table = layui.table;
    const layer = layui.layer;
    const form = layui.form;

    table.render({
      elem: '#studentTable',
      url: '/demo/students',
      method: 'GET',
      toolbar: '#toolbarDemo',
      cols: [[
        {field: 'id', title: 'ID', width: 60},
        {field: 'name', title: 'Name'},
        {field: 'gender', title: 'Gender'},
        {field: 'age', title: 'Age'},
        {field: 'score', title: 'Score'},
        {
          title: 'Operate', width: 160, templet: function (d) {
            return [
              '<button class="layui-btn layui-btn-xs" onclick="edit(' + d.id + ')">Edit</button>',
              '<button class="layui-btn layui-btn-danger layui-btn-xs" onclick="del(' + d.id
              + ')">Delete</button>'
            ].join('');
          }
        }
      ]]
    });

    // 打开表单弹窗方法
    function openStudentForm(title, data = {}) {
      layer.open({
        type: 1,
        area: ['500px'],
        title: title,
        content: document.getElementById('studentFormTpl').innerHTML,
        success: function () {
          form.val('studentForm', data);
          form.render();
        }
      });
    }

    // 点击工具栏 Add 按钮
    table.on('toolbar(studentFilter)', function (obj) {
      if (obj.event === 'add') {
        openStudentForm('Add Student');
      }
    });

    // Edit 按钮
    window.edit = function (id) {
      fetch('/demo/students')
          .then(res => res.json())
          .then(data => {
            const student = data.data.find(item => item.id === id);
            openStudentForm('Edit Student', student);
          });
    };

    // Delete 按钮
    window.del = function (id) {
      console.log('==> del 函数收到的 id =', id);
      fetch('/demo/students?id=' + id, {
        method: 'DELETE'
      }).then(res => res.json()).then(() => {
        table.reload('studentTable');
      });
    };

    // 表单提交
    form.on('submit(submitStudentForm)', function (data) {
      const formData = data.field;
      const method = formData.id ? 'PUT' : 'POST';
      fetch('/demo/students', {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
      }).then(res => res.json()).then(() => {
        layer.closeAll();
        table.reload('studentTable');
      });
      return false;
    });
  });
</script>
</body>
</html>
