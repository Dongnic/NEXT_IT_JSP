<%@page import="com.study.login.vo.UserVO"%>
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

자바빈 : 자바빈규칙을 만족하는 자바 클래스<br>
규칙<br>
1. 패키지가 있어야한다.<br>
2. 기본 생성자가 있어야 한다.<br>
3. 멤버변수는 private<br>
4. get/set은 public<br>
5. 직렬화(선택사항)<br>

<br>
<%
	UserVO user = new UserVO();
	UserVO user2 = new UserVO("a001","Yunie","0530","MEMBER");
%>
<%=user %> <br>
<%=user2 %>

<hr>
<hr>

<jsp:useBean id="user3" class="com.study.login.vo.UserVO" scope="request"></jsp:useBean>
<%
	UserVO user4 = (UserVO)request.getAttribute("user4");
	if(user4==null){
		user4= new UserVO();
		request.setAttribute("user4", user4);
	}
%>
<%=user3 %>

</body>
</html>