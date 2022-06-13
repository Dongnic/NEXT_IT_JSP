<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/inc/top.jsp" %>
<%-- <, > <=, =>, ==, !=, !, empty --%>
<pre>
	${10 == 5 }		${10 eq 5 }
<%-- 	${10 != 5 }		${10 ne 5 } --%>
	${10 <  5 }		${10 lt 5 }
	${10 >  5 }		${10 gt 5 }
	${10 <= 5 }		${10 le 5 }
	${10 >= 5 }		${10 ge 5 }
	${!(10==5)}		${not(10 eq 5)}
	

</pre>

</body>
</html>