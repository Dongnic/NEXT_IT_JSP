<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("UTF-8"); // 하는 일은 디코딩인데 이름은 인코딩 %> 
<!-- 톰캣은 get방식일 때 자동으로 decoding해줌
	request.setCharacterEncoding은 파라미터 디코딩
	contentType charSet은 response에 보낼 때 인코딩
	습관 적으로 request.setCharacterEncoding(UTF-8)
	charset = UTF-8 로 들이자
 -->
 
 
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String kor = request.getParameter("한");// \ud55c\uae00 : 한글 로 요청하면 나옴
	String eng = request.getParameter("eng");
%>
한글 : <%=kor %> <br>
eng : <%=eng %>

</body>
</html>