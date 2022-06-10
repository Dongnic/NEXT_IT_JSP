<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 사진이 먼저 오냐 memberList가 먼저 오냐 차이 -->
<img alt="사진" src="aoa.jpeg" width="400px" height="300px"> <br>
<img alt="사진" src="/study/07pageModule/aoa.jpeg" width="400px" height="300px"> <br>
<img alt="사진" src="<%=request.getContextPath() %>/07pageModule/aoa.jpeg" width="400px" height="300px"> <br>

<%
	List<String> memberList=(List<String>)request.getAttribute("memberList");
	out.print("<ul>");
	for(String member : memberList){
		out.print("<li>"+member);
	}
%>

</body>
</html>