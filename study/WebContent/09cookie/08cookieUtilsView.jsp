<%@page import="com.study.common.util.CookieUtils"%>
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
	CookieUtils cookieUtils= new CookieUtils(request);
	if(cookieUtils.exists("han")){
		Cookie cookie= cookieUtils.getCookie("han");
		out.print(cookie.getName()+" : "+cookie.getValue());
	}

%>

</body>
</html>