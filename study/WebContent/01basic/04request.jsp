<%@page import="java.awt.print.Printable"%>
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
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
%>

파라미터 id 값 : <%=id %>	<br>
파라미터 pw 값 : <%=pw %>	<br>
<hr>
<a href="04aGet.jsp?p1=한창희">a태그로 가는건 get방식</a>
<%
	String p1 = request.getParameter("p1");
%>
파라미터 p1 = <%=p1%>

<!-- get방식 요청 방법 : url 직접 입력 (a href=""), (form action="" method="get")   -->
<!-- post방식 요청 방법 : (form action="" Method="post") 밖에 없다.  -->
<!-- 대부분 get방식 데이터가 많거나 보안 post방식 -->

</body>
</html>