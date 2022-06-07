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
	out.print("<table border=\"1\">");
	out.print("<tbody>");
	for(int i = 1; i <10; i++){
		out.print("<tr>");
		int sum=0;
		for(int j = 2; j<10;j++){
			out.print("<td>");
			out.print(j+"x"+i);
			sum=i*j;
			out.print("="+sum);
			out.print("</td>");
		}
		out.print("</tr>");
	}
	out.print("</tbody>");
	out.print("</table>");
%>
		
</body>
</html>