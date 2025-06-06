<%
  session.invalidate(); // 清除 session
  response.sendRedirect("JXYindex.jsp");
%>
