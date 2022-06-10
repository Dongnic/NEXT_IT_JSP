<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
	String idol = request.getParameter("idol");
	String viewPage = "";
	List<String> memberList = new ArrayList<>();
	
	if("aoa".equals(idol)){
		viewPage="04aoa.jsp";	
		memberList.add("설현");
		memberList.add("초아");
		memberList.add("기타 등등");
	}else if("bts".equals(idol)){
		viewPage="04bts.jsp";	
		memberList.add("정국");
		memberList.add("뷔");
		memberList.add("지민");
		memberList.add("기타 등등");
		
	}
	request.setAttribute("memberList", memberList);
%>

	04form.jsp에서 뭘 선택했냐에 따라 aoa.jsp 또는 bts.jsp로 이동하도록
	(화면 양식이 같다면 1개만 만들어도 됨)
	forward돼도 같은 request를 공유함.
	<jsp:forward page="<%=viewPage %>"></jsp:forward>
</body>
</html>