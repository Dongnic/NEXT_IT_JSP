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
<!-- 
	쿠키
	: 브라우저에 저장되는 데이터
	(로그인정보는 저장되면 안되니 쿠키로 하지않음)
	id 기억하기, 1주일 간 공지사항 안보기 등등

	브라우저는 요청할 때마다 가지고 있는 쿠키 전부를 무조건 request에 실어서 같이 보냄
 -->
 <%
 	Cookie cookie= new Cookie("han","valueqwerqwer");
 	response.addCookie(cookie);

 	Cookie[] cookies= request.getCookies();
 	for(Cookie cook : cookies){
 		out.print(cook.getName()+" : "+cook.getValue()+"<br>");
 	}
 %>
</body>
</html>