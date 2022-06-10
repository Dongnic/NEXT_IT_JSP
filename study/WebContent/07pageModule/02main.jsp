<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Map<String, String[]> paramMap = request.getParameterMap();
	Set<Map.Entry<String, String[]>> entrySet = paramMap.entrySet();
	String mbti="";
	for(Map.Entry<String, String[]> entry : entrySet){
		mbti+=entry.getValue()[0];
	}
	switch (mbti){
		case "intj" :
			request.setAttribute("mbti", mbti);
			request.setAttribute("type", "용의주도한 전략가");
			break;
		case "ensp" :
			request.setAttribute("mbti", mbti);
			request.setAttribute("type", "골리");
			break;
		case "intp" :
			request.setAttribute("mbti", mbti);
			request.setAttribute("type", "일반인");
			break;
		case "entj" :
			request.setAttribute("mbti", mbti);
			request.setAttribute("type", "헐크");
			break;
		default:
	}
%>
<jsp:include page="02sub.jsp"></jsp:include>
<br>
후
</body>
</html>