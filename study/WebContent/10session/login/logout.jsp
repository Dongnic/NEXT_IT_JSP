<%@page import="com.study.common.util.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/inc/header.jsp" %>
<title>Insert title here</title>
</head>

<body>
<%@include file="/WEB-INF/inc/top.jsp" %> 
<%
	//Cookie cookie=CookieUtils.createCookie("AUTH","");
	//cookie.setMaxAge(0);
	//response.addCookie(cookie);
	session.removeAttribute("USER_INFO");
	response.sendRedirect("login.jsp");
%>

</body>
</html>
