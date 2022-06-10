<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>

<%
	Cookie cookie = new Cookie("next", "it");
	// 기본적으로 maxAge = -1 브라우저 끄면 사라짐
	cookie.setMaxAge(0); // 0이상으로 설정하면 브라우저가 꺼져도 남아있음
						  // 단, 해당 시간이 지나면 무조건 사라짐
						  // 1 = 1초
	response.addCookie(cookie);						  
%>


</body>
</html>