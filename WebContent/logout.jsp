<%
session.removeAttribute("su");

session.invalidate();
response.sendRedirect("signin.jsp");
%>