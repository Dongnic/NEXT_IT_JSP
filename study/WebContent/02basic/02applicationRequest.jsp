<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.print(request.getAttribute("data"));
	out.print("<br>");
	out.print(application.getAttribute("data"));
%>
</body>
</html>