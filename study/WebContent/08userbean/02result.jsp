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
<!-- id는 변수이름을 의미 -->
<jsp:useBean id="user" class="com.study.login.vo.UserVO" scope="request"></jsp:useBean>
<!-- name은 useBean의 변수 이름 property는 02form의 name -->
<%-- 아래와 같은 구문
<%
	user.setUserId(request.getParameter("userId"));
%> --%>
<%-- <jsp:setProperty property="userId" name="user"/>
<jsp:setProperty property="userName" name="user"/>
<jsp:setProperty property="userPw" name="user"/>
<jsp:setProperty property="userRole" name="user"/>
 --%>
<!--  한방에 세팅 -->
<jsp:setProperty property="*" name="user"/>
<%=user %>
</body>
</html>