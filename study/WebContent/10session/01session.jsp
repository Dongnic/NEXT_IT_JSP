<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	// session은 서버쪽에서 생성한 객체
	out.print(session.getId());
	// 쿠키 중에 JSESSION ID 가지고 구별함.
	
	// 쿠키는 브라우저에 저장, session은 서버에서 저장(서버에 있는 객체)
	// 쿠키는 보안이 안좋음. session은 보안이 좋음
	// 쿠키는 1일동안 안보기, id저장하기 등에 쓰이고
	// 세션은 로그인, 장바구니 등에 쓰임
	
	// 세션은 기본적으로 30분 or 1시간 유지
	// 생성된 시간이 아니라 브라우저가 접근 안한시간
	
	// 생성시간, 접근시간
	long createTime= session.getCreationTime();
	long lastAccessTime= session.getLastAccessedTime();
	Date createDate= new Date();
	Date lastAccessDate= new Date();
	createDate.setTime(createTime);
	lastAccessDate.setTime(lastAccessTime);
	
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
%>
<br>
세션 생성시간 : <%=sdf.format(createDate) %> <br>
세션 접근시간 : <%=sdf.format(lastAccessDate) %> <br>

<%
	session.setMaxInactiveInterval(3600);
%>

</body>
</html>