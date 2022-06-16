<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
c: forEach var변수는 c:forEach안에서 EL로 사용
<c:forEach begin="1" end="5" var="i" step="2" varStatus="status"><hr>
	<c:if test="${status.first }">처음에만 실행</c:if>
	<pre>
	begin :	  ${status.begin }
	end : 	  ${status.end }
	step :	  ${status.step }
	current : ${status.current } 
	index :   ${status.index }
	count :   ${status.count }
	first :   ${status.first }
	last :    ${status.last }
	i : 	  ${i }
	</pre>
</c:forEach>

</body>
</html>