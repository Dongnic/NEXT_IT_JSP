<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단1</title>
</head>
<body>

	<table border="1">
	<tbody>
		<c:forEach begin="1" end="9" var="i">
			<%="<tr>"%>
			<c:forEach begin="2" end="9" var="j">
				<%="<td>"%>
				${j } x ${i } = ${i*j }
				<%="</td>"%>
			</c:forEach>
			<%="</tr>"%>
		</c:forEach>
	</tbody>
	</table>

</body>
</html>