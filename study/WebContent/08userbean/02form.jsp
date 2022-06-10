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
<!--  02result.jsp 에서 UserVO에서 useBean태그 사용
		이 때 02form.jsp에서 <input name="">은 UserVO의 필드이름과 같아야 함 -->
<form action="02result.jsp" method="get">
	userId	 : <input type="text" name="userId" value=""> <br>
	userName : <input type="text" name="userName" value=""> <br>
	userPw 	 : <input type="text" name="userPw" value=""> <br>
	userRole : <input type="text" name="userRole" value=""> <br>
	<button type="submit">제출</button>
</form>

</body>
</html>