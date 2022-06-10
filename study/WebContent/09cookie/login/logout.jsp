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
	CookieUtils cookieUtils = new CookieUtils(request);
    Cookie cookie =	CookieUtils.createCookie("AUTH","", 0);
	response.addCookie(cookie);
	response.sendRedirect("login.jsp");
// 	if(request.getParameter("rememberMe") == null){
// 	   Cookie cookie1 = CookieUtils.createCookie("SAVE_ID","", 0);
// 	   response.addCookie(cookie1);
// 	}
%>

</body>
</html>
