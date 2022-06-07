<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 실행 x, 서버키고 브라우저에 직접요청  -->
<%
	out.print("<ul>");
	for(int i = 1; i <=10; i++){
		out.print("<li>");
		int sum=0;
		for(int j = 1; j<=i;j++){
			out.print(j);
			out.print("+");
			sum+=j;
		}
			out.print("="+sum);
	}
	out.print("</ul>");
%>
<hr>

<ul>
	<li>1+1=2</li>
	<li>1+2=3</li>
	<li>1+2+3=6</li>
	<li>1+2+3+4=10</li>
	<li>1+2+3+4+5=15</li>
	<li>1+2+3+4+5+6=21</li>
</ul>
</body>
</html>