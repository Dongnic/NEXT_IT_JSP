<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>star</title>
</head>
<body>

<% 
	String star = "*";
	String blank = "&nbsp;";
%>
<c:forEach begin="1" end="5" var="i">
	<%=star %><br>
	<%star+="*"; %>
</c:forEach>

<hr><hr><hr>

<c:forEach begin="1" end="5" var="i">
	<%=blank %>
	<%blank+="&nbsp;&nbsp;";%>
	<% star ="";%>
	<c:forEach begin="${i }" end="5" var="j">
		<%star+="*"; %>
	</c:forEach>
	<%=star %>
	<br>
</c:forEach>

<hr><hr><hr>

<%
	star = "*";
%>
<c:forEach begin="1" end="5" var="i">
	<c:forEach begin="${i }" end="5" var="j">
		&nbsp;
	</c:forEach>
	<%=star %>
	<%star+="**"; %>
	<br>
</c:forEach>

</body>
</html>