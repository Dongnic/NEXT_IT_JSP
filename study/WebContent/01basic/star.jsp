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
	String star = "*";
	for(int i = 0; i<5;i++){
		out.print(star+"<br>");
		star+="*";
		
	}
%>
<hr><hr><hr>

<%
String blank = "&nbsp;";
for(int i = 5; i > 0; i--) {
	out.print(blank);
	blank+="&nbsp;&nbsp;";
	for(int k = 0; i > k; k++){
		out.print("*");
	}
	out.print("<br>");
}	
%>
<hr><hr><hr>

<%
String star2 = "*";
String blank2 = "&nbsp;&nbsp;";
for(int i = 5; i > 0; i--) {
	blank+="&nbsp;&nbsp;";
	for(int k = 0; i > k; k++){
		out.print(blank2);
	}
	out.print(star2);
	star2+="**";
	out.print("<br>");
}	
%>
</body>
</html>