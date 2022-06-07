<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- form태그로 파라미터 여러개 보내기 -->
<!-- form태그 안에 있는 input, textarea 태그의 파라미터를 한번에 보냄 -->
<!-- input, textarea 태그의 name 속성이 파라미터 이름, value는 파라미터 값 -->
<form action="05result.jsp" method="get"> <!-- 디폴트 get 방식 -->
	이름 : <input type="text" name="name" value=""><br>
	나이 : <input type="number" name="age" value=""><br>
	취미 : <input type="text" name="hobby" value=""><br>
	<button type="submit">제출</button>
</form>

</body>
</html>