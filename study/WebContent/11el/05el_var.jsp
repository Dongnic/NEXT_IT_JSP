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
EL은 기본적으로 출력이 됨, 언어라서 변수선언이 됨.
??? 변수선언헀는데 화면에 출력이 됨
변수선언만 하고 화면에 출력은 안하고 싶으면
; 앞에는 출력안함
EL은 빈값이 있으면 안됨 <%-- ${}은 에러남 --%>
<br>
${a="나는 한창희" ; "" }
<br>
${a }

</body>
</html>