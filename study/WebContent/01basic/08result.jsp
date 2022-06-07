<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% request.setCharacterEncoding("UTF-8"); %> 
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String[] hobbies = request.getParameterValues("hobby");
	String[] names = request.getParameterValues("name");
// 	아래와 같음
// 	names[0]=request.getParameter("name");
%>
<h1>모든 파라미터 한번에 Map으로 보기 </h1>
<h3>1. Set for문 이용</h3>
<%
	Map<String, String[]> paramMap = request.getParameterMap();
	Set<String> keySet = paramMap.keySet();
	for(String key : keySet){
		out.print(key+" : ");
		String[] values = paramMap.get(key);
		for(String value : values){
			out.print(value+",");
		}
		out.print("<br>");
	}
	
%>
<h3>2. Iterator</h3>
<%
	Iterator<String> iterator = keySet.iterator();
	while(iterator.hasNext()){
		String key = iterator.next();
		out.print(key+" : ");
		String[] values = paramMap.get(key);
		for(String value : values){
			out.print(value+",");
		}
		out.print("<br>");
		
	}

%>
<h3>3. Entryset</h3>
<%
	Set<Entry<String, String[]>> entrySet = paramMap.entrySet();
	for(Entry<String, String[]> entry : entrySet){
		String key = entry.getKey();
		String[] values = entry.getValue();
		out.print(key+" : ");
		for(String value : values){
			out.print(value+",");
		}
		out.print("<br>");
	}

%>

</body>
</html>