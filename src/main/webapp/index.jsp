<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--
学生成绩管理页面
依赖：
• Layui 2.8.15（CSS、JS）
• StudentServlet 后端接口 (/students)
功能：
• 表格异步加载学生数据
• 添加 / 编辑 / 删除 学生信息
• 采用 JSON 交互，符合 Layui table 组件数据格式
-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生成绩</title>
    <!-- Layui 样式表 -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/layui@2.8.15/dist/css/layui.css">
</head>
<body>
<!--
  Layui table 组件占位元素：
    • class="layui-hide" 让 table 初始隐藏
    • lay-filter="studentFilter" 用于事件监听
-->
<table class="layui-hide" id="studentTable" lay-filter="studentFilter"></table>

<!-- 工具条模板：table 顶部工具栏（只有“添加”按钮） -->
<script type="text/html" id="toolbarDemo">
    <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
</script>

<!-- 表单模板：弹窗表单，用于新增 / 编辑学生 -->
<script type="text/html" id="studentFormTpl">
    <!-- lay-filter="studentForm" 方便使用 form.val 回填数据 -->
    <form class="layui-form" lay-filter="studentForm" style="padding: 20px;">
        <!-- 隐藏域：编辑时携带 id，新增时为空 -->
        <input type="hidden" name="id">
        <!-- 姓名 -->
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required"
                       placeholder="输入姓名" class="layui-input">
            </div>
        </div>
        <!-- 性别 -->
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <select name="gender" required>
                    <option value="">请选择</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>
        <!-- 年龄 -->
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block">
                <input type="number" name="age" required lay-verify="number"
                       placeholder="输入年龄" class="layui-input">
            </div>
        </div>
        <!-- 绩点 -->
        <div class="layui-form-item">
            <label class="layui-form-label">绩点</label>
            <div class="layui-input-block">
                <input type="text" name="score" required lay-verify="required"
                       placeholder="输入绩点" class="layui-input">
            </div>
        </div>
        <!-- 提交按钮 -->
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submitStudentForm">提交</button>
            </div>
        </div>
    </form>
</script>

<!-- Layui 脚本 -->
<script src="https://cdn.jsdelivr.net/npm/layui@2.8.15/dist/layui.js"></script>
<script>
  // 使用 table、layer、form 模块
  layui.use(['table', 'layer', 'form'], function () {
    const table = layui.table; // 表格
    const layer = layui.layer; // 弹窗
    const form = layui.form;   // 表单

    /** 1. 渲染学生表格 **/
    table.render({
      elem: '#studentTable',    // 绑定元素
      url: '/demo/students',    // 后端接口（GET）
      method: 'GET',
      toolbar: '#toolbarDemo',  // 顶部工具栏模板
      cols: [[
        {field: 'id',     title: 'ID',   width: 60},
        {field: 'name',   title: '姓名'},
        {field: 'gender', title: '性别'},
        {field: 'age',    title: '年龄'},
        {field: 'score',  title: '绩点'},
        {
          title: '操作',
          width: 160,
          // templet 自定义单元格：提供“编辑 / 删除”按钮
          templet: function (d) {
            return [
              '<button class="layui-btn layui-btn-xs" onclick="edit(' + d.id + ')">编辑</button>',
              '<button class="layui-btn layui-btn-danger layui-btn-xs" onclick="del(' + d.id + ')">删除</button>'
            ].join('');
          }
        }
      ]]
    });

    /**
     * 打开“学生表单”弹窗
     * @param {string} title 弹窗标题
     * @param {object} data  需要回填的表单数据（新增时为空对象）
     */
    function openStudentForm(title, data = {}) {
      layer.open({
        type: 1,            // 页面层
        area: ['500px'],    // 宽度
        title: title,       // 标题
        content: document.getElementById('studentFormTpl').innerHTML,
        success: function () {
          // 回填并渲染表单
          form.val('studentForm', data);
          form.render();
        }
      });
    }

    /** 2. 监听工具栏（添加按钮） **/
    table.on('toolbar(studentFilter)', function (obj) {
      if (obj.event === 'add') {
        openStudentForm('添加学生');
      }
    });

    /** 3. 编辑按钮（window 级函数，供 templet 调用） **/
    window.edit = function (id) {
      // 重新请求一遍全部数据，找到目标记录
      fetch('/demo/students')
          .then(res => res.json())
          .then(data => {
            const student = data.data.find(item => item.id === id);
            openStudentForm('编辑学生信息', student);
          });
    };

    /** 4. 删除按钮 **/
    window.del = function (id) {
      console.log('==> del 函数收到的 id =', id); // 调试信息
      fetch('/demo/students?id=' + id, {method: 'DELETE'})
          .then(res => res.json())
          .then(() => {
            table.reload('studentTable'); // 刷新表格
          });
    };

    /** 5. 表单提交（新增 / 更新通用） **/
    form.on('submit(submitStudentForm)', function (data) {
      const formData = data.field;          // 提交数据
      const method = formData.id ? 'PUT' : 'POST'; // 判断是新增还是编辑

      fetch('/demo/students', {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
      }).then(res => res.json()).then(() => {
        layer.closeAll();               // 关闭弹窗
        table.reload('studentTable');   // 刷新表格
      });

      return false; // 阻止表单跳转
    });
  });
</script>
</body>
</html>