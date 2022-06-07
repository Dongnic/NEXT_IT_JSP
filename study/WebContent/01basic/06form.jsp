<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
post 방식 한글 못 넘기는 이유 
parameter 이름 (ex. "한글")을 못 읽음
<form action="06result.jsp" method="post">
	한글 : <input type="text" name="한글" value=""><br>
	eng : <input type="text" name="eng" value=""><br>
	<button type="submit">제출</button>
</form>
</body>
</html>