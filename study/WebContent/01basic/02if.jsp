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
String ch = request.getParameter("ch");
%>
<%
	if(ch==null){
%>
	파라미터가 없어요
<%
	}else{
%>
	ch : <%=ch %>
<%
	}
%>
</body>
</html>