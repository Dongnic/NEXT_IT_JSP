<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="08result.jsp" method="post">
	이름 : <input type="text" name="name" value=""> <br>
	나이 : <input type="text" name="age" value=""> <br>
	취미 : <br>
	축구 : <input type="checkBox" name="hobby" value="football"> 
	농구 : <input type="checkBox" name="hobby" value="basketball"> 
	노래 : <input type="checkBox" name="hobby" value="sing"> 
	독서 : <input type="checkBox" name="hobby" value="readbook">
	피아노 : <input type="checkBox" name="hobby" value="piano">
	기타 : <input type="text" name="hobby" value=""> 
	<button type="submit">제출</button>	
</form>	
</body>
</html>