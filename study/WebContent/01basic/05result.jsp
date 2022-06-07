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
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String hobby = request.getParameter("hobby");

%>
이름 : <%=name %> <br>
나이 : <%=age %> <br>
취미 : <%=hobby %> <br>
</body>
</html>