<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My JSP 'login.jsp' starting page</title>
  </head>
  
  <body>
    <form action="servlet/LoginServlet" method="post">
    	用户名：<input type="text" name="username"><br>
    	密码：<input type="password" name="password"><br>
    	<input type="submit" value="登录">
    </form>
  </body>
</html>
